package io.github.stardewmini.client.Renderers;

import io.github.stardewmini.client.Renderers.Lives.AnimalRenderer;
import io.github.stardewmini.client.Renderers.Lives.NPCRenderer;
import io.github.stardewmini.client.Renderers.Lives.PlayerRenderer;
import io.github.stardewmini.client.Renderers.Plants.CropRenderer;
import io.github.stardewmini.client.Renderers.Plants.FruitRenderer;
import io.github.stardewmini.client.Renderers.Plants.SeedRenderer;
import io.github.stardewmini.client.Renderers.Plants.TreeRenderer;
import io.github.stardewmini.common.model.Game;
import io.github.stardewmini.common.model.lives.Player;

import java.util.ArrayList;

public class GameRenderer {
    private final ArrayList<TileRenderer> tileRenderers = new ArrayList<>();
    private final ArrayList<MaterialRenderer> materialRenderers = new ArrayList<>();
    private final ArrayList<BuildingRenderer> buildingRenderers = new ArrayList<>();
    private final ArrayList<TreeRenderer> treeRenderers = new ArrayList<>();
    private final ArrayList<SeedRenderer> seedRenderers = new ArrayList<>();
    private final ArrayList<FruitRenderer> fruitRenderers = new ArrayList<>();
    private final ArrayList<CropRenderer> cropRenderers = new ArrayList<>();
    private final ArrayList<AnimalRenderer> animalRenderers = new ArrayList<>();
    private final ArrayList<NPCRenderer> npcRenderers = new ArrayList<>();
    private final ArrayList<PlayerRenderer> playerRenderers = new ArrayList<>();


    public GameRenderer(Game game) {
        for (Player player: game.getPlayers()) {
            playerRenderers.add(new PlayerRenderer(player));
        }

        // TODO
    }

    public ArrayList<TileRenderer> getTileRenderers() {
        return tileRenderers;
    }

    public ArrayList<MaterialRenderer> getMaterialRenderers() {
        return materialRenderers;
    }

    public ArrayList<BuildingRenderer> getBuildingRenderers() {
        return buildingRenderers;
    }

    public ArrayList<TreeRenderer> getTreeRenderers() {
        return treeRenderers;
    }

    public ArrayList<SeedRenderer> getSeedRenderers() {
        return seedRenderers;
    }

    public ArrayList<FruitRenderer> getFruitRenderers() {
        return fruitRenderers;
    }

    public ArrayList<CropRenderer> getCropRenderers() {
        return cropRenderers;
    }

    public ArrayList<AnimalRenderer> getAnimalRenderers() {
        return animalRenderers;
    }

    public ArrayList<NPCRenderer> getNpcRenderers() {
        return npcRenderers;
    }

    public ArrayList<PlayerRenderer> getPlayerRenderers() {
        return playerRenderers;
    }
}
