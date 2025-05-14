package controller.Game;

import model.App;
import model.Result;
import model.alive.Player;
import model.enums.FishingPoleLevel;
import model.enums.ProduceQuality;
import model.enums.SkillType;
import model.items.Fish;
import model.map.Farm;
import model.map.Lake;

import static controller.Game.ToolsController.addToBackPack;

public class FishingController {
    public static Result fishing(String fishingPoleName) {
        Player player = App.getCurrentGame().getCurrentPlayer();
        Farm farm = player.getFarm();

        boolean isNearLake = false;
        for (Lake lake : farm.getLakes()) {
            if (MapController.isNear(player.getCurrentLocation(), lake)) {
                isNearLake = true;
            }
        }

        if (!isNearLake) {
            return new Result(false,"you must be next to a lake to start fishing");
        }

        int skillLevel = player.getSkills().get(SkillType.FISHING).getLevel();
        double weatherFactor = App.getCurrentGame().getCurrentWeather().getFishingFactor();

        int numberOfFishes = Math.min((int) Math.ceil(Math.random() * weatherFactor * (skillLevel + 2)), 6);
        double poleFactor = player.getFishingPole(FishingPoleLevel.valueOf(fishingPoleName)).getPoleFactor();

        StringBuilder message = new StringBuilder("Starting fishing ...");

        for (int i = 0; i < numberOfFishes; i++) {
            ProduceQuality quality = CommonGameController.
                    giveQuality(Math.random() * (skillLevel + 2) * poleFactor / (7 - weatherFactor));

            Fish fish = Fish.getSeasonFish();
            // TODO getCheapestFish
            fish.setQuality(quality);

            Result result = addToBackPack(player.getBackpack(), fish, 1);
            message.append("\n").append(result.message());
        }

        if (numberOfFishes == 0)
            message.append("\nYou got no fish :(");

        player.getSkills().get(SkillType.FISHING).addXP(5);
        return new Result(true, message.toString());
    }

}
