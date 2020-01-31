package org.woehlke.computer.kurzweil.apps.cca.view;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.config.ComputerKurzweilProperties;
import org.woehlke.computer.kurzweil.config.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.model.Startable;

import javax.swing.*;
import java.awt.*;

@Log
@ToString
@EqualsAndHashCode(callSuper=true)
public class CyclicCellularAutomatonButtonsPanel extends JPanel implements Startable {

  @Getter private final JButton buttonVonNeumann;
  @Getter private final JButton buttonMoore;
  @Getter private final JButton buttonWoehlke;

  @Getter private final ComputerKurzweilApplicationContext ctx;

  public CyclicCellularAutomatonButtonsPanel(ComputerKurzweilApplicationContext ctx) {
    this.ctx = ctx;
    ComputerKurzweilProperties.Cca.View.Neighborhood neighborhoodConf = ctx.getProperties().getCca().getView().getNeighborhood();
    this.buttonVonNeumann = new JButton(neighborhoodConf.getTypeVonNeumann());
    this.buttonMoore = new JButton(neighborhoodConf.getTypeMoore());
    this.buttonWoehlke = new JButton(neighborhoodConf.getTypeWoehlke());
    this.setLayout(new FlowLayout());
    this.add(new JLabel(neighborhoodConf.getTitle()));
    this.add(this.buttonVonNeumann);
    this.add(this.buttonMoore);
    this.add(this.buttonWoehlke);
  }

    @Override
    public void start() {
        log.info("start");
    }

    @Override
    public void stop() {
        log.info("stop");
    }
}
