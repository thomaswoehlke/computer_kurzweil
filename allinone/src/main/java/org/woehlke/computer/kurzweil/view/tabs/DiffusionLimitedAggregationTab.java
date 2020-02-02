package org.woehlke.computer.kurzweil.view.tabs;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.apps.dla.ctx.DiffusionLimitedAggregationContext;
import org.woehlke.computer.kurzweil.ctx.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.control.signals.UserSignal;
import org.woehlke.computer.kurzweil.control.commons.Startable;
import org.woehlke.computer.kurzweil.control.commons.AppGuiComponent;
import org.woehlke.computer.kurzweil.view.apps.DiffusionLimitedAggregationTabApp;
import org.woehlke.computer.kurzweil.view.tabs.common.TabPanel;

import javax.accessibility.Accessible;
import java.awt.image.ImageObserver;
import java.io.Serializable;

@Log
public class DiffusionLimitedAggregationTab extends TabPanel implements ImageObserver,
    Serializable,
    Accessible, Startable, AppGuiComponent {

    @Getter
    private final DiffusionLimitedAggregationTabApp app;

    @Getter
    private final DiffusionLimitedAggregationContext appCtx;

    public DiffusionLimitedAggregationTab(ComputerKurzweilApplicationContext ctx) {
        super(ctx,ctx.getProperties().getDla().getView().getSubtitle());
        this.appCtx = new DiffusionLimitedAggregationContext();
        this.app = new DiffusionLimitedAggregationTabApp(this);
        this.add(this.panelSubtitle);
        this.add(this.app);
        this.add(this.startStopButtonsPanel);
    }

    @Override
    public void start() {
        this.app.start();
    }

    @Override
    public void stop() {
        this.app.stop();
    }

    @Override
    public void update() {

    }

    @Override
    public void showMe() {

    }

    @Override
    public void hideMe() {

    }

    @Override
    public void handleUserSignal(UserSignal userSignal) {
        log.info("handleUserSignal: "+userSignal.name());
    }

}
