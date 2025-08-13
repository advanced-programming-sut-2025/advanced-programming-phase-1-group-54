package io.github.stardewmini.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.stardewmini.Main;
import io.github.stardewmini.controller.GameMenuController;
import io.github.stardewmini.model.GameAssetManager;
import io.github.stardewmini.model.Result;
import io.github.stardewmini.model.SoundManager;

import java.util.ArrayList;

public class CreateGameMenu implements Screen {
    private Table root;
    private Table firstPage;
    private Table secondPage;
    private Label choosingMapLabel;
    private Stage stage;

    private final ArrayList<String> playerNames = new ArrayList<>();

    private String getPlayersList() {
        StringBuilder message = new StringBuilder("Selected Players:\n");
        int i = 0;
        for (String playerName : playerNames) {
            i++;
            message.append(i).append(". ").append(playerName);
        }

        return message.toString();
    }

    private String getChoosingMapString() {
        return "Choose map for " + GameMenuController.getNextPlayerUsername();
    }

    private void createFirstPage(Skin skin) {
        Label titleLabel = new Label("Choose Players", skin, "Bold");

        TextField usernameField = new TextField("", skin);
        usernameField.setMessageText("Username");

        TextButton addPlayerButton = new TextButton("Add Player", skin);
        TextButton clearPlayersButton = new TextButton("Clear Players", skin);

        Label playersLabel = new Label(getPlayersList(), skin);
        Label resultLabel = new Label("", skin);

        addPlayerButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                SoundManager.getInstance().playClick();
                Result result = GameMenuController.findUsername(usernameField.getText());

                resultLabel.setText(result.message());

                if (result.success()) {
                    playerNames.add(usernameField.getText());
                    playersLabel.setText(getPlayersList());
                }
            }
        });


        TextButton nextButton = new TextButton("Next", skin);

        nextButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                SoundManager.getInstance().playClick();
                Result result = GameMenuController.selectNewGameUsers(playerNames);

                resultLabel.setText(result.message());
                if (result.success()) {
                    choosingMapLabel.setText(getChoosingMapString());
                    root.removeActor(firstPage);
                    root.add(secondPage).expand().fill().row();
                }
            }
        });

        TextButton backButton = new TextButton("Back", skin);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                SoundManager.getInstance().playClick();
                Main.getInstance().getScreen().dispose();
                Main.getInstance().setScreen(new MainMenu());
            }
        });

        firstPage = new Table(skin);
        firstPage.setFillParent(true);
        firstPage.center();

        firstPage.add(titleLabel);
        firstPage.row().pad(10, 0, 10, 0);
        firstPage.add(usernameField).width(300);
        firstPage.row().pad(10, 0, 10, 0);
        firstPage.add(addPlayerButton).width(300).height(90);
        firstPage.row().pad(10, 0, 10, 0);
        firstPage.add(clearPlayersButton).width(300).height(90);
        firstPage.row().pad(10, 0, 10, 0);
        firstPage.add(playersLabel);
        firstPage.row().pad(10, 0, 10, 0);
        firstPage.add(resultLabel);
        firstPage.row().pad(10, 0, 10, 0);
        firstPage.add(nextButton).height(90).width(300);
        firstPage.row().pad(10, 0, 10, 0);
        firstPage.add(backButton).height(90).width(300);
    }

    private void createSecondPage(Skin skin) {
        choosingMapLabel = new Label("", skin, "Bold");

        SelectBox<Integer> farmSelectBox = new SelectBox<>(skin);
        Integer[] integerArray = new Integer[GameMenuController.getNumberOfFarms()];
        for (int i = 0; i < integerArray.length; i++) {
            integerArray[i] = i + 1;
        }
        farmSelectBox.setItems(integerArray);

        Label resultLabel = new Label("", skin);

        TextButton nextButton = new TextButton("Next", skin);
        nextButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                SoundManager.getInstance().playClick();
                Result result = GameMenuController.chooseNewGameMap(farmSelectBox.getSelected());

                resultLabel.setText(result.message());

                if (result.code() == 0) {
                    choosingMapLabel.setText(getChoosingMapString());
                }
                else if (result.code() == 1) {
                    GameMenuController.createNewGame();
                    GameMenuController.loadGame();
                    Main.getInstance().getScreen().dispose();
                    Main.getInstance().setScreen(new GameScreen(GameAssetManager.getInstance().getSkin()));
                }
            }
        });


        TextButton backButton = new TextButton("Back", skin);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                SoundManager.getInstance().playClick();
                GameMenuController.reset();
                Main.getInstance().getScreen().dispose();
                Main.getInstance().setScreen(new MainMenu());
            }
        });

        secondPage = new Table();
        secondPage.setFillParent(true);
        secondPage.center();

        secondPage.add(choosingMapLabel);
        secondPage.row().pad(10, 0, 10, 0);
        secondPage.add(farmSelectBox);
        secondPage.row().pad(10, 0, 10, 0);
        secondPage.add(resultLabel);
        secondPage.row().pad(10, 0, 10, 0);
        secondPage.add(nextButton).height(90).width(300);
        secondPage.row().pad(10, 0, 10, 0);
        secondPage.add(backButton).height(90).width(300);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Skin skin = GameAssetManager.getInstance().getSkin();

        root = new Table();
        root.setFillParent(true);
        root.center();

        createFirstPage(skin);
        createSecondPage(skin);

        root.add(firstPage).expand().fill().row();

        stage.addActor(root);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 0, 0, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
