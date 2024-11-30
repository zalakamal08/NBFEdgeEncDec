package com.mycompany.nbfedgeencdec;
 
 import burp.api.montoya.MontoyaApi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NicoEncDecUI {
    private final JPanel panel;
    private final JTextField keyField;

    public NicoEncDecUI(MontoyaApi api) {
        panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding around the edges

        JLabel label = new JLabel("Enter Encryption Key:");
        keyField = new JTextField(30); // Text field for key input
        JButton saveButton = new JButton("Save Key");

        // Save button logic
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NicoEncDec.KEY = keyField.getText();
                api.logging().logToOutput("Encryption key updated: " + NicoEncDec.KEY);
                 
            }
        });

        // Align UI components to the left
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        inputPanel.add(label);
        inputPanel.add(keyField);
        inputPanel.add(saveButton);

        panel.add(inputPanel, BorderLayout.NORTH);
    }

    public JPanel getPanel() {
        return panel;
    }
}
