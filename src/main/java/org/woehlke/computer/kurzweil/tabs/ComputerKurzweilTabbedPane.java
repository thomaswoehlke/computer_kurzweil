package org.woehlke.computer.kurzweil.tabs;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilContext;
import org.woehlke.computer.kurzweil.commons.gui.GuiComponent;
import org.woehlke.computer.kurzweil.commons.Startable;
import org.woehlke.computer.kurzweil.tabs.cca.CyclicCellularAutomatonTab;
import org.woehlke.computer.kurzweil.tabs.dla.DiffusionLimitedAggregationTab;
import org.woehlke.computer.kurzweil.tabs.mandelbrot.MandelbrotTab;
import org.woehlke.computer.kurzweil.tabs.evolution.SimulatedEvolutionTab;
import org.woehlke.computer.kurzweil.tabs.randomwalk.RandomWalkTab;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Getter
@ToString(exclude={"ctx","apps","border"},callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class ComputerKurzweilTabbedPane extends JTabbedPane implements Startable,
    GuiComponent {

    private final ComputerKurzweilContext ctx;
    private final CyclicCellularAutomatonTab cyclicCellularAutomatonTab;
    private final RandomWalkTab wienerProcessTab;
    private final DiffusionLimitedAggregationTab diffusionLimitedAggregationTab;
    private final MandelbrotTab mandelbrotTab;
    private final SimulatedEvolutionTab simulatedEvolutionTab;
    private final List<TabPanel> apps = new ArrayList<>();
    private final CompoundBorder border;

    public ComputerKurzweilTabbedPane(
        ComputerKurzweilContext ctx
    ) {
        this.ctx = ctx;
        this.border = ctx.getTabbedPaneBorder();
        this.setBorder(border);
        this.cyclicCellularAutomatonTab = new CyclicCellularAutomatonTab(this);
        this.wienerProcessTab = new RandomWalkTab(this);
        this.diffusionLimitedAggregationTab = new DiffusionLimitedAggregationTab(this);
        this.mandelbrotTab = new MandelbrotTab(this);
        this.simulatedEvolutionTab = new SimulatedEvolutionTab(this);
        TabPanel[] tabPanelAbstractPanels = {
            this.cyclicCellularAutomatonTab,
            this.wienerProcessTab,
            this.diffusionLimitedAggregationTab,
            this.simulatedEvolutionTab,
            this.mandelbrotTab
        };
        int[] events = {
            KeyEvent.VK_1,
            KeyEvent.VK_2,
            KeyEvent.VK_3,
            KeyEvent.VK_4,
            KeyEvent.VK_5
        };
        int i = 0;
        ImageIcon icon = null;
        for(TabPanel tabPanelAbstract : tabPanelAbstractPanels){
            this.apps.add(tabPanelAbstract);
            this.addTab(tabPanelAbstract.getTitle(), icon, tabPanelAbstract, tabPanelAbstract.getSubTitle());
            this.setMnemonicAt(i,events[i]);
        }
        //TODO: change tab lister:
        //this.addAncestorListener(this);
    }

    public Tab getActiveTab(){
        Component c = this.getSelectedComponent();
        if(c instanceof Tab){
            return (Tab)c;
        } else {
            return null;
        }
    }

    @Override
    public void start(){
        log.info("start");
        for(TabPanel tabPanel:apps){
            tabPanel.stop();
        }
        getActiveTab().start();
        log.info("started");
    }

    @Override
    public void stop() {
        log.info("stop");
        getActiveTab().stop();
        log.info("stopped");
    }

    @Override
    public void showMe() {
        for(TabPanel tabPanel:apps){
            tabPanel.showMe();
        }
    }
}