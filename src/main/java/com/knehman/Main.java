package com.knehman;

import com.knehman.UI.MainWindow;
import com.knehman.Utils.*;

public class Main {

    private static String projectName = "<project name>";
    private static ConfigProperties properties = null;
    private static MainWindow window = null;

    public static void main(String args[]) {
        init();


        window.setVisible(true);
    }

    private static void init() {
        Config config = new Config("config.properties");
        ConfigProperties properties = config.getProperties();

        projectName = properties.getString("projectName");

        int windowWidth = properties.getInt("windowWidth");
        int windowHeight = properties.getInt("windowHeight");
        window = new MainWindow(projectName, windowWidth, windowHeight);
    }
}