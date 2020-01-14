package org.woehlke.simulation.mandelbrot.model.turing;

import org.springframework.stereotype.Service;
import org.woehlke.simulation.mandelbrot.model.state.TuringPhase;

import java.util.logging.Logger;

/**
 * Mandelbrot Set drawn by a Turing Machine.
 *
 * (C) 2006 - 2015 Thomas Woehlke.
 * https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html
 * @author Thomas Woehlke
 *
 * Created by tw on 16.12.2019.
 */
@Service
public class TuringPhaseStateMachine {

    private TuringPhase turingTuringPhase;

    public TuringPhaseStateMachine() {
        turingTuringPhase = TuringPhase.start();
    }

    public void start(){
        turingTuringPhase = TuringPhase.start();
    }

    public void finishSearchTheSet(){
        turingTuringPhase = TuringPhase.WALK_AROUND_THE_SET;
    }

    public void finishWalkAround() {
        turingTuringPhase = TuringPhase.FILL_THE_OUTSIDE_WITH_COLOR;
    }

    public void finishFillTheOutsideWithColors() {
        turingTuringPhase = TuringPhase.FINISHED;
    }

    public boolean isFinished() {
        return turingTuringPhase == TuringPhase.FINISHED;
    }

    public TuringPhase getTuringTuringPhase() {
        return turingTuringPhase;
    }

    public static Logger log = Logger.getLogger(TuringPhaseStateMachine.class.getName());
}