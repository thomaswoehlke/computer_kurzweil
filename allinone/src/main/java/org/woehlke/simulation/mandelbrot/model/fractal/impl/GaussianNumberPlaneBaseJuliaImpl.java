package org.woehlke.simulation.mandelbrot.model.fractal.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.woehlke.simulation.all.model.LatticePointMandelbrot;
import org.woehlke.simulation.mandelbrot.control.common.MandelbrotApplicationContext;
import org.woehlke.simulation.mandelbrot.control.state.FractalSetType;
import org.woehlke.simulation.mandelbrot.model.fractal.GaussianNumberPlaneBaseJulia;
import org.woehlke.simulation.mandelbrot.model.numbers.*;

import java.util.ArrayDeque;
import java.util.Deque;

import static org.woehlke.simulation.mandelbrot.model.numbers.ComputingPlan.startCenterForJulia;
import static org.woehlke.simulation.mandelbrot.model.numbers.ComputingPlan.startWorldDimension;

@Service
public class GaussianNumberPlaneBaseJuliaImpl extends GaussianNumberPlaneBaseImpl
    implements GaussianNumberPlaneBaseJulia {

    private Deque<ComputingPlan> complexCenterForZoomedJulia = new ArrayDeque<>();

    private final ZoomLevel zoomLevel;

    private ComplexNumber complexNumberForJuliaSetC;

    @Autowired
    public GaussianNumberPlaneBaseJuliaImpl(MandelbrotApplicationContext ctx) {
        super(ctx,FractalSetType.JULIA_SET);
        zoomLevel = new ZoomLevel();
    }

    @Override
    public void start(){
        super.start();
        zoomLevel.setLowestZoomLevel();
    }

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

    private ComplexNumber getComplexNumberFromLatticeCoordsForJulia(LatticePointMandelbrot turingPosition) {
        double realX =startCenterForJulia.getReal()
            + (startWorldDimension.getReal()*turingPosition.getX())/this.ctx.getWorldDimensions().getX();
        double imgY = startCenterForJulia.getImg()
            + (startWorldDimension.getImg()*turingPosition.getY())/this.ctx.getWorldDimensions().getY();
        return new ComplexNumber(realX,imgY);
    }

    //TODO:
    private ComplexNumber getComplexNumberFromLatticeCoordsForZoomedJulia(LatticePointMandelbrot turingPosition) {
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
                LatticePointMandelbrot zLatticePoint = new LatticePointMandelbrot(x, y);
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
                LatticePointMandelbrot zLatticePoint = new LatticePointMandelbrot(x, y);
                ComplexNumber z = this.getComplexNumberFromLatticeCoordsForZoomedJulia(zLatticePoint);
                ComplexNumberFractal result  = ComplexNumberFractal.iterateJuliaSetFunction(z,c);
                super.setCellStatusFor(x,y,result.getIterations());
            }
        }
    }

    public void computeTheSet(LatticePointMandelbrot latticePoint) {
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

    public boolean isInZooomed(LatticePointMandelbrot turingPosition) {
        ComplexNumber c = this.getComplexNumberFromLatticeCoordsForZoomedJulia(turingPosition);
        ComplexNumber z = new ComplexNumber();
        ComplexNumberFractal result  = ComplexNumberFractal.iterateJuliaSetFunction(z,c);
        return result.getInJuliaSet();
    }

    public boolean isInSet(LatticePointMandelbrot turingPosition) {
        ComplexNumber c = this.getComplexNumberFromLatticeCoordsForJulia(turingPosition);
        ComplexNumber z = new ComplexNumber();
        ComplexNumberFractal result  = ComplexNumberFractal.iterateJuliaSetFunction(z,c);
        return result.getInJuliaSet();
    }

    private void computeWZoomedWorld(ComplexNumber c){
        for(int y = 0; y < ctx.getWorldDimensions().getY(); y++){
            for(int x = 0; x < ctx.getWorldDimensions().getX(); x++){
                LatticePointMandelbrot p = new LatticePointMandelbrot(x, y);
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
    public void zoomInto(LatticePointMandelbrot zoomLatticePoint) {
        if(ctx.getProperties().getLogDebug()){
            log.info("zoomIntoTheMandelbrotSet: "+ zoomLatticePoint +" - old:  "+this.getZoomCenter());
        }
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
        if(ctx.getProperties().getLogDebug()) {
            String msg = "zoomPoint: "+ zoomLatticePoint
                + " zoomCenterNew: " + this.getZoomCenter()
                + " zoomLevel:  "+ this.zoomLevel.getZoomLevel();
            log.info(msg);
        }
        //    ComplexNumber c = this.getComplexNumberFromLatticeCoordsForZoomedJulia(zoomLatticePoint);
        ComplexNumber c = this.complexNumberForJuliaSetC;
        computeWZoomedWorld(c);
    }

    //TODO:
    public void zoomOut() {
        if(ctx.getProperties().getLogDebug()) {
            log.info("zoomOutOfTheMandelbrotSet: " + this.getZoomCenter());
        }
        if(!this.zoomLevel.isLowestZoomLevel()) {
            this.zoomLevel.decreaseZoomLevel();
            try {
                //ComplexNumber zoomCenter = complexCenterForZoomedJulia.pop();
                //this.setZoomCenter(zoomCenter);
            } catch (Exception e){

            }
        }
        if(ctx.getProperties().getLogDebug()) {
            log.info("zoomCenter: " + this.getZoomCenter() + " - zoomLevel:  "+ this.zoomLevel.getZoomLevel());
        }
        ComplexNumber c = this.complexNumberForJuliaSetC;
        computeWZoomedWorld(c);
    }

    public String getZoomLevel() {
        return this.zoomLevel.getZoomLevel() + "";
    }
}
