package io.github.stardewmini;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.stardewmini.client.app.App;
import io.github.stardewmini.client.view.MainMenu;
import io.github.stardewmini.client.view.StartMenu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class Main extends Game {
    private static Main instance;
    private static SpriteBatch batch;


    @Override
    public void create() {
        instance = this;
        batch = new SpriteBatch();
//        setScreen(new FirstScreen());
//        setScreen(new AnimalMenu(GameAssetManager.getInstance().getSkin(), Animal.getAnimal("Hen")));
//        setScreen(new NPCMenu(new NPC("sasa","ahh"),GameAssetManager.getInstance().getSkin()));
//        setScreen(new InventoryMenu(GameAssetManager.getInstance().getSkin()));

//        FishingGame game = new FishingGame(null,0);
//        FishingController.setGame(game);
//        setScreen(new FishingMenu(GameAssetManager.getInstance().getSkin() , "gsdg","fish"));

//        setScreen(new CraftingMenu(GameAssetManager.getInstance().getSkin()));
//        setScreen(new CookingMenu(GameAssetManager.getInstance(Skin()));
//        setScreen(new shopMenu(GameAssetManager.getInstance().getSkin()));
//                setScreen(new CheatMenu(GameAssetManager.getInstance().getSkin()));


        if (App.getLoggedInUsername() != null) {
            setScreen(new MainMenu());
        } else {
            setScreen(new StartMenu());
        }


//        String url = "jdbc:sqlite:C:/Users/Asus/Desktop/mydatabase.db"; // نام فایل دیتابیس
//
//                try {
//                    Connection conn = DriverManager.getConnection(url);
//                    if (conn != null) {
//                        System.out.println("اتصال موفق و دیتابیس ساخته شد!");
//                    }
//                } catch (SQLException e) {
//                    System.out.println(e.getMessage());
//                }


//        String url = "jdbc:sqlite:C:/Users/Asus/Desktop/mydatabase.db";
//
//                String sql = """
//            CREATE TABLE IF NOT EXISTS A (
//                id INTEGER PRIMARY KEY,
//                name TEXT
//            );
//        """;
//
//                try (Connection conn = DriverManager.getConnection(url);
//                     Statement stmt = conn.createStatement()) {
//                    stmt.execute(sql);
//                    System.out.println("جدول ساخته شد!");
//                } catch (SQLException e) {
//                    System.out.println(e.getMessage());
//                }

    }


    @Override
    public void render() {
        super.render();

    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    public static Main getInstance() {
        return instance;
    }

    public static SpriteBatch getBatch() {
        return batch;
    }
}
