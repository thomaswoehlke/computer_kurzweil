package org.woehlke.simulation.mandelbrot.model.turing.state;

/**
 * Mandelbrot Set drawn by a Turing Machine.
 *
 * (C) 2006 - 2015 Thomas Woehlke.
 * https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html
 * @author Thomas Woehlke
 *
 * Created by tw on 18.08.15.
 */
public enum TuringDirection {
    UP,
    RIGHT,
    DOWN,
    LEFT;

    public static TuringDirection start(){
        return LEFT;
    }
}
