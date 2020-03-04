package org.woehlke.computer.kurzweil.tabs.todo.wator;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.commons.widgets.PanelBottomButtons;
import org.woehlke.computer.kurzweil.tabs.ComputerKurzweilTabbedPane;
import org.woehlke.computer.kurzweil.tabs.Tab;
import org.woehlke.computer.kurzweil.tabs.TabPanel;
import org.woehlke.computer.kurzweil.tabs.todo.wator.canvas.WaTorButtons;

import java.awt.event.ActionEvent;

@Log4j2
@Getter
@ToString(callSuper = true, exclude = {"tabCtx"})
@EqualsAndHashCode(callSuper=true, exclude = {"tabCtx"})
public class WaTorTab extends TabPanel implements Tab, WaTor {

    private final WaTorContext tabCtx;
    private final WaTorCanvas canvas;

    private final WaTorButtons neighbourhoodButtonsPanel;
    private final PanelBottomButtons bottomButtonsPanel;

    public WaTorTab(ComputerKurzweilTabbedPane tabbedPane) {
        super(tabbedPane, TAB_TYPE);
        this.tabCtx = new WaTorContext(this);
        this.canvas = this.tabCtx.getCanvas();
        this.neighbourhoodButtonsPanel = new WaTorButtons(this.canvas);
        this.bottomButtonsPanel = new PanelBottomButtons( this );
        this.bottomButtonsPanel.add(this.neighbourhoodButtonsPanel);
        this.add(this.panelSubtitle);
        this.add(this.canvas);
        this.add(this.bottomButtonsPanel);
        this.neighbourhoodButtonsPanel.getButtonVonNeumann().addActionListener(this);
        this.neighbourhoodButtonsPanel.getButtonMoore().addActionListener(this);
        this.neighbourhoodButtonsPanel.getButtonWoehlke().addActionListener(this);
        this.bottomButtonsPanel.getStartButton().addActionListener(this);
        this.bottomButtonsPanel.getStopButton().addActionListener(this);
        this.bottomButtonsPanel.getStartStopButtonsPanel().stop();
        this.ctx.getFrame().pack();
        showMe();
    }

    @Override
    public void start() {
        log.info("start");
        this.showMe();
        this.canvas.start();
        this.bottomButtonsPanel.getStartStopButtonsPanel().start();
        this.getTabCtx().startController();
        this.getTabCtx().getController().start();
        this.ctx.getFrame().pack();
        int x = this.canvas.getWidth();
        int y = this.canvas.getHeight();
        log.info("started with canvas x="+x+" y="+y);
    }

    @Override
    public void stop() {
        log.info("stop");
        this.canvas.stop();
        this.bottomButtonsPanel.getStartStopButtonsPanel().stop();
        this.getTabCtx().stopController();
        int x = this.canvas.getWidth();
        int y = this.canvas.getHeight();
        log.info("stopped with canvas x="+x+" y="+y);
    }

    @Override
    public void showMe() {
        log.info("showMe");
        int x = this.canvas.getWidth();
        int y = this.canvas.getHeight();
        log.info("showMe with canvas x="+x+" y="+y+" this: "+this.toString());
    }

    @Override
    public String getTitle() {
        return ctx.getProperties().getCca().getView().getTitle();
    }

    @Override
    public String getSubTitle() {
        return ctx.getProperties().getCca().getView().getSubtitle();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() ==  this.neighbourhoodButtonsPanel.getButtonVonNeumann()) {
            this.canvas.startWithNeighbourhoodVonNeumann();
            this.start();
        } else if (ae.getSource() ==  this.neighbourhoodButtonsPanel.getButtonMoore()) {
            this.canvas.startWithNeighbourhoodMoore();
            this.start();
        } else if (ae.getSource() ==  this.neighbourhoodButtonsPanel.getButtonWoehlke()) {
            this.canvas.startWithNeighbourhoodWoehlke();
            this.start();
        }
        if(ae.getSource() == this.bottomButtonsPanel.getStartButton()){
            super.ctx.getFrame().start();
        }
        if(ae.getSource() == this.bottomButtonsPanel.getStopButton()){
            super.ctx.getFrame().stop();
        }
    }
}