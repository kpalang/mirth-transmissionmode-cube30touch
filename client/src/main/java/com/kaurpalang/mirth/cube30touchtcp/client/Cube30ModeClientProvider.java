package com.kaurpalang.mirth.cube30touchtcp.client;

import com.kaurpalang.mirth.cube30touchtcp.shared.Cube30ModeProperties;
import com.mirth.connect.model.transmission.TransmissionModeProperties;
import com.mirth.connect.model.transmission.framemode.FrameModeProperties;
import com.mirth.connect.plugins.FrameTransmissionModeClientProvider;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cube30ModeClientProvider extends FrameTransmissionModeClientProvider {
    protected Cube30ModeSettingsPanel settingsPanel;
    private Cube30ModeProperties cubeModeProperties;

    public Cube30ModeClientProvider() {}

    @Override
    public void initialize(ActionListener actionListener) {
        super.initialize(actionListener);
        settingsPanel = new Cube30ModeSettingsPanel(this);
        super.settingsPanel.switchComponent(this.settingsPanel);
        setProperties(new Cube30ModeProperties());
    }

    @Override
    public TransmissionModeProperties getProperties() {
        FrameModeProperties frameModeProperties = (FrameModeProperties) super.getProperties();
        cubeModeProperties.setStartOfMessageBytes(frameModeProperties.getStartOfMessageBytes());
        cubeModeProperties.setEndOfMessageBytes(frameModeProperties.getEndOfMessageBytes());

        System.out.println(
                "PROVIDER get props"
                        + "\noutgoing " + cubeModeProperties.isRejectInvalidFrames() + " " + cubeModeProperties.getLogLevel()
                        + "\n--------------------"
        );
        return cubeModeProperties;
    }

    @Override
    public TransmissionModeProperties getDefaultProperties() {
        return new Cube30ModeProperties();
    }

    @Override
    public void setProperties(TransmissionModeProperties properties) {
        super.setProperties(properties);
        if (properties instanceof Cube30ModeProperties) {
            Cube30ModeProperties props = (Cube30ModeProperties) properties;

            if (this.cubeModeProperties == null) {
                System.out.println(
                        "PROVIDER set props"
                                + "\nincoming " + props.isRejectInvalidFrames() + " " + props.getLogLevel()
                                + "\n--------------------"
                );
            } else {
                System.out.println(
                        "PROVIDER set props"
                                + "\nincoming " + props.isRejectInvalidFrames() + " " + props.getLogLevel()
                                + "\nlocal " + this.cubeModeProperties.isRejectInvalidFrames() + " " + this.cubeModeProperties.getLogLevel()
                                + "\n--------------------"
                );
            }

            this.cubeModeProperties = props;
        } else {
            this.cubeModeProperties = new Cube30ModeProperties();
            FrameModeProperties frameModeProperties = (FrameModeProperties) properties;
            this.cubeModeProperties.setStartOfMessageBytes(frameModeProperties.getStartOfMessageBytes());
            this.cubeModeProperties.setEndOfMessageBytes(frameModeProperties.getEndOfMessageBytes());
        }

        changeSampleValue();
    }

    public JComponent getSettingsComponent() {
        return this.settingsPanel;
    }

    public String getSampleLabel() {
        return "Cube30 Sample Frame:";
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        Cube30ModeSettingsDialog settingsDialog = new Cube30ModeSettingsDialog(this);
        settingsDialog.setProperties(this.cubeModeProperties);
        settingsDialog.setVisible(true);

        if (settingsDialog.isSaved()) {
            this.setProperties(settingsDialog.getProperties());
        } else {
            this.setProperties(this.cubeModeProperties);
        }
    }
}
