package org.woehlke.simulation.cca.view;

import org.woehlke.simulation.cca.config.ObjectRegistry;

import javax.swing.*;
import java.awt.*;

/**
 * TODO write doc.
 */
public class PanelSubtitle extends JPanel {

  private final JLabel subtitleLabel;

  public PanelSubtitle(ObjectRegistry ctx) {
    this.subtitleLabel = new JLabel(ctx.getConfig().getSubtitle());
    FlowLayout layout = new FlowLayout();
    this.setLayout(layout);
    this.add(subtitleLabel);
  }

}
