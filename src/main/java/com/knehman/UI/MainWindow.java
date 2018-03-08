package com.knehman.UI;

import com.knehman.Utils.JsonDownloader;

import javax.swing.*;
import java.awt.*;

import com.google.gson.*;

public class MainWindow extends JFrame {

    private JPanel panel;

    public MainWindow(String title) {
        int[] screenCenter = getScreenCenter();
        this.panel = new JPanel();

        setTitle(title);
        setResizable(false);

        initElements();
        setContentPane(panel);
        pack();

        int halfWidth = getWidth() / 2;
        int halfHeight = getHeight() / 2;
        setLocation(screenCenter[0] - halfWidth, screenCenter[1] - halfHeight);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
        subredditField.addActionListener(e -> {
            String text = subredditField.getText();
            String url = "http://www.reddit.com/r/" + text + ".json";

            JsonDownloader downloader = new JsonDownloader(url);
            System.err.println(url);
            JsonObject json = downloader.getJson();

            //System.out.println(json.toString());
        });


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
