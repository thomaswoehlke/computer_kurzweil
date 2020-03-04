package org.woehlke.computer.kurzweil.tabs.kochsnowflake;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.commons.gui.GuiComponent;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;

@Log4j2
@Getter
@ToString(callSuper = true, exclude = {"buttonVonNeumann","buttonMoore","buttonWoehlke"})
@EqualsAndHashCode(callSuper=true, exclude = {"buttonVonNeumann","buttonMoore","buttonWoehlke"})
public class KochSnowflakeButtons extends JPanel implements GuiComponent {

  private final JButton buttonVonNeumann;
  private final JButton buttonMoore;
  private final JButton buttonWoehlke;
  private final String buttonLabelVonNeumann;
  private final String buttonLabelMoore;
  private final String buttonLabelWoehlke;
  private final String title;
  private final KochSnowflakeCanvas canvas;
  private final CompoundBorder border;
  private final FlowLayout layout;

  public KochSnowflakeButtons(
      KochSnowflakeCanvas canvas
  ) {
    this.canvas = canvas;
    this.title = this.canvas.getTabCtx().getCtx().getProperties().getKochsnowflake().getView().getNeighborhood().getTitle();
    this.buttonLabelVonNeumann = this.canvas.getTabCtx().getCtx().getProperties().getKochsnowflake().getView().getNeighborhood().getTypeVonNeumann();
    this.buttonLabelMoore =this.canvas.getTabCtx().getCtx().getProperties().getKochsnowflake().getView().getNeighborhood().getTypeMoore();
    this.buttonLabelWoehlke =this.canvas.getTabCtx().getCtx().getProperties().getKochsnowflake().getView().getNeighborhood().getTypeWoehlke();
    this.buttonVonNeumann = new JButton(this.buttonLabelVonNeumann);
    this.buttonMoore = new JButton(this.buttonLabelMoore );
    this.buttonWoehlke = new JButton(this.buttonLabelWoehlke);
    this.border = this.canvas.getTabCtx().getCtx().getBottomButtonsPanelBorder(  this.title);
    this.layout = new FlowLayout();
    this.setBorder(border);
    this.setLayout(layout);
    this.add(this.buttonVonNeumann);
    this.add(this.buttonMoore);
    this.add(this.buttonWoehlke);
    showMe();
  }

    @Override
    public void showMe() {
        //log.info("showMe");
    }

}