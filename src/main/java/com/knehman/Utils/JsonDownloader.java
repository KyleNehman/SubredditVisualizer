package com.knehman.Utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.*;
import com.knehman.Main;

public class JsonDownloader {

    private JsonObject json;

    public JsonDownloader(String subredditString) {
        try {

            URL url = new URL("https", "www.reddit.com", "/r/" + subredditString + ".json");
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.setRequestMethod("GET");
            request.setRequestProperty("User-Agent",  Main.projectName + ":" + Main.projectVersion +
                    " (by KyleNehman)");


            request.setRequestProperty("Accept", "application/json");
            request.connect();

            InputStreamReader contextStream = new InputStreamReader(request.getInputStream());
            JsonParser parser = new JsonParser();

            this.json = parser.parse(contextStream).getAsJsonObject();

        } catch (IOException e) {
            // Awful way to handle exceptions, but it'll be updated following testing
            e.printStackTrace();
        }
    }

    public JsonObject getJson() {
         return this.json;
    }

    private static void printHeaders(HttpURLConnection request) {

        for (String h : request.getHeaderFields().keySet()) {
            System.out.println("\t\"" + h + "\": \"" + request.getHeaderField(h) + "\"");
        }
    }
}
