package io.github.stardewmini.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.stardewmini.Main;
import io.github.stardewmini.model.GameAssetManager;

/** First screen of the application. Displayed after the application is created. */
public class FirstScreen implements Screen {
    @Override
    public void show() {
        GameAssetManager gameAssetManager = GameAssetManager.getGameAssetManager();
        Texture aks = new Texture("Stardew_Valley_Images-main/sprites/Robin.png");
        int totalWidth = aks.getWidth();
        int totalHeight = aks.getHeight();
        TextureRegion[][] cow = TextureRegion.split(new
            Texture("Stardew_Valley_Images-main/sprites/Robin.png"),totalWidth/9,totalHeight);
        for(int i = 0; i < cow[0].length; i++) {

            Gdx.gl.glClearColor(0, 0, 0, 0);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            Main.getBatch().begin();
            Main.getBatch().draw(cow[0][i],0,0);
            if (i %3 == 1){
                i++;
                Main.getBatch().draw(cow[0][i],totalWidth/9,0);
            }
//        for(int i = 0; i < cow.length; i++){
//            for(int j = 0; j < cow[i].length; j++){
//                Main.getBatch().draw(cow[i][j],j*100 + 100,i*200 + 100,100,400);
//            }
//        }
            Main.getBatch().end();
//            Pixmap pixmap;
//            Pixmap flipped;
//
//            if(i %3 == 2){
//                pixmap = ScreenUtils.getFrameBufferPixmap(0,0,2 * totalWidth/9,totalHeight);
//                flipped = new Pixmap(2 * totalWidth/9,totalHeight,Pixmap.Format.RGBA8888);
//                for(int y = 0 ;y < totalHeight ;y++) {
//                    for(int x = 0 ;x < 2 * totalWidth/9 ;x++) {
//                        int pixel = pixmap.getPixel(x,y);
//                        flipped.drawPixel(x,totalHeight - y - 1,pixel);
//                    }
//                }
//            }
//            else{
//                pixmap = ScreenUtils.getFrameBufferPixmap(0,0,totalWidth/9,totalHeight);
//                flipped = new Pixmap(totalWidth/9,totalHeight,Pixmap.Format.RGBA8888);
//                for(int y = 0 ;y < totalHeight ;y++) {
//                    for(int x = 0 ;x < totalWidth/9 ;x++) {
//                        int pixel = pixmap.getPixel(x,y);
//                        flipped.drawPixel(x,totalHeight - y - 1,pixel);
//                    }
//                }
//            }
//
//
//            PixmapIO.writePNG(Gdx.files.local("Robin" + i +".png"), flipped);
        }


        // Stardew_Valley_Images-main/sprites/Cow Brown.png

        // Prepare your screen here.
    }

    @Override
    public void render(float delta) {
        // Draw your screen here. "delta" is the time since last render in seconds.
    }

    @Override
    public void resize(int width, int height) {
        // If the window is minimized on a desktop (LWJGL3) platform, width and height are 0, which causes problems.
        // In that case, we don't resize anything, and wait for the window to be a normal size before updating.
        if(width <= 0 || height <= 0) return;

        // Resize your screen here. The parameters represent the new window size.
    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void hide() {
        // This method is called when another screen replaces this one.
    }

    @Override
    public void dispose() {
        // Destroy screen's assets here.
    }
}
