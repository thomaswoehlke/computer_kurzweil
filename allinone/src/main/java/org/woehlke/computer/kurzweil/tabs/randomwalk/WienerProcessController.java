package org.woehlke.computer.kurzweil.tabs.randomwalk;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.commons.tabs.TabController;

/**
 * Cyclic Cellular Automaton.
 *
 * (C) 2006 - 2013 Thomas Woehlke.
 * http://thomas-woehlke.de/p/cyclic-cellular-automaton/
 * @author Thomas Woehlke
 *
 * Date: 05.02.2006
 * Time: 00:36:20
 */
@Log
@Getter
public class WienerProcessController extends Thread
        implements TabController {

    private static final int THREAD_SLEEP_TIME = 100;
    private static final long serialVersionUID = 3642865135701767557L;
    private final ComputerKurzweilApplicationContext ctx;
    private final WienerProcessContext appCtx;
    private final int threadSleepTime;

    private Boolean goOn;

    public WienerProcessController(
        WienerProcessContext appCtx
    ) {
        super("CCA-Controller");
        this.appCtx = appCtx;
        this.ctx = appCtx.getCtx();
        this.goOn = Boolean.TRUE;
        this.threadSleepTime = THREAD_SLEEP_TIME;
    }

    public void run() {
        log.info("run() - begin");
        boolean doIt;
        do {
            synchronized (this.goOn) {
                doIt = goOn.booleanValue();
            }
            synchronized (this.appCtx) {
                //log.info("running");
                this.appCtx.getStepper().step();
                this.appCtx.getCanvas().update();
                this.appCtx.getTab().repaint();
            }
            try { super.sleep( this.threadSleepTime ); }
            catch (InterruptedException e) { log.info(e.getMessage()); }
        }
        while (doIt);
        log.info("run() - finished");
    }

    public void exit() {
        log.info("exit");
        try {
            synchronized (goOn) {
                goOn = Boolean.FALSE;
            }
            log.info("join");
            join();
            log.info("joined");
        } catch (InterruptedException e){
            log.info(e.getMessage());
        }
        log.info("exited");
    }

}