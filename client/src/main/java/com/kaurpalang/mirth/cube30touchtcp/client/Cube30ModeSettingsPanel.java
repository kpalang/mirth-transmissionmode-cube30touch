package com.kaurpalang.mirth.cube30touchtcp.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cube30ModeSettingsPanel extends JPanel {
    public JButton settingsButton;

    public Cube30ModeSettingsPanel(ActionListener actionListener) {
        this.initComponents();
        this.settingsButton.addActionListener(actionListener);
    }

    private void initComponents() {
        this.settingsButton = new JButton();
        this.setBackground(new Color(255, 255, 255));
        this.setMaximumSize(new Dimension(22, 22));
        this.setMinimumSize(new Dimension(22, 22));
        this.setPreferredSize(new Dimension(22, 22));
        this.settingsButton.setIcon(new ImageIcon(this.getClass().getResource("/com/mirth/connect/client/ui/images/wrench.png")));
        this.settingsButton.setMargin(new Insets(0, 0, 0, 0));
        this.settingsButton.setMaximumSize(new Dimension(22, 22));
        this.settingsButton.setMinimumSize(new Dimension(22, 22));
        this.settingsButton.setPreferredSize(new Dimension(22, 22));
        this.settingsButton.addActionListener(Cube30ModeSettingsPanel.this::settingsButtonActionPerformed);


        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.settingsButton, -2, -1, -2));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.settingsButton, -2, -1, -2));
    }

    private void settingsButtonActionPerformed(ActionEvent evt) {
    }
}
