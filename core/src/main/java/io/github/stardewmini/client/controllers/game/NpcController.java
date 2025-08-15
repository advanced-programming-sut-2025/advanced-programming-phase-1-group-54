package io.github.stardewmini.client.controllers.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import io.github.stardewmini.client.app.App;
import io.github.stardewmini.client.app.ClientApp;
import io.github.stardewmini.client.controllers.ClientGameController;
import io.github.stardewmini.common.Message;
import io.github.stardewmini.common.model.GameAssetManager;
import io.github.stardewmini.common.model.Result;
import io.github.stardewmini.common.model.lives.NPC;
import io.github.stardewmini.common.model.map.NPCHouse;
import io.github.stardewmini.common.model.map.Tile;

import java.util.ArrayList;

public class NpcController {
    public static void giftAnimation(NPC npc, float delta) {
        Animation<TextureRegion> animation = GameAssetManager.getInstance().getNPCsGifts(npc.getName());
        if(! animation.isAnimationFinished(npc.getAnimationTime())){
            npc.getSprite().setRegion(animation.getKeyFrame(npc.getAnimationTime()));
            npc.setAnimationTime(npc.getAnimationTime() + delta);
            animation.setPlayMode(Animation.PlayMode.NORMAL);
        }
    }

    public static void update(float delta) {
        ArrayList<NPCHouse> npcHouses = App.getCurrentGame().getWorld().getNpcHouses();
        for(int i = 0 ;i < npcHouses.size() ; i++){
            NPC npc = npcHouses.get(i).getNpc();
            giftAnimation(npc,delta);
            if(npc.getDialogTime() < NPC.dialogTiming){
                npc.setDialogTime(npc.getDialogTime() + delta);
            }
        }
    }

    public static void draw(SpriteBatch batch, Window[] windows) {
        ArrayList<NPCHouse> npcHouses = App.getCurrentGame().getWorld().getNpcHouses();
        for(int i = 0 ; i < npcHouses.size() ; i++){
            NPC npc = npcHouses.get(i).getNpc();
            if(npc.getDialogTime() >= NPC.dialogTiming){
                windows[i].draw(batch,1f);
            }
            else if(npc.getDialogTime() < 10){
                windows[i].draw(batch,1f);
            }
            else {
                windows[i].getTitleLabel().setText("dialog");
            }
        }
    }

    public static void fixWindows(Window[] windows){
        ArrayList<NPCHouse> npcHouses = App.getCurrentGame().getWorld().getNpcHouses();
        for(int i = 0 ; i < windows.length ; i++){
            windows[i].setPosition(npcHouses.get(i).getNpc().getLocation().column() * Tile.getSize(),
                (npcHouses.get(i).getNpc().getLocation().row() + 1) * Tile.getSize());
        }
    }


    public static Result questList(NPC npc){
        Message message = ClientGameController.createQuestList(npc.getName());
        return ClientApp.sendRequest(message);
    }

    public static Result friendShipNpc(NPC npc){
        Message message = ClientGameController.createFriendShipNpc(npc.getName());
        return ClientApp.sendRequest(message);
    }

    public static Result giftNpc(NPC npc, String giftItem){
        Message message = ClientGameController.createGiftNpc(npc.getName(),giftItem);
        return ClientApp.sendRequest(message);
    }

    public static Result questFinish(String questNumber, NPC npc){
        Message message = ClientGameController.createQuestFinish(questNumber,npc.getName());
        return ClientApp.sendRequest(message);
    }
}
