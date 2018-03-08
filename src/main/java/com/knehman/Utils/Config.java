package com.knehman.Utils;

import java.io.*;
import java.net.URL;

public class Config {

    private InputStream configInputStream;
    private ConfigProperties properties = null;

    // If no config path is provided, TRY to assume resources/config.properties
    public Config() {
        this.configInputStream = getConfigStream("config.properties");
        readConfig();
    }

    public Config(String configFileName) {
        this.configInputStream = getConfigStream(configFileName);
        readConfig();
    }

    private void readConfig() {
        try {
            BufferedReader bR = new BufferedReader(new InputStreamReader(configInputStream));

            properties = new ConfigProperties();

            String line;
            while ((line = bR.readLine()) != null) {
                addLineToProperties(line);
            }

            bR.close();

        } catch (IOException IOE) {
            // If an IOE occurs just print the stack trace because it really shouldn't...
            IOE.printStackTrace();
            System.exit(-150);

        } catch (NullPointerException NPE) {
            NPE.printStackTrace();
            System.exit(-200);
        }
    }

    public ConfigProperties getProperties() {
        return this.properties;
    }

    private void addLineToProperties(String line) {
        if (line.trim().length() > 0 && line.trim().charAt(0) == '#')
            return;

        int colonIndex = line.indexOf('=');

        String key;
        String configVal;
        if (colonIndex > 1) {
            key = line.substring(0, colonIndex).trim();

            if (!key.isEmpty()) {
                configVal = line.substring(colonIndex + 1).trim();
                int configValLen = configVal.length();
                String valAsString = null;
                Double valAsDub = null;

                try {
                    valAsDub = Double.parseDouble(configVal);
                } catch (NumberFormatException nFE) {
                    // Do nothing, if it's not a double it's null
                }

                if (valAsDub != null) {
                    // double doubleVal = valAsDub.doubleValue(); // 'Unnecessary after Java 5' (??)

                    if (valAsDub == Math.floor(valAsDub)) {
                        this.properties.put(key, (int) valAsDub.doubleValue());
                    } else {
                        this.properties.put(key, valAsDub);
                    }
                } else if ((configVal.charAt(0) == '\'' || configVal.charAt(0) == '\"') &&
                        (configVal.charAt(configValLen - 1) == '\'' || configVal.charAt(configValLen - 1) == '\"')) {

                    valAsString = configVal.substring(1, configValLen - 1);
                    if (valAsString.length() == 1) {
                        this.properties.put(key, valAsString.charAt(0));
                    } else {
                        this.properties.put(key, valAsString);
                    }
                }
            }
        }
    }

    private InputStream getConfigStream(String configName) {

        return this.getClass().getResourceAsStream('/' + configName);
    }
}
