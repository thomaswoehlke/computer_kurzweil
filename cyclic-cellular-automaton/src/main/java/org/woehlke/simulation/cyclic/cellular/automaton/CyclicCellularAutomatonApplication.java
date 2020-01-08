package org.woehlke.simulation.cyclic.cellular.automaton;

import org.woehlke.simulation.cca.config.ObjectRegistry;
import org.woehlke.simulation.cca.view.CyclicCellularAutomatonFrame;

/**
 * Cyclic Cellular Automaton.
 *
 * (C) 2006 - 2013 Thomas Woehlke.
 * http://thomas-woehlke.de/p/cyclic-cellular-automaton/
 * @author Thomas Woehlke
 */
public class CyclicCellularAutomatonApplication {

    private CyclicCellularAutomatonApplication() { }

    /**
     * Starting the App.
     * @param args CLI Parameter
     */
    public static void main(String[] args) {
        ObjectRegistry ctx = new ObjectRegistry();
        CyclicCellularAutomatonFrame frame = new CyclicCellularAutomatonFrame(ctx);
    }
}
