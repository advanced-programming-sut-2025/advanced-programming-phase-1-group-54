package model.relationships;

public abstract class Friendship {
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
}
