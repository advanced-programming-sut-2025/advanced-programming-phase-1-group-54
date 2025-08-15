package io.github.stardewmini.server.controllers.game;

import io.github.stardewmini.client.app.App;
import io.github.stardewmini.common.model.Result;
import io.github.stardewmini.common.model.lives.Player;

public class EmojiController {

    public static Result getEmojis(String requester){
        Player player = App.getCurrentGame().getPlayerByUsername(requester);
        StringBuilder output = new StringBuilder();
        for(String string : player.getEmojis()){
            output.append(string).append(",");
        }
        output.deleteCharAt(output.length()-1);
        return new Result(true,output.toString());
    }

    public static Result addEmojis(String requester, String emoji){
        Player player = App.getCurrentGame().getPlayerByUsername(requester);
        player.getEmojis().add(emoji);
        return new Result(true,"Emoji" + emoji +" added");
    }

    public static Result removeEmojis(String requester, String emoji){
        Player player = App.getCurrentGame().getPlayerByUsername(requester);
        player.getEmojis().remove(emoji);
        return new Result(true,"Emoji" + emoji +" removed");
    }

    public static Result showEmojis(String requester, String emoji){
        Player player = App.getCurrentGame().getPlayerByUsername(requester);
        player.setEmojiName(emoji);
        player.setEmojiTime(0);
        return new Result(true,"Emoji" + emoji +" showed");
    }

}
