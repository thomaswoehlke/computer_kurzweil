package org.woehlke.computer.kurzweil.tabs.mandelbrot.model.fractal;


import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.model.LatticePoint;
import org.woehlke.computer.kurzweil.tabs.mandelbrot.model.state.FractalSetType;
import org.woehlke.computer.kurzweil.tabs.mandelbrot.model.numbers.*;

import java.util.ArrayDeque;
import java.util.Deque;

import static org.woehlke.computer.kurzweil.tabs.mandelbrot.model.numbers.ComputingPlan.startCenterForJulia;
import static org.woehlke.computer.kurzweil.tabs.mandelbrot.model.numbers.ComputingPlan.startWorldDimension;

@Log
public class GaussianNumberPlaneBaseJulia extends GaussianNumberPlaneBase {

    private Deque<ComputingPlan> complexCenterForZoomedJulia = new ArrayDeque<>();

    private final ZoomLevel zoomLevel;
    private ComplexNumber complexNumberForJuliaSetC;

    public GaussianNumberPlaneBaseJulia(ComputerKurzweilApplicationContext ctx) {
        super(ctx, FractalSetType.JULIA_SET);
        this.zoomLevel = new ZoomLevel();
    }

    @Override
    public void start(){
        super.start();
        zoomLevel.setLowestZoomLevel();
    }

    @Override
    public void stop() {}

    @Override
    public void setModeZoom() {
        if(super.isModeSwitch()){
            super.setModeZoom();
            zoomLevel.setLowestZoomLevel();
        }
    }

    //TODO: implement and Bugfix
    @Override
    public void setModeSwitch(){
        if(super.isModeZoom()){
            zoomLevel.setLowestZoomLevel();
        }
        super.setModeSwitch();
    }

    private ComplexNumber getComplexNumberFromLatticeCoordsForJulia(LatticePoint turingPosition) {
        double realX =startCenterForJulia.getReal()
            + (startWorldDimension.getReal()*turingPosition.getX())/this.ctx.getWorldDimensions().getX();
        double imgY = startCenterForJulia.getImg()
            + (startWorldDimension.getImg()*turingPosition.getY())/this.ctx.getWorldDimensions().getY();
        return new ComplexNumber(realX,imgY);
    }

    //TODO:
    private ComplexNumber getComplexNumberFromLatticeCoordsForZoomedJulia(LatticePoint turingPosition) {
        double realX = ( startCenterForJulia.getReal() / this.zoomLevel.getZoomLevel() )
            + ( startWorldDimension.getReal()*turingPosition.getX())/(this.ctx.getWorldDimensions().getX() * this.zoomLevel.getZoomLevel());
        double imgY = ( startCenterForJulia.getImg() / this.zoomLevel.getZoomLevel() )
            + (startWorldDimension.getImg()*turingPosition.getY())/(this.ctx.getWorldDimensions().getY() * this.zoomLevel.getZoomLevel());
        return new ComplexNumber(realX,imgY);
    }

    private void computeTheJuliaSetForC(ComplexNumber c) {
        this.complexNumberForJuliaSetC = c;
        for(int y = 0; y < this.ctx.getWorldDimensions().getY(); y++) {
            for (int x = 0; x < this.ctx.getWorldDimensions().getX(); x++) {
                LatticePoint zLatticePoint = new LatticePoint(x, y);
                ComplexNumber z = this.getComplexNumberFromLatticeCoordsForJulia(zLatticePoint);
                ComplexNumberFractal result  = ComplexNumberFractal.iterateJuliaSetFunction(z,c);
                super.setCellStatusFor(zLatticePoint,result.getIterations());
            }
        }
    }

    private void computeTheZoomedJuliaSetForC(ComplexNumber c) {
        this.complexNumberForJuliaSetC = c;
        for(int y = 0; y < this.ctx.getWorldDimensions().getY(); y++) {
            for (int x = 0; x < this.ctx.getWorldDimensions().getX(); x++) {
                LatticePoint zLatticePoint = new LatticePoint(x, y);
                ComplexNumber z = this.getComplexNumberFromLatticeCoordsForZoomedJulia(zLatticePoint);
                ComplexNumberFractal result  = ComplexNumberFractal.iterateJuliaSetFunction(z,c);
                super.setCellStatusFor(x,y,result.getIterations());
            }
        }
    }

