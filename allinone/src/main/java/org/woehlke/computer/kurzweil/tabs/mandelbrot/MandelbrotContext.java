package org.woehlke.computer.kurzweil.tabs.mandelbrot;


import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.tabs.TabType;
import org.woehlke.computer.kurzweil.commons.tabs.TabContext;
import org.woehlke.computer.kurzweil.tabs.evolution.SimulatedEvolutionController;


import static java.lang.Thread.State.NEW;
import static org.woehlke.computer.kurzweil.tabs.TabType.MANDELBROT_SET;

@Log
@Getter
public class MandelbrotContext implements TabContext {

    private final TabType tabType = MANDELBROT_SET;
    private final ComputerKurzweilApplicationContext ctx;
    private final MandelbrotTab tab;
    private final Mandelbrot stepper;
    private final MandelbrotCanvas canvas;

    private MandelbrotController controller;

    public MandelbrotContext(MandelbrotTab tab, ComputerKurzweilApplicationContext ctx) {
        this.tab = tab;
        this.ctx = ctx;
        this.canvas = new MandelbrotCanvas( this );
        this.controller = new MandelbrotController(this);
        this.stepper = new Mandelbrot( this );
    }

    @Override
    public void stopController() {
        this.controller.exit();
        this.controller = null;
        this.controller = new MandelbrotController(this);
    }

    @Override
    public void startController() {
        if(this.controller == null){
            this.controller = new MandelbrotController(this);
        } else {
            if(this.controller.getState() != NEW){
                this.stopController();
            }
        }
    }
}
