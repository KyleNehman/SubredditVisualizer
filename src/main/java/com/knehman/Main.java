package com.knehman;

import com.knehman.UI.MainWindow;
import com.knehman.Utils.*;

public class Main {

    public static String projectName = "<project name>";
    public static String projectVersion = "<project version>";
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
        projectVersion = properties.getString("projectVersion");

        window = new MainWindow(projectName);
    }
}