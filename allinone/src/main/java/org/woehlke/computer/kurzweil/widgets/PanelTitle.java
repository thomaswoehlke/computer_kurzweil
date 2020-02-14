package org.woehlke.computer.kurzweil.widgets;


import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.commons.GuiComponentTab;
import org.woehlke.computer.kurzweil.widgets.layouts.PanelSubtitleLayout;

import javax.swing.*;

@Log
@ToString
@EqualsAndHashCode(callSuper=true)
public class PanelTitle extends JPanel implements GuiComponentTab {

    public PanelTitle(String text) {
        this.setLayout(new PanelSubtitleLayout());
        this.add(new JLabel(text));
    }

    @Override
    public void showMe() {
        this.setVisible(true);
    }

    public static PanelTitle getPanelTitleForApplication(ComputerKurzweilApplicationContext ctx) {
        String text = ctx.getProperties().getAllinone().getView().getTitle();
        return new PanelTitle(text);
    }

    public static PanelTitle getPanelTitleForCca(ComputerKurzweilApplicationContext ctx) {
        String text = ctx.getProperties().getCca().getView().getTitle();
        return new PanelTitle(text);
    }

    public static PanelTitle getPanelTitleForDla(ComputerKurzweilApplicationContext ctx) {
        String text = ctx.getProperties().getDla().getView().getTitle();
        return new PanelTitle(text);
    }

    public static PanelTitle getPanelTitleForSimulatedEvolution(ComputerKurzweilApplicationContext ctx) {
        String text = ctx.getProperties().getEvolution().getView().getTitle();
        return new PanelTitle(text);
    }

    public static PanelTitle getPanelTitleForSimulatedKochSnowflake(ComputerKurzweilApplicationContext ctx) {
        String text = ctx.getProperties().getKochsnowflake().getView().getTitle();
        return new PanelTitle(text);
    }

    public static PanelTitle getPanelTitleForMandelbrot(ComputerKurzweilApplicationContext ctx) {
        String text = ctx.getProperties().getMandelbrot().getView().getTitle();
        return new PanelTitle(text);
    }


    public static PanelTitle getPanelTitleForSameGame(ComputerKurzweilApplicationContext ctx) {
        String text = ctx.getProperties().getSamegame().getView().getTitle();
        return new PanelTitle(text);
    }

    public static PanelTitle getPanelTitleForSierpinskiTriangle(ComputerKurzweilApplicationContext ctx) {
        String text = ctx.getProperties().getSierpinskitriangle().getView().getTitle();
        return new PanelTitle(text);
    }

    public static PanelTitle getPanelTitleForSierpinskiTetris(ComputerKurzweilApplicationContext ctx) {
        String text = ctx.getProperties().getTetris().getView().getTitle();
        return new PanelTitle(text);
    }

    public static PanelTitle getPanelTitleForSierpinskiTurmite(ComputerKurzweilApplicationContext ctx) {
        String text = ctx.getProperties().getTurmite().getView().getTitle();
        return new PanelTitle(text);
    }

    public static PanelTitle getPanelTitleForSierpinskiWaTor(ComputerKurzweilApplicationContext ctx) {
        String text = ctx.getProperties().getWator().getView().getTitle();
        return new PanelTitle(text);
    }
}