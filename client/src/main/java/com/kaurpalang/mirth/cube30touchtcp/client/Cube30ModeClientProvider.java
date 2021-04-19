package com.kaurpalang.mirth.cube30touchtcp.client;

import com.kaurpalang.mirth.cube30touchtcp.shared.Cube30ModeProperties;
import com.mirth.connect.model.transmission.TransmissionModeProperties;
import com.mirth.connect.model.transmission.framemode.FrameModeProperties;
import com.mirth.connect.plugins.FrameTransmissionModeClientProvider;
import com.mirth.connect.plugins.mllpmode.MLLPModeSettingsDialog;
import com.mirth.connect.plugins.mllpmode.MLLPModeSettingsPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cube30ModeClientProvider extends FrameTransmissionModeClientProvider {
    static final String CHANGE_START_BYTES_COMMAND = "changeStartBytes";
    static final String CHANGE_END_BYTES_COMMAND = "changeEndBytes";
    protected MLLPModeSettingsPanel settingsPanel;
    private Cube30ModeProperties cubeModeProperties;

    public Cube30ModeClientProvider() {
    }

    public void initialize(ActionListener actionListener) {
        super.initialize(actionListener);
        this.settingsPanel = new MLLPModeSettingsPanel(this);
        super.settingsPanel.switchComponent(this.settingsPanel);
        this.setProperties(new Cube30ModeProperties());
    }

    public TransmissionModeProperties getProperties() {
        FrameModeProperties frameModeProperties = (FrameModeProperties)super.getProperties();
        this.cubeModeProperties.setStartOfMessageBytes(frameModeProperties.getStartOfMessageBytes());
        this.cubeModeProperties.setEndOfMessageBytes(frameModeProperties.getEndOfMessageBytes());
        return this.cubeModeProperties;
    }

    public TransmissionModeProperties getDefaultProperties() {
        return new Cube30ModeProperties();
    }

    public void setProperties(TransmissionModeProperties properties) {
        super.setProperties(properties);
        if (properties instanceof Cube30ModeProperties) {
            this.cubeModeProperties = (Cube30ModeProperties)properties;
        } else {
            this.cubeModeProperties = new Cube30ModeProperties();
            FrameModeProperties frameModeProperties = (FrameModeProperties)properties;
            this.cubeModeProperties.setStartOfMessageBytes(frameModeProperties.getStartOfMessageBytes());
            this.cubeModeProperties.setEndOfMessageBytes(frameModeProperties.getEndOfMessageBytes());
        }

        this.changeSampleValue();
    }

    public JComponent getSettingsComponent() {
        return this.settingsPanel;
    }

    public String getSampleLabel() {
        return "Cube30 Sample Frame:";
    }

    public void actionPerformed(ActionEvent evt) {
        if (evt.getActionCommand().equals("changeStartBytes")) {
            super.settingsPanel.startOfMessageBytesField.setText(((JTextField)evt.getSource()).getText());
        } else if (evt.getActionCommand().equals("changeEndBytes")) {
            super.settingsPanel.endOfMessageBytesField.setText(((JTextField)evt.getSource()).getText());
        } else {
            MLLPModeSettingsDialog settingsDialog = new MLLPModeSettingsDialog(this);
            settingsDialog.setProperties(this.cubeModeProperties);
            settingsDialog.setVisible(true);
            if (settingsDialog.isSaved()) {
                this.setProperties(settingsDialog.getProperties());
            } else {
                this.setProperties(this.cubeModeProperties);
            }
        }

    }
}
