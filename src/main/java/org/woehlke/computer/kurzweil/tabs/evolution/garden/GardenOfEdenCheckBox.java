package org.woehlke.computer.kurzweil.tabs.evolution.garden;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.tabs.evolution.SimulatedEvolutionContext;

import javax.swing.*;

@Log4j2
@Getter
@ToString(callSuper = true)
public class GardenOfEdenCheckBox extends JCheckBox {

    @ToString.Exclude
    private final SimulatedEvolutionContext tabCtx;
    private final String gardenOfEdenEnabledString;
    private final boolean gardenOfEdenEnabledSelected;

    public GardenOfEdenCheckBox(SimulatedEvolutionContext tabCtx) {
        super(
            tabCtx.getCtx().getProperties().getEvolution().getGardenOfEden().getGardenOfEdenEnabledString(),
            tabCtx.getCtx().getProperties().getEvolution().getGardenOfEden().getGardenOfEdenEnabled()
        );
        this.tabCtx = tabCtx;
        this.gardenOfEdenEnabledSelected = tabCtx.getCtx().getProperties().getEvolution().getGardenOfEden().getGardenOfEdenEnabled();
        this.gardenOfEdenEnabledString = tabCtx.getCtx().getProperties().getEvolution().getGardenOfEden().getGardenOfEdenEnabledString();
    }
}