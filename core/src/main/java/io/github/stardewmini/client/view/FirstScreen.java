package io.github.stardewmini.client.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import io.github.stardewmini.client.Main;
import io.github.stardewmini.client.Renderers.GameAssetManager;

/** First screen of the application. Displayed after the application is created. */
public class FirstScreen implements Screen {
    @Override
    public void show() {
        GameAssetManager gameAssetManager = GameAssetManager.getInstance();
        Texture aks = new Texture("Stardew_Valley_Images-main/sprites/Alex.png");
        int totalWidth = aks.getWidth();
        int totalHeight = aks.getHeight();
        TextureRegion[][] cow = TextureRegion.split(aks,totalWidth/9,totalHeight);
        Main.getBatch().begin();
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

//        for(int i = 0; i < 3; i++){
//            Gdx.gl.glClearColor(0, 0, 0, 0);
//            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//
//
//            Main.getBatch().draw(cow[i][0],0,4 * totalHeight/14 - totalHeight/14 * i);
//
//        }
//        Main.getBatch().draw(cow[4][0],0,4 * totalHeight/14 - totalHeight/14 * 3);
//        Main.getBatch().draw(cow[6][0],0,4 * totalHeight/14 - totalHeight/14 * 4);
//        Main.getBatch().draw(cow[0][0],0,0);
//        Main.getBatch().end();

//        Pixmap pixmap;
//        Pixmap flipped;
//        int pngHeight = 5 * totalHeight/14;
//
//        pixmap = ScreenUtils.getFrameBufferPixmap(0,0,totalWidth, pngHeight);
//        flipped = new Pixmap(totalWidth, pngHeight,Pixmap.Format.RGBA8888);
//        for(int y = 0 ;y <  pngHeight ;y++) {
//            for(int x = 0 ;x < totalWidth ;x++) {
//                int pixel = pixmap.getPixel(x,y);
//                flipped.drawPixel(x,pngHeight - y - 1,pixel);
//            }
//        }
//
//
//        PixmapIO.writePNG(Gdx.files.local("Duck" +".png"), flipped);



//        for(int i = 0; i < cow[0].length; i++) {
//
//            Gdx.gl.glClearColor(0, 0, 0, 0);
//            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//
//            Main.getBatch().begin();
//            Main.getBatch().draw(cow[0][i],0,0);
//            if (i %3 == 1){
//                i++;
//                Main.getBatch().draw(cow[0][i],totalWidth/9,0);
//            }
//        for(int i = 0; i < cow.length; i++) {
//            for (int j = 0; j < cow[i].length; j++) {
//                Main.getBatch().draw(cow[i][j], j * totalWidth/9, 0);
//            }
//
//        }
        Main.getBatch().end();
//
//        for(int i = 0;i < 9;i++){
//
//            Pixmap pixmap;
//            Pixmap flipped;
//
//            if(i %3 == 1){
//                pixmap = ScreenUtils.getFrameBufferPixmap(i * totalWidth/9,0,2 * totalWidth/9,totalHeight);
//                flipped = new Pixmap(2 * totalWidth/9,totalHeight,Pixmap.Format.RGBA8888);
//                for(int y = 0 ;y < totalHeight ;y++) {
//                    for(int x = 0 ;x < 2 * totalWidth/9 ;x++) {
//                        int pixel = pixmap.getPixel(x,y);
//                        flipped.drawPixel(x,totalHeight - y - 1,pixel);
//                    }
//                }
//                i++;
//            }
//            else{
//                pixmap = ScreenUtils.getFrameBufferPixmap(i * totalWidth/9,0,totalWidth/9,totalHeight);
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
//            PixmapIO.writePNG(Gdx.files.local("Alex" + i +".png"), flipped);
//        }


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