    public void computeTheSet(LatticePoint latticePoint) {
        if(super.isModeZoom()){
            ComplexNumber c = getComplexNumberFromLatticeCoordsForZoomedJulia(latticePoint);
            computeTheZoomedJuliaSetForC(c);
            ComputingPlan x = new ComputingPlan();
        } else {
            ComplexNumber c = getComplexNumberFromLatticeCoordsForJulia(latticePoint);
            computeTheJuliaSetForC(c);
            ComputingPlan x = new ComputingPlan();
        }
    }

    //TODO: usage
    public boolean isInZooomed(LatticePoint turingPosition) {
        ComplexNumber c = this.getComplexNumberFromLatticeCoordsForZoomedJulia(turingPosition);
        ComplexNumber z = new ComplexNumber();
        ComplexNumberFractal result  = ComplexNumberFractal.iterateJuliaSetFunction(z,c);
        return result.getInJuliaSet();
    }

    //TODO: usage
    public boolean isInSet(LatticePoint turingPosition) {
        ComplexNumber c = this.getComplexNumberFromLatticeCoordsForJulia(turingPosition);
        ComplexNumber z = new ComplexNumber();
        ComplexNumberFractal result  = ComplexNumberFractal.iterateJuliaSetFunction(z,c);
        return result.getInJuliaSet();
    }

    private void computeWZoomedWorld(ComplexNumber c){
        for(int y = 0; y < ctx.getWorldDimensions().getY(); y++){
            for(int x = 0; x < ctx.getWorldDimensions().getX(); x++){
                LatticePoint p = new LatticePoint(x, y);
                ComplexNumber z = this.getComplexNumberFromLatticeCoordsForZoomedJulia(p);
                ComplexNumberFractal f = ComplexNumberFractal.iterateJuliaSetFunction(z,c);
                if(f.getInJuliaSet()){
                    super.setCellStatusFor(p.getX(),p.getY(),f.getIterations());
                } else {
                    super.setCellStatusFor(p.getX(),p.getY(),0);
                }
            }
        }
    }

    //TODO:
    public void zoomInto(LatticePoint zoomLatticePoint) {
        log.info("zoomIntoTheMandelbrotSet: "+ zoomLatticePoint +" - old:  "+this.getZoomCenter());
        boolean LowestZoomLevel = zoomLevel.isLowestZoomLevel();
        this.zoomLevel.inceaseZoomLevel();
        if(LowestZoomLevel){
            ComplexNumber complexCenter = new ComplexNumber(startCenterForJulia);
            //complexCenterForZoomedJulia.push(complexCenter);
            this.setZoomCenter(getComplexNumberFromLatticeCoordsForJulia(zoomLatticePoint));
        } else {
            this.setZoomCenter(getComplexNumberFromLatticeCoordsForZoomedJulia(zoomLatticePoint));
        }
        // complexCenterForZoomedJulia.push(this.getZoomCenter());
            String msg = "zoomPoint: "+ zoomLatticePoint
                + " zoomCenterNew: " + this.getZoomCenter()
                + " zoomLevel:  "+ this.zoomLevel.getZoomLevel();
            log.info(msg);
        //    ComplexNumber c = this.getComplexNumberFromLatticeCoordsForZoomedJulia(zoomLatticePoint);
        ComplexNumber c = this.complexNumberForJuliaSetC;
        computeWZoomedWorld(c);
    }

    //TODO:
    public void zoomOut() {
        log.info("zoomOutOfTheMandelbrotSet: " + this.getZoomCenter());
        if(!this.zoomLevel.isLowestZoomLevel()) {
            this.zoomLevel.decreaseZoomLevel();
            try {
                //ComplexNumber zoomCenter = complexCenterForZoomedJulia.pop();
                //this.setZoomCenter(zoomCenter);
            } catch (Exception e){

            }
        }
        log.info("zoomCenter: " + this.getZoomCenter() + " - zoomLevel:  "+ this.zoomLevel.getZoomLevel());
        ComplexNumber c = this.complexNumberForJuliaSetC;
        computeWZoomedWorld(c);
    }

    public String getZoomLevel() {
        return this.zoomLevel.getZoomLevel() + "";
    }
}
