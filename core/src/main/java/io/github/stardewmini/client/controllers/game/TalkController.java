package io.github.stardewmini.client.controllers.game;

import io.github.stardewmini.client.app.ClientApp;
import io.github.stardewmini.client.controllers.ClientGameController;
import io.github.stardewmini.common.Message;
import io.github.stardewmini.common.model.Result;

public class TalkController {

    public static Result gameUsernames(){
        Message message = ClientGameController.createGameUsernames();
        return ClientApp.sendRequest(message);
    }

    public static Result talk(String username, String talkingMessage){
        Message message = ClientGameController.createTalk(username, talkingMessage);
        return ClientApp.sendRequest(message);
    }

    public static Result talkingHistory(String username){
        Message message = ClientGameController.createTalkHistory(username);
        return ClientApp.sendRequest(message);
    }
}
