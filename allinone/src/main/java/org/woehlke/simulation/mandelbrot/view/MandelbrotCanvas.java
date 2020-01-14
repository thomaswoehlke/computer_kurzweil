package org.woehlke.simulation.mandelbrot.view;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.woehlke.simulation.mandelbrot.model.MandelbrotContext;

import javax.swing.*;
import java.awt.*;


/**
 * Mandelbrot Set drawn by a Turing Machine.
 *
 * (C) 2006 - 2013 Thomas Woehlke.
 * https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html
 * @author Thomas Woehlke
 *
 * Date: 05.02.2006
 * Time: 00:51:51
 */
@Log
public class MandelbrotCanvas extends JComponent {

    private final MandelbrotContext ctx;
    private final Dimension preferredSize;

    @Autowired
    public MandelbrotCanvas(MandelbrotContext ctx) {
        this.ctx = ctx;
        int width = this.ctx.getWorldDimensions().getWidth();
        int height = this.ctx.getWorldDimensions().getHeight();
        this.preferredSize = new Dimension(width, height);
        this.setSize(this.preferredSize);
        this.setPreferredSize(this.preferredSize);
    }

    @Override public void paint(Graphics g) {
        this.setSize(this.preferredSize);
        this.setPreferredSize(this.preferredSize);
        super.paintComponent(g);
        for(int y = 0; y < ctx.getWorldDimensions().getY(); y++){
            for(int x = 0; x < ctx.getWorldDimensions().getX(); x++){
                g.setColor( ctx.getCellStatusFor(x,y).canvasColor());
                g.drawLine(x,y,x,y);
            }
        }
    }

    @Override public void update(Graphics g) {
        paint(g);
    }

    public double getCanvasHeight() { return preferredSize.getHeight(); }
}
