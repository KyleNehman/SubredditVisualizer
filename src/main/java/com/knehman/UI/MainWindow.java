package com.knehman.UI;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private JPanel panel;

    public MainWindow(String title, int xSize, int ySize) {
        int[] screenCenter = getScreenCenter();
        this.panel = new JPanel();

        int xLoc = screenCenter[0] - (xSize / 2);
        int yLoc = screenCenter[1] - (ySize / 2);

        setTitle(title);
        setLocation(xLoc, yLoc);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        initElements();
        setResizable(false);
        setContentPane(panel);
        pack();
    }

    private void initElements() {
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);


        // Define layout components
        JLabel subredditFieldLabel = new JLabel("Enter a subreddit: ");
        JTextField subredditField = new JTextField("WorldNews", 20);
        JButton toggleButton = new JButton("Click to toggle");
        JLabel toggleLabel = new JLabel("Toggled text!");


        // Set component properties
        subredditField.setCaretPosition(subredditField.getText().length());
        toggleLabel.setVisible(false);

        // Component Listeners
        toggleButton.addActionListener(e -> toggleLabel.setVisible(!toggleLabel.isVisible()));
        subredditField.addActionListener(e -> System.out.println(subredditField.getText()));


        // Layout groupings / add components
        layout.setHorizontalGroup(layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addComponent(subredditFieldLabel)
                    .addComponent(subredditField))
                .addGroup(layout.createSequentialGroup()
                    .addComponent(toggleButton)
                    .addComponent(toggleLabel)));

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(subredditField)
                    .addComponent(subredditFieldLabel))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(toggleButton)
                    .addComponent(toggleLabel)));
    }

    private static int[] getScreenCenter() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int resolution[] = new int[2];

        resolution[0] = (int) screenSize.getWidth() / 2;
        resolution[1] = (int) screenSize.getHeight() / 2;

        return resolution;
    }
}