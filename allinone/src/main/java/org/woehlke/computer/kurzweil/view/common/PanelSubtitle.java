package org.woehlke.computer.kurzweil.view.common;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.config.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.control.startables.Startable;

import javax.swing.*;


@Log
@ToString
@EqualsAndHashCode(callSuper=true)
public class PanelSubtitle extends JPanel implements Startable {

    public PanelSubtitle(String text) {
        this.setLayout(new PanelSubtitleLayout());
        this.add(new JLabel(text));
    }

    public static PanelSubtitle getPanelSubtitleForAllinone(ComputerKurzweilApplicationContext ctx) {
        String text = ctx.getProperties().getAllinone().getView().getSubtitle();
        return new PanelSubtitle(text);
    }

    public static PanelSubtitle getPanelSubtitleForCca(ComputerKurzweilApplicationContext ctx) {
        String text = ctx.getProperties().getCca().getView().getSubtitle();
        return new PanelSubtitle(text);
    }

    public static PanelSubtitle getPanelSubtitleForDla(ComputerKurzweilApplicationContext ctx) {
        String text = ctx.getProperties().getDla().getView().getSubtitle();
        return new PanelSubtitle(text);
    }

    public static PanelSubtitle getPanelSubtitleForSimulatedEvolution(ComputerKurzweilApplicationContext ctx) {
        String text = ctx.getProperties().getEvolution().getView().getSubtitle();
        return new PanelSubtitle(text);
    }

    public static PanelSubtitle getPanelSubtitleForMandelbrot(ComputerKurzweilApplicationContext ctx) {
        String text = ctx.getProperties().getMandelbrot().getView().getSubtitle();
        return new PanelSubtitle(text);
    }

    @Override
    public void start() {
        this.setVisible(true);
        this.repaint();
    }

    public void stop() {
        this.setVisible(false);
    }

    @Override
    public void update() {
        this.setVisible(true);
        this.repaint();
    }
}
