package org.woehlke.simulation.mandelbrot.view;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.simulation.allinone.view.PanelBorder;
import org.woehlke.simulation.mandelbrot.config.MandelbrotProperties;
import org.woehlke.simulation.mandelbrot.model.MandelbrotContext;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;

import static org.woehlke.simulation.mandelbrot.model.state.RadioButtons.RADIO_BUTTONS_SWITCH;
import static org.woehlke.simulation.mandelbrot.model.state.RadioButtons.RADIO_BUTTONS_ZOOM;


/**
 * Mandelbrot Set drawn by a Turing Machine.
 *
 * (C) 2006 - 2015 Thomas Woehlke.
 * https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html
 * @author Thomas Woehlke
 *
 * Created by tw on 16.12.2019.
 */
@Log
public class MandelbrotPanelButtons extends JPanel {

    @Getter private JRadioButton radioButtonsSwitch;
    @Getter private JRadioButton radioButtonsZoom;
    @Getter private JButton zoomOutButton;

    private ButtonGroup radioButtonsGroup;
    private JPanel panelButtonsGroup;
    private JPanel panelZoomButtons;
    private TextField zoomLevelField;

    private final MandelbrotContext ctx;

    public MandelbrotPanelButtons(MandelbrotContext ctx) {
        this.ctx = ctx;
        init(ctx.getProperties());
        this.radioButtonsSwitch.addActionListener(this.ctx);
        this.radioButtonsZoom.addActionListener(this.ctx);
        this.zoomOutButton.addActionListener(this.ctx);
    }

    private void init(MandelbrotProperties properties){
        FlowLayout layout = new FlowLayout();
        this.radioButtonsSwitch = new JRadioButton(properties.getButtonsSwitch());
        this.radioButtonsSwitch.setMnemonic(RADIO_BUTTONS_SWITCH.ordinal());
        this.radioButtonsZoom = new JRadioButton(properties.getButtonsZoom());
        this.radioButtonsZoom.setMnemonic(RADIO_BUTTONS_ZOOM.ordinal());
        this.radioButtonsGroup = new ButtonGroup();
        this.radioButtonsGroup.add(radioButtonsSwitch);
        this.radioButtonsGroup.add(radioButtonsZoom);
        this.zoomOutButton = new JButton(properties.getButtonsZoomOut());
        this.panelButtonsGroup = new JPanel();
        CompoundBorder borderPanelRadioButtons = PanelBorder.getBorder(properties.getButtonsLabel());
        CompoundBorder borderPanelPushButtons = PanelBorder.getBorder(properties.getButtonsZoomLabel());
        JLabel zoomLevelFieldLabel = new JLabel("Zoom Level");
        zoomLevelField = new TextField("1",3);
        //zoomLevelField.setText("1");
        panelButtonsGroup.setLayout(layout);
        panelButtonsGroup.setBorder(borderPanelRadioButtons);
        panelButtonsGroup.add(radioButtonsSwitch);
        panelButtonsGroup.add(radioButtonsZoom);
        this.panelZoomButtons = new JPanel();
        this.panelZoomButtons.setLayout(layout);
        this.panelZoomButtons.setBorder(borderPanelPushButtons);
        this.panelZoomButtons.add(zoomLevelFieldLabel);
        this.panelZoomButtons.add(this.zoomLevelField);
        this.panelZoomButtons.add(this.zoomOutButton);
        this.setLayout(layout);
        this.add(panelButtonsGroup);
        this.add(panelZoomButtons);
        this.radioButtonsSwitch.setSelected( true );
        this.disableZoomButton();
    }

    @Override
    public void repaint() {
        if(this.zoomLevelField == null){
            zoomLevelField = new TextField("1");
        }
        String text;
        try {
            text = ctx.getZoomLevel();
        } catch (NullPointerException e){
            text = "ERR";
        }
        this.zoomLevelField.setText(text);
        this.zoomLevelField.repaint();
        super.repaint();
    }

    public void enableZoomButton() {
        this.panelZoomButtons.setEnabled(false);
    }

    public void disableZoomButton() {
        this.panelZoomButtons.setEnabled(true);
    }
}
