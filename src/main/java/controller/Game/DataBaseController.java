package controller.Game;

import org.json.JSONArray;
import org.json.JSONTokener;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

public class DataBaseController {
    public static JSONArray loadJsonArray(String filename) {
        String fullPath = Paths.get("").toAbsolutePath().resolve(filename).toString();
        try (InputStream inputStream = new FileInputStream(fullPath)) {
            return new JSONArray(new JSONTokener(inputStream));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
