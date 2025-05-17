package controller.builders;

import model.App;
import model.lives.NPC;
import model.map.World;
import model.relationships.NPCFriendship;

import java.util.ArrayList;

public class PlayerBuilder {
    public static ArrayList<NPCFriendship> npcFriendshipBuilder(){
        ArrayList<NPCFriendship> npcFriendships = new ArrayList<>();
        ArrayList<NPC> npcs = App.getCurrentGame().getWorld().getNpcs();

        NPCFriendship npcFriendship = new NPCFriendship(npcs.get(0));
        npcFriendships.add(npcFriendship);

        npcFriendship = new NPCFriendship(npcs.get(1));
        npcFriendships.add(npcFriendship);

        npcFriendship = new NPCFriendship(npcs.get(2));
        npcFriendships.add(npcFriendship);

        npcFriendship = new NPCFriendship(npcs.get(3));
        npcFriendships.add(npcFriendship);

        npcFriendship = new NPCFriendship(npcs.get(4));
        npcFriendships.add(npcFriendship);
        return npcFriendships;
    }
}
