package io.github.stardewmini.client.controllers;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import io.github.stardewmini.Main;
import io.github.stardewmini.client.app.ClientApp;
import io.github.stardewmini.client.view.GameScreen;
import io.github.stardewmini.common.Message;
import io.github.stardewmini.common.model.GameAssetManager;
import io.github.stardewmini.common.model.Result;

import java.util.ArrayList;
import java.util.Collections;

public class EmojiController {

    public static void showSelectedEmojis(Window window){
        Table table = new Table();
        Message message = ClientGameController.createGetEmojis();
        Result result = ClientApp.sendRequest(message);
        String[] parts = result.message().split(",");
        int inRow = 0;
        for (String part : parts) {
            Image image = new Image(GameAssetManager.getInstance().getEmojis(part));
            image.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {
                    Result result1 = selectEmojis(part);
                    window.remove();
                    Main.getInstance().getScreen().dispose();
                    Main.getInstance().setScreen(new GameScreen(GameAssetManager.getInstance().getSkin(),result1.message()));
                }
            });
            table.add(image);
            inRow++;
            if (inRow == 4){
                inRow = 0;
                table.row();
            }
        }
        window.add(table);
    }

    public static Result selectEmojis(String name){
        Message message = ClientGameController.createSelectEmojis(name);
        return ClientApp.sendRequest(message);
    }

    public static void getEmojis(Window window, Label selectedEmojis, Label restEmojis) {
        Table table1 = new Table();
        Table table2 = new Table();
        Message message = ClientGameController.createGetEmojis();
        Result result = ClientApp.sendRequest(message);
        String[] parts = result.message().split(",");
        ArrayList<String> emojis = new ArrayList<>();
        Collections.addAll(emojis, parts);
        GameAssetManager gameAssetManager = GameAssetManager.getInstance();
        int inRow1 = 0;
        int inRow2 = 0;
        for (int i = 0; i < 20; i++) {
            Image image = new Image(gameAssetManager.getEmojis(i + ""));
            if(! emojis.contains(i + "")){
                int finalI = i;
                image.addListener(new ClickListener() {
                    public void clicked(InputEvent event, float x, float y) {
                        Result result1 = addEmojis(finalI + "");
                        window.remove();
                        Main.getInstance().getScreen().dispose();
                        Main.getInstance().setScreen(new GameScreen(GameAssetManager.getInstance().getSkin(),result1.message()));
                    }
                });
                table2.add(image);
                inRow2++;
                if(inRow2 == 4){
                    inRow2 = 0;
                    table2.row();
                }
            }
            else {
                int finalI1 = i;
                image.addListener(new ClickListener() {
                    public void clicked(InputEvent event, float x, float y) {
                        Result result1 = removeEmojis(finalI1 + "");
                        window.remove();
                        Main.getInstance().getScreen().dispose();
                        Main.getInstance().setScreen(new GameScreen(GameAssetManager.getInstance().getSkin(),result1.message()));
                    }
                });
                table1.add(image);
                inRow1++;
                if(inRow1 == 4){
                    inRow1 = 0;
                    table1.row();
                }
            }
        }
        window.add(selectedEmojis).expand().pad(10).row();
        window.add(table1).expand().pad(10).row();
        window.add(restEmojis).expand().pad(10).row();
        window.add(table2).expand().pad(10).row();
    }

    public static Result addEmojis(String name) {
        Message message = ClientGameController.createAddEmojis(name);
        return ClientApp.sendRequest(message);
    }

    public static Result removeEmojis(String name){
        Message message = ClientGameController.createRemoveEmojis(name);
        return ClientApp.sendRequest(message);
    }
}
