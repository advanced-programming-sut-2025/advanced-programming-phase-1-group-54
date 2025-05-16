package model.relationships;

public class Friendship {
    protected int xp;
    protected int level;
    public Friendship() {
        this.xp = 0;
        this.level = 0;
    }

    public int getXP() {
        return xp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    public void increaseXP(int x){
        this.xp += x;
        if (this.xp > (this.level + 1) * 100){
            this.xp = 0;
            this.level++;
        }
        if (this.xp < 0){
            this.xp += this.level * 100;
            this.level--;
        }
    }
}
