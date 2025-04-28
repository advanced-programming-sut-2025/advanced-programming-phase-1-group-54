package model;

import model.enums.SkillType;

public class Skill {
    private SkillType skillType;
    private int xp;
    private int level;

    public void addXP(int amount) {
        this.xp += amount;
        while (this.level != 4 && this.xp > 100 * level + 50) {
            this.level++;
        }
    }
}
