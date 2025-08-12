package io.github.stardewmini.controller.game;


import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.Objects;

public class ResourceController {
    /* TODO
         move reading data here.
     */
    public static JsonArray loadJsonArray(String filename) {
        try (InputStream inputStream = Objects.requireNonNull(ResourceController.class.getClassLoader().getResourceAsStream(filename));
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream)) {
            return JsonParser.parseReader(inputStreamReader).getAsJsonArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
