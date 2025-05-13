package controller.Game;

import model.App;
import model.Result;
import model.alive.Player;
import model.enums.FishingPoleLevel;
import model.enums.ProduceQuality;
import model.enums.SkillType;
import model.items.Fish;

public class FishingController {
    // TODO getNeighbors
    public static Result fishing(String fishingPoleName) {
        Player player = App.getCurrentGame().getCurrentPlayer();

        int skillLevel = player.getSkills().get(SkillType.FISHING).getLevel();
        double weatherFactor = App.getCurrentGame().getCurrentWeather().getFishingFactor();

        int numberOfFishes = Math.min((int) Math.ceil(Math.random() * weatherFactor * (skillLevel + 2)), 6);
        double poleFactor = player.getFishingPole(FishingPoleLevel.valueOf(fishingPoleName)).getPoleFactor();

        for (int i = 0; i < numberOfFishes; i++) {
            ProduceQuality quality = CommonGameController.
                    giveQuality(Math.random() * (skillLevel + 2) * poleFactor / (7 - weatherFactor));

            Fish fish = Fish.getSeasonFish();
            fish.setQuality(quality);
            if (!player.getBackpack().addItem(fish, 1)) {
                return new Result(-1, "Backpack is full");
            }

        }

        return new Result(1, "You got " + numberOfFishes + " fishes");
    }

}
