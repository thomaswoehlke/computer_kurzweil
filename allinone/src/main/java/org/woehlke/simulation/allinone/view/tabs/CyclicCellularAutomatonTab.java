package org.woehlke.simulation.allinone.view.tabs;


import lombok.Getter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.woehlke.simulation.allinone.model.ComputerKurzweilApplicationContext;
import org.woehlke.simulation.allinone.view.parts.FramePanel;
import org.woehlke.simulation.allinone.view.parts.PanelBorder;
import org.woehlke.simulation.allinone.view.parts.PanelSubtitle;
import org.woehlke.simulation.allinone.view.parts.StartStopPanel;
import org.woehlke.simulation.cca.view.CyclicCellularAutomatonFrame;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Log
@Component
public class CyclicCellularAutomatonTab extends JPanel implements ActionListener {

    @Getter
    private final ComputerKurzweilApplicationContext ctx;

    @Getter
    private final StartStopPanel startStopPanel;

    @Getter
    private final FramePanel framePanel;

    @Getter
    private final CyclicCellularAutomatonFrame cyclicCellularAutomatonFrame;

    @Autowired
    public CyclicCellularAutomatonTab(ComputerKurzweilApplicationContext ctx) {
        CompoundBorder border = PanelBorder.getBorder();
        this.setBorder(border);
        this.ctx = ctx;
        this.cyclicCellularAutomatonFrame = new CyclicCellularAutomatonFrame( this.ctx );
        PanelSubtitle panelSubtitle = new PanelSubtitle(
            ctx.getProperties().getCca().getView().getSubtitle()
        );
        this.startStopPanel = new StartStopPanel(this);
        this.framePanel = new FramePanel(this);
        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(layout);
        this.add(panelSubtitle);
        this.add(this.cyclicCellularAutomatonFrame);
        this.add(this.framePanel);
        this.add(this.startStopPanel);
    }

    public void start(){
        log.info("started");
    }

    public void stop(){
        log.info("stopped");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.startStopPanel.getStartButton()){
            this.startStopPanel.getStartButton().setEnabled(false);
            this.startStopPanel.getStopButton().setEnabled(true);
            this.start();
        }
        if(e.getSource() == this.startStopPanel.getStopButton()){
            this.startStopPanel.getStartButton().setEnabled(true);
            this.startStopPanel.getStopButton().setEnabled(false);
            this.stop();
        }
    }
}
