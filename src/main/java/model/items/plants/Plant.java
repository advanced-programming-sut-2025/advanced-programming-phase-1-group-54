package model.items.plants;

import model.Placeable;
import model.enums.Season;
import model.items.Item;

public abstract class Plant extends Item implements Placeable {

    protected String name;
    protected String source;
    protected String fruit;

    protected int[] stages;
    protected int maxStages;
    protected int currentStage;
    protected int daysInCurrentStage;
    protected int totalHarvestTime;

    protected int regrowthTime;
    protected Season[] seasons;

    protected boolean fruitIsRipen;
    protected boolean fertilized;
    protected boolean watered;
    protected int numberOfDaysWithoutWater;

    public String getName() {
        return name;
    }

    public String getSource() {
        return source;
    }

    public String getFruit() {
        return fruit;
    }

    public int[] getStages() {
        return stages;
    }

    public int getMaxStages() {
        return maxStages;
    }

    public int getCurrentStage() {
        return currentStage;
    }

    public int getDaysInCurrentStage() {
        return daysInCurrentStage;
    }

    public int getTotalHarvestTime() {
        return totalHarvestTime;
    }

    public int getRegrowthTime() {
        return regrowthTime;
    }

    public Season[] getSeasons() {
        return seasons;
    }

    public boolean isFruitIsRipen() {
        return fruitIsRipen;
    }

    public boolean isFertilized() {
        return fertilized;
    }

    public boolean isWatered() {
        return watered;
    }

    public int getNumberOfDaysWithoutWater() {
        return numberOfDaysWithoutWater;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setFruit(String fruit) {
        this.fruit = fruit;
    }

    public void setStages(int[] stages) {
        this.stages = stages;
    }

    public void setMaxStages(int maxStages) {
        this.maxStages = maxStages;
    }

    public void setCurrentStage(int currentStage) {
        this.currentStage = currentStage;
    }

    public void setDaysInCurrentStage(int daysInCurrentStage) {
        this.daysInCurrentStage = daysInCurrentStage;
    }

    public void setTotalHarvestTime(int totalHarvestTime) {
        this.totalHarvestTime = totalHarvestTime;
    }

    public void setRegrowthTime(int regrowthTime) {
        this.regrowthTime = regrowthTime;
    }

    public void setSeasons(Season[] seasons) {
        this.seasons = seasons;
    }

    public void setFruitIsRipen(boolean fruitIsRipen) {
        this.fruitIsRipen = fruitIsRipen;
    }

    public void setFertilized(boolean fertilized) {
        this.fertilized = fertilized;
    }

    public void setWatered(boolean watered) {
        this.watered = watered;
    }

    public void setNumberOfDaysWithoutWater(int numberOfDaysWithoutWater) {
        this.numberOfDaysWithoutWater = numberOfDaysWithoutWater;
    }

}
