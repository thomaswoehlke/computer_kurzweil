package org.woehlke.computer.kurzweil.application;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.commons.Startable;
import org.woehlke.computer.kurzweil.commons.tabs.TabPanel;
import org.woehlke.computer.kurzweil.model.LatticePoint;
import org.woehlke.computer.kurzweil.tabs.evolution.model.CellCore;
import org.woehlke.computer.kurzweil.tabs.evolution.model.CellLifeCycle;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.beans.Transient;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Log
@Getter
@ToString(exclude={"random"})
public class ComputerKurzweilApplicationContext implements Startable {

    private final ComputerKurzweilProperties properties;
    private final Random random;
    private ComputerKurzweilApplicationFrame frame;

    public ComputerKurzweilApplicationContext(
        ComputerKurzweilProperties computerKurzweilProperties,
        ComputerKurzweilApplicationFrame frame
    ) {
        this.frame = frame;
        this.properties = computerKurzweilProperties;
        long seed = new Date().getTime();
        this.random = new Random(seed);
    }

    public List<TabPanel> getApps(){
        return frame.getApps();
    }

    @Transient
    public void exit() {
        System.exit(0);
    }

    @Transient
    public CompoundBorder getBorder(){
        int left = this.getProperties().getAllinone().getView().getBorderPaddingX();
        int right = this.getProperties().getAllinone().getView().getBorderPaddingX();
        int top = this.getProperties().getAllinone().getView().getBorderPaddingY();
        int bottom = this.getProperties().getAllinone().getView().getBorderPaddingY();
        return BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(),
            //BorderFactory.createEmptyBorder(top,left,bottom,right),
            BorderFactory.createEmptyBorder(top,left,bottom,right)
        );
    }

    @Transient
    public CompoundBorder getBorder(String label){
        int top = this.getProperties().getAllinone().getView().getBorderPaddingY();
        int left = this.getProperties().getAllinone().getView().getBorderPaddingX();
        int bottom = this.getProperties().getAllinone().getView().getBorderPaddingY();
        int right = this.getProperties().getAllinone().getView().getBorderPaddingX();
        return BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(label),
            BorderFactory.createEmptyBorder(top,left,bottom,right)
        );
    }

    @Transient
    public Rectangle getFrameBounds(){
        int x = this.properties.getAllinone().getLattice().getWidth();
        int y = this.properties.getAllinone().getLattice().getHeight();
        int titleHeight = this.properties.getAllinone().getView().getTitleHeight();
        double twoOfFiveParts = 2d;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double startX = (screenSize.getWidth() - x ) / twoOfFiveParts;
        double startY = (screenSize.getHeight() - y) / twoOfFiveParts;
        int myheight = Double.valueOf( y ).intValue() + titleHeight;
        int mywidth = Double.valueOf( x ).intValue();
        int mystartX = Double.valueOf( startX ).intValue();
        int mystartY = Double.valueOf( startY ).intValue();
        return new Rectangle( mystartX, mystartY, mywidth, myheight );
    }

    /*
    @Transient
    public Rectangle getCanvasBounds(){
        int start=0;
        return new Rectangle(
            start, start,
            this.getWorldDimensions().getX(),
            this.getWorldDimensions().getY()
        );
    }


    @Transient
    public Dimension getLatticeDimension(){
        return new Dimension(
            this.getWorldDimensions().getX(),
            this.getWorldDimensions().getY()
        );
    }

    @Transient
    public Point getLatticeDimensions(){
        return new Point(
            this.getWorldDimensions().getX(),
            this.getWorldDimensions().getY()
        );
    }
    */

    @Transient
    public LatticePoint getWorldDimensions(){
        int x = this.properties.getAllinone().getLattice().getWidth();
        int y = this.properties.getAllinone().getLattice().getHeight();
        return new LatticePoint(x,y);
    }

    @Transient
    public LatticePoint getNextRandomLatticePoint() {
        int x = this.properties.getAllinone().getLattice().getWidth();
        int y = this.properties.getAllinone().getLattice().getHeight();
        int nextX = this.getRandom().nextInt(x);
        int nextY = this.getRandom().nextInt(y);
        LatticePoint p = new LatticePoint(nextX,nextY);
        p.normalize(this.getWorldDimensions());
        p.absoluteValue();
        return p;
    }

    @Transient
    public CellLifeCycle getNewCellLifeCycle() {
        return new CellLifeCycle(this.properties.getEvolution().getCellConf());
    }

    @Transient
    public CellCore getNewCellCore() {
        return new CellCore(this);
    }

    @Override
    public void start() {
        frame.start();
    }

    @Override
    public void stop() {
        frame.stop();
    }
}
