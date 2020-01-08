package org.woehlke.simulation.mandelbrot.control.state;

/**
 * Mandelbrot Set drawn by a Turing Machine.
 *
 * (C) 2006 - 2015 Thomas Woehlke.
 * https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html
 * @author Thomas Woehlke
 *
 * Created by tw on 16.12.2019.
 */
public enum RadioButtons {
    RADIO_BUTTONS_SWITCH,
    RADIO_BUTTONS_ZOOM;

    public static RadioButtons start(){
        return RADIO_BUTTONS_SWITCH;
    }
}
