package org.woehlke.simulation.mandelbrot.control.impl;

import org.woehlke.simulation.mandelbrot.control.ApplicationStateMachine;
import org.woehlke.simulation.mandelbrot.control.state.ApplicationState;
import org.woehlke.simulation.mandelbrot.control.ApplicationContext;

import java.util.logging.Logger;

import static org.woehlke.simulation.mandelbrot.control.state.ApplicationState.*;
import static org.woehlke.simulation.mandelbrot.control.state.ApplicationState.JULIA_SET_ZOOM;

/**
 * Mandelbrot Set drawn by a Turing Machine.
 *
 * (C) 2006 - 2015 Thomas Woehlke.
 * https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html
 * @author Thomas Woehlke
 *
 * Created by tw on 16.12.2019.
 */
public class ApplicationStateMachineImpl implements ApplicationStateMachine {

    private ApplicationState applicationState;

    private final ApplicationContext ctx;

    public ApplicationStateMachineImpl(ApplicationContext ctx) {
        this.applicationState = ApplicationState.MANDELBROT_SWITCH;
        this.ctx = ctx;
    }

    public void click(){
        ApplicationState nextApplicationState = null;
        switch (applicationState){
            case MANDELBROT_SWITCH:
                nextApplicationState = JULIA_SET_SWITCH;
                break;
            case JULIA_SET_SWITCH:
                nextApplicationState = MANDELBROT_SWITCH;
                break;
            case MANDELBROT_ZOOM:
                nextApplicationState = MANDELBROT_ZOOM;
                break;
            case JULIA_SET_ZOOM:
                nextApplicationState = JULIA_SET_ZOOM;
                break;
        }
        if(this.ctx.getConfig().getLogDebug()){
            log.info("click: "+ applicationState + " -> "+ nextApplicationState);
        }
        this.setApplicationState(nextApplicationState);
    }

    public void setModeSwitch() {
        ApplicationState nextApplicationState  = null;
        switch (applicationState){
            case MANDELBROT_SWITCH:
            case MANDELBROT_ZOOM:
                nextApplicationState = MANDELBROT_SWITCH;
                break;
            case JULIA_SET_SWITCH:
            case JULIA_SET_ZOOM:
                nextApplicationState = JULIA_SET_SWITCH;
                break;
        }
        if(this.ctx.getConfig().getLogDebug()){
            String msg = "setModeZoom: "+ applicationState + " -> "+ nextApplicationState;
            log.info(msg);
        }
        this.setApplicationState(nextApplicationState);
    }

    public void setModeZoom() {
        ApplicationState nextApplicationState = null;
        switch (applicationState){
            case MANDELBROT_SWITCH:
            case MANDELBROT_ZOOM:
                nextApplicationState = MANDELBROT_ZOOM;
                break;
            case JULIA_SET_SWITCH:
            case JULIA_SET_ZOOM:
                nextApplicationState = JULIA_SET_ZOOM;
                break;
        }
        if(this.ctx.getConfig().getLogDebug()){
            String msg = "setModeZoom: "+ applicationState + " -> "+ nextApplicationState;
            log.info(msg);
        }
        this.setApplicationState(nextApplicationState);
    }

    public ApplicationState getState() {
        return applicationState;
    }

    public void setApplicationState(ApplicationState applicationState) {
        this.applicationState = applicationState;
    }

    public static Logger log = Logger.getLogger(ApplicationStateMachineImpl.class.getName());
}
