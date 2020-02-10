package org.woehlke.computer.kurzweil.apps.dla;

import lombok.Delegate;
import lombok.Getter;
import lombok.Setter;
import org.woehlke.computer.kurzweil.apps.AppType;
import org.woehlke.computer.kurzweil.commons.AppContext;
import org.woehlke.computer.kurzweil.commons.ControllerThread;
import org.woehlke.computer.kurzweil.commons.Stepper;
import org.woehlke.computer.kurzweil.trashcan.signals.SignalSlotDispatcher;
import org.woehlke.computer.kurzweil.trashcan.signals.SignalSlotDispatcherImpl;
import org.woehlke.computer.kurzweil.tabs.DiffusionLimitedAggregationTab;
import org.woehlke.computer.kurzweil.tabs.common.TabPanel;

public class DiffusionLimitedAggregationContext implements AppContext {

    @Getter @Setter
    private DiffusionLimitedAggregationControllerThread diffusionLimitedAggregationControllerThread;

    @Getter @Setter
    private DiffusionLimitedAggregationTab diffusionLimitedAggregationTab;

    @Getter @Setter
    private DiffusionLimitedAggregationWorld diffusionLimitedAggregationWorld;

    @Delegate
    @Getter
    private final SignalSlotDispatcher signalSlotDispatcher;

    public DiffusionLimitedAggregationContext() {
        this.signalSlotDispatcher = new SignalSlotDispatcherImpl();
    }

    @Override
    public ControllerThread getControllerThread() {
        return diffusionLimitedAggregationControllerThread;
    }

    @Override
    public TabPanel getTabPanel() {
        return diffusionLimitedAggregationTab;
    }

    @Override
    public AppType getAppType() {
        return AppType.DIFFUSION_LIMITED_AGGREGATION;
    }

    @Override
    public Stepper getStepper() {
        return diffusionLimitedAggregationWorld;
    }
}