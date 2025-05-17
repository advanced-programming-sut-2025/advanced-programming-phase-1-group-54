package model.items.plants;

import model.DailyUpdate;
import model.Placeable;
import model.enums.Season;

public abstract class Plant implements Placeable, DailyUpdate {

    protected final String name;
    protected final String source;
    protected final String fruit;

    protected final int[] stages;
    protected final int maxStages;
    protected int currentStage;
    protected int daysInCurrentStage;
    protected final int totalHarvestTime;

    protected final int regrowthTime;
    protected final Season[] seasons;

    protected boolean fruitIsRipen;
    protected boolean fertilized;
    protected boolean watered;
    protected int numberOfDaysWithoutWater;

    public Plant(String name, String source, String fruit, int[] stages, int totalHarvestTime,
                 int regrowthTime, Season[] seasons) {
        this.name = name;
        this.source = source;
        this.fruit = fruit;
        this.stages = stages;
        this.maxStages = stages.length;
        this.totalHarvestTime = totalHarvestTime;
        this.regrowthTime = regrowthTime;
        this.seasons = seasons;
    }

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

    public boolean containSeason(Season season) {
        for (Season value : seasons) {
            if (value == season) {
                return true;
            }
        }
        return false;
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

    public void increaseCurrentStage(int currentStage) {
        this.currentStage += currentStage;
    }

    public void increaseDaysInCurrentStage(int daysInCurrentStage) {
        this.daysInCurrentStage += daysInCurrentStage;
    }

    public void setFruitIsRipen(boolean fruitIsRipen) {
        this.fruitIsRipen = fruitIsRipen;
    }

    public void setFertilized(boolean fertilized) {
        this.fertilized = fertilized;
    }

    public void setWatered(boolean watered) {
        this.watered = watered;
        if (watered)
            setNumberOfDaysWithoutWater(0);
    }

    public void setNumberOfDaysWithoutWater(int numberOfDaysWithoutWater) {
        this.numberOfDaysWithoutWater = numberOfDaysWithoutWater;
    }

    public void increaseNumberOfDaysWithoutWater() {
        this.numberOfDaysWithoutWater++;
    }

    public boolean isDead() {
        return (!watered && !fertilized && numberOfDaysWithoutWater > 0);
//        if(!watered && !fertilized && numberOfDaysWithoutWater > 0){
//            Tile tile = player.getPlants().get(this);
//            tile.setThingOnTile(null);
//            player.getPlants().remove(this);
//        }
        // commands moved to each objects' nextDayUpdate();
    }


    @Override
    public void nextDayUpdate() {
        if(this.currentStage <= this.maxStages){
            this.daysInCurrentStage++;
            this.daysInCurrentStage = this.daysInCurrentStage % this.stages[this.currentStage];
            if(this.daysInCurrentStage == 0){
                this.currentStage++;
                if(this.currentStage > this.maxStages){
                    this.fruitIsRipen = true;
                }
            }
        }
        else if(!this.fruitIsRipen){
            this.daysInCurrentStage++;
            this.daysInCurrentStage = this.daysInCurrentStage % regrowthTime;
            if(this.daysInCurrentStage == 0){
                this.fruitIsRipen = true;
            }
        }

        if (!watered)
            numberOfDaysWithoutWater++;

        watered = false;
    }
}
