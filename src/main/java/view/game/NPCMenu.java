package view.game;

import controller.Game.NpcController;
import model.Quest;
import model.Result;
import model.enums.commands.Command;
import model.enums.commands.GameCommand;
import model.lives.NPC;
import model.relationships.NPCFriendship;

import java.util.ArrayList;
import java.util.Scanner;

public class NPCMenu implements GameSubMenu{
    private static NPC npc;
    @Override
    public void run(Scanner scanner){
        String input = scanner.nextLine();

        if (checkGameCommand(input, scanner))
            return;

        invalidCommand();
    }
    private boolean checkGameCommand(String input, Scanner scanner){
        if(GameCommand.QUESTS_LIST.matches(input))
            handleQuestList();
        else if(GameCommand.QUESTS_FINISH.matches(input))
            handleQuestFinish(input);
        else{
            return false;
        }
        return true;
    }

    private void handleQuestFinish(String input) {
        Command command = GameCommand.QUESTS_FINISH;
        String index = command.getGroup(input,"index");
        showResult(NpcController.questFinish(Integer.parseInt(index)));
    }

    public static void setNpc(NPC temp) {
        npc = temp;
    }
    public static NPC getNpc(){
        return npc;
    }

    private void handleQuestList() {
        ArrayList<String> list = NpcController.questList(npc);
        for (String s : list) {
            System.out.println(s);
        }
    }
}
