package org.woehlke.computer.kurzweil.tabs.evolution;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.commons.tabs.TabController;


/**
 * The ControllerThreadApplet controls the Interactions between Model and View (MVC-Pattern).
 * <p>
 * Simulated Evolution.
 * Artificial Life Simulation of Bacteria Motion depending on DNA.
 * <p>
 * &copy; 2006 - 2013 Thomas Woehlke.
 * http://thomas-woehlke.de/p/simulated-evolution/
 *
 * @author Thomas Woehlke
 * Date: 05.02.2006
 * Time: 00:36:20
 */
@Log
@Getter
@ToString(callSuper = true, exclude={"tabCtx"})
@EqualsAndHashCode(callSuper = true, exclude={"tabCtx"})
public class SimulatedEvolutionController extends Thread implements TabController {

    private final SimulatedEvolutionContext tabCtx;
    private Boolean goOn;
    private final int threadSleepTime;

  public SimulatedEvolutionController(
      SimulatedEvolutionContext tabCtx
  ) {
      super("DNA-Controller");
      this.tabCtx = tabCtx;
      this.goOn = Boolean.TRUE;
      this.threadSleepTime = this.tabCtx.getCtx().getProperties().getEvolution().getControl().getThreadSleepTime();
  }

  public void run() {
    boolean doMyJob;
    do {
      synchronized (goOn) {
        doMyJob = goOn.booleanValue();
      }
      if( this.tabCtx != null){
        synchronized (this.tabCtx) {
            this.tabCtx.getStepper().step();
            this.tabCtx.getCanvas().update();
            this.tabCtx.getTab().repaint();
        }
      }
      try {
        sleep( this.threadSleepTime );
      } catch (InterruptedException e) {
        System.out.println(e.getLocalizedMessage());
      }
    }
    while (doMyJob);
  }

    @Override
    public void exit() {
        try {
            synchronized (goOn) {
                goOn = Boolean.FALSE;
            }
            join();
        } catch (InterruptedException e){
            log.info(e.getMessage());
        }
    }

}
