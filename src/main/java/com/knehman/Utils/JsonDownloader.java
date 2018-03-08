package com.knehman.Utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.net.URL;

import com.google.gson.*;

public class JsonDownloader {

    private JsonObject json;

    public JsonDownloader(String urlString) {
        try {

            URL url = new URL(urlString);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.setRequestMethod("GET");
            request.setRequestProperty("Accept", "application/json");
            request.connect();

            System.out.println("Len: " + request.getContentLength());

            InputStreamReader contextStream = new InputStreamReader(request.getInputStream());
            JsonParser parser = new JsonParser();

            JsonElement ele = parser.parse(contextStream);
            System.out.println(ele.toString());
            //this.json = ele.getAsJsonObject();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JsonObject getJson() {
         return this.json;
    }
}
