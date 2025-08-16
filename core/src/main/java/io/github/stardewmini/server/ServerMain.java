package io.github.stardewmini.server;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.stardewmini.Main;
import io.github.stardewmini.client.view.FirstScreen;


public class ServerMain extends Game {
    @Override
    public void create() {
        setScreen(new FirstScreen());
    }


    @Override
    public void render() {
        super.render();

    }
}
