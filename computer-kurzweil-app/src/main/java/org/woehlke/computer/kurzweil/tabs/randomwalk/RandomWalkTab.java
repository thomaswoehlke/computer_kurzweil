package org.woehlke.computer.kurzweil.tabs.randomwalk;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.tabs.ComputerKurzweilTabbedPane;
import org.woehlke.computer.kurzweil.tabs.Tab;
import org.woehlke.computer.kurzweil.tabs.TabPanel;

import java.awt.event.ActionEvent;

@Log4j2
@Getter
@ToString(callSuper = true, exclude = {"tabCtx"})
@EqualsAndHashCode(callSuper=true, exclude = {"tabCtx"})
public class RandomWalkTab extends TabPanel implements Tab, RandomWalk {

    private final RandomWalkContext tabCtx;

    private final RandomWalkCanvas canvas;
    private final RandomWalkModel tabModel;
    private final RandomWalkTabPane randomWalkTabPane;

    public RandomWalkTab(ComputerKurzweilTabbedPane tabbedPane) {
        super(tabbedPane, TAB_TYPE);
        this.tabCtx = new RandomWalkContext(this);
        this.canvas = this.tabCtx.getCanvas();
        this.tabModel = this.canvas.getTabModel();
        this.randomWalkTabPane = new RandomWalkTabPane( this );
        this.add(this.panelSubtitle);
        this.add(this.canvas);
        this.add(this.randomWalkTabPane);
        this.randomWalkTabPane.getStartButton().addActionListener(this);
        this.randomWalkTabPane.getStopButton().addActionListener(this);
        this.tabModel.stop();
        this.randomWalkTabPane.stop();
        this.ctx.getFrame().pack();
    }

    @Override
    public void start() {
        log.info("start");
        this.tabModel.start();
        this.randomWalkTabPane.start();
        this.getTabCtx().stopController();
        this.getTabCtx().getController().start();
        this.ctx.getFrame().pack();
        int x = this.canvas.getWidth();
        int y = this.canvas.getHeight();
        log.info("started with canvas x="+x+" y="+y);
    }

    @Override
    public void stop() {
        log.info("stop");
        this.tabModel.stop();
        this.randomWalkTabPane.stop();
        this.getTabCtx().stopController();
        int x = this.canvas.getWidth();
        int y = this.canvas.getHeight();
        log.info("stopped with canvas x="+x+" y="+y);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == this.randomWalkTabPane.getStartButton()){
            super.ctx.getFrame().start();
        }
        if(ae.getSource() == this.randomWalkTabPane.getStopButton()){
            super.ctx.getFrame().stop();
        }
    }

}
