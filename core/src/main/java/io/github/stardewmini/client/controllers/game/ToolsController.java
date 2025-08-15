package io.github.stardewmini.client.controllers.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import io.github.stardewmini.client.app.App;
import io.github.stardewmini.client.app.ClientApp;
import io.github.stardewmini.client.controllers.ClientGameController;
import io.github.stardewmini.common.Message;
import io.github.stardewmini.common.model.Game;
import io.github.stardewmini.common.model.Result;
import io.github.stardewmini.common.model.enums.Direction;
import io.github.stardewmini.common.model.enums.ToolType;
import io.github.stardewmini.common.model.lives.Player;
import io.github.stardewmini.common.model.map.Tile;

public class ToolsController {
    public static void mouseClick(int screenX, int screenY, OrthographicCamera camera) {
        Player player = App.getCurrentPlayer();
        Vector3 clickPos = new Vector3(screenX, screenY, 0);
        camera.unproject(clickPos); // Converts screen to world coordinates
        Vector2 playerPos = new Vector2(player.getX() + Tile.getSize()/2f, player.getY() + Tile.getSize()/2f); // Adjust to your player’s position

        float dx = clickPos.x - playerPos.x;
        float dy = clickPos.y - playerPos.y;

        Direction direction = getDirection(dy, dx);

        Message message = ClientGameController.createUseTool(direction);
        ClientApp.sendUpdate(message);
    }

    private static Direction getDirection(float dy, float dx) {
        float angleDeg = MathUtils.atan2(dy, dx) * MathUtils.radiansToDegrees;
        if (angleDeg < 0) angleDeg += 360; // Normalize 0–360

        System.out.println(angleDeg);

        Direction direction; // directions are reversed because Y axis is from bottom to top.
        if (angleDeg >= 337.5 || angleDeg < 22.5) direction = Direction.RIGHT;
        else if (angleDeg < 67.5) direction = Direction.DOWN_RIGHT;
        else if (angleDeg < 112.5) direction = Direction.DOWN;
        else if (angleDeg < 157.5) direction = Direction.DOWN_LEFT;
        else if (angleDeg < 202.5) direction = Direction.LEFT;
        else if (angleDeg < 247.5) direction = Direction.UP_LEFT;
        else if (angleDeg < 292.5) direction = Direction.UP;
        else direction = Direction.UP_RIGHT;
        return direction;
    }
}
