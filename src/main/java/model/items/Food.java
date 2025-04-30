package model.items;

import model.enums.SkillType;

public class Food extends Item {

    private final int energy;
    private final int sellPrice;
    private final SkillType skillType;
    private final int energyBuff;
    private final int buffHours;

    public Food(String name, int energy, int sellPrice, SkillType skillType, int energyBuff, int buffHours) {
        super(name);
        this.energy = energy;
        this.sellPrice = sellPrice;
        this.skillType = skillType;
        this.energyBuff = energyBuff;
        this.buffHours = buffHours;
    }

    public int getEnergy() {
        return energy;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public SkillType getSkillType() {
        return skillType;
    }

    public int getEnergyBuff() {
        return energyBuff;
    }

    public int getBuffHours() {
        return buffHours;
    }

}
