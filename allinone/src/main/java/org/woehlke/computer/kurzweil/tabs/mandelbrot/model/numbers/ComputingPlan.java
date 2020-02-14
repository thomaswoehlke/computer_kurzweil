package org.woehlke.computer.kurzweil.tabs.mandelbrot.model.numbers;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.woehlke.computer.kurzweil.model.LatticePoint;
import org.woehlke.computer.kurzweil.tabs.mandelbrot.model.state.ApplicationState;
import org.woehlke.computer.kurzweil.tabs.mandelbrot.model.state.ClickBehaviour;
import org.woehlke.computer.kurzweil.tabs.mandelbrot.model.state.FractalSetType;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class ComputingPlan {

    private final static double startWorldDimensionReal = 3.2d;
    private final static double startWorldDimensionImg = 2.34d;

    private final static double startCenterForJuliaReal = -1.6d;
    private final static double startCenterForJuliaImg =  -1.17d;

    private final static double startCenterForMandelbrotReal = -2.2f;
    private final static double startCenterForMandelbrotImg = -1.17f;

    public final static ComplexNumber startWorldDimension;
    public final static ComplexNumber startCenterForJulia;
    public final static ComplexNumber startCenterForMandelbrot;
    static {
        startWorldDimension = new ComplexNumber(
            startWorldDimensionReal,
            startWorldDimensionImg
        );
        startCenterForJulia = new ComplexNumber(
            startCenterForJuliaReal,
            startCenterForJuliaImg
        );
        startCenterForMandelbrot = new ComplexNumber(
            startCenterForMandelbrotReal,
            startCenterForMandelbrotImg
        );
    }

    private final ApplicationState applicationState;
    private final ZoomLevel zoomLevel;
    private final ClickBehaviour clickBehaviour;
    private final FractalSetType fractalSetType;
    private final ComplexNumber worldDimension;
    private final ComplexNumber center;
    private final LatticePoint latticePoint;

    public ComputingPlan(
        ClickBehaviour clickBehaviour,
        FractalSetType fractalSetType,
        ComplexNumber worldDimension,
        ComplexNumber center,
        ZoomLevel zoomLevel,
        LatticePoint latticePoint
    ) {
        this.zoomLevel = zoomLevel;
        this.clickBehaviour = clickBehaviour;
        this.fractalSetType = fractalSetType;
        this.worldDimension = worldDimension;
        this.center = center;
        this.applicationState = ApplicationState.resolve(fractalSetType,clickBehaviour);
        this.latticePoint = latticePoint;
    }

    public ComputingPlan(
        ApplicationState applicationState
    ) {
        this.applicationState = applicationState;
        this.zoomLevel = new ZoomLevel();
        this.clickBehaviour = applicationState.getClickBehaviour();
        this.fractalSetType = applicationState.getFractalSetType();
        this.worldDimension = startWorldDimension;
        this.latticePoint = new LatticePoint();
        switch (this.fractalSetType){
            case JULIA_SET:
                this.center = startCenterForJulia;
                break;
            case MANDELBROT_SET:
            default:
                this.center = startCenterForMandelbrot;
                break;
        }

    }

    public ComputingPlan() {
        this.zoomLevel = new ZoomLevel();
        this.clickBehaviour = ClickBehaviour.start();
        this.fractalSetType = FractalSetType.start();
        this.applicationState = ApplicationState.resolve(fractalSetType,clickBehaviour);
        this.worldDimension = startWorldDimension;
        this.latticePoint = new LatticePoint();
        switch (this.fractalSetType){
            case JULIA_SET:
                this.center = startCenterForJulia;
                break;
            case MANDELBROT_SET:
            default:
                this.center = startCenterForMandelbrot;
                break;
        }
    }
}
