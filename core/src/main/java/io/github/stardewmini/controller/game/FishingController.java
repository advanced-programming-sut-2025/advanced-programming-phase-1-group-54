package io.github.stardewmini.controller.game;

import io.github.stardewmini.model.App;
import io.github.stardewmini.model.Result;
import io.github.stardewmini.model.enums.FishingPoleType;
import io.github.stardewmini.model.enums.ProduceQuality;
import io.github.stardewmini.model.enums.SkillType;
import io.github.stardewmini.model.items.Fish;
import io.github.stardewmini.model.items.tools.FishingPole;
import io.github.stardewmini.model.lives.Player;
import io.github.stardewmini.model.lives.Skill;
import io.github.stardewmini.model.map.Farm;
import io.github.stardewmini.model.map.GenericWall;
import io.github.stardewmini.view.FishingMenu;

public class FishingController {

    private FishingMenu menu;

    public void setMenu(FishingMenu menu) {
        this.menu = menu;
    }

    public void handle(){

    }

    public static Result fishing(String fishingPoleName) {
        Player player = App.getCurrentGame().getCurrentPlayer();
        Farm farm = App.getCurrentGame().getWorld().getFarmAt(player.getCurrentLocation());

        if (farm == null) {
            return new Result(1, "You aren't in any farm");
        }

        boolean isNearLake = false;
        for (GenericWall lake : farm.getLakes()) {
            System.out.println(lake);
            System.out.println(player.getCurrentLocation());
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
        double poleFactor = player.getFishingPole(FishingPoleType.fromString(fishingPoleName)).getPoleFactor();

        StringBuilder message = new StringBuilder("Starting fishing ...");

        for (int i = 0; i < numberOfFishes; i++) {
            ProduceQuality quality = ProduceQuality.
                    giveQuality(Math.random() * (skillLevel + 2) * poleFactor / (7 - weatherFactor));


            Fish fish;
            if (fishingPoleType == FishingPoleType.TRAINING) {
                fish = Fish.getCheapestSeasonFish(App.getCurrentGame().getDateTime().getSeason());
            } else {
                fish = Fish.getSeasonFish(App.getCurrentGame().getDateTime().getSeason(),
                        skillLevel == Skill.getMaxSkillLevel());
            }
            fish.setQuality(quality);

            Result result = ToolsController.addToBackPack(player.getBackpack(), fish, 1);
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
