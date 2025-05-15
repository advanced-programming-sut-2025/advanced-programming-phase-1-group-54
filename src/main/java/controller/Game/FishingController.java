package controller.Game;

import model.App;
import model.Result;
import model.lives.Skill;
import model.lives.Player;
import model.enums.FishingPoleType;
import model.enums.ProduceQuality;
import model.enums.SkillType;
import model.items.Fish;
import model.items.tools.FishingPole;
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
            return new Result(false, "you must be next to a lake to start fishing");
        }

        FishingPoleType fishingPoleType = FishingPoleType.fromString(fishingPoleName);

        if (fishingPoleType == null) {
            return new Result(false, "invalid fishing pole");
        }

        FishingPole fishingPole = player.getFishingPole(fishingPoleType);

        if (fishingPole == null) {
            return new Result(false, "you don't have " + fishingPoleName + " fishing pole");
        }

        int energyNeeded = fishingPole.getEnergyNeededPerUse();
        boolean enoughEnergy = player.checkEnergy(energyNeeded, SkillType.FISHING);

        int skillLevel = player.getSkill(SkillType.FISHING).getLevel();
        double weatherFactor = App.getCurrentGame().getCurrentWeather().getFishingFactor();

        int numberOfFishes = Math.min((int) Math.ceil(Math.random() * weatherFactor * (skillLevel + 2)), 6);
        double poleFactor = player.getFishingPole(FishingPoleType.valueOf(fishingPoleName)).getPoleFactor();

        StringBuilder message = new StringBuilder("Starting fishing ...");

        for (int i = 0; i < numberOfFishes; i++) {
            ProduceQuality quality = CommonGameController.
                    giveQuality(Math.random() * (skillLevel + 2) * poleFactor / (7 - weatherFactor));


            Fish fish;
            if (fishingPoleType == FishingPoleType.TRAINING) {
                fish = Fish.getCheapestSeasonFish(App.getCurrentGame().getDateTime().getSeason());
            } else {
                fish = Fish.getSeasonFish(App.getCurrentGame().getDateTime().getSeason(), skillLevel == Skill.getMaxSkillLevel());
            }
            fish.setQuality(quality);

            Result result = addToBackPack(player.getBackpack(), fish, 1);
            message.append("\n").append(result.message());
        }

        if (numberOfFishes == 0)
            message.append("\nYou got no fish :(");

        if (!enoughEnergy) {

        }
        player.getSkill(SkillType.FISHING).addXP(5);
        return new Result(true, message.toString());
    }

}
