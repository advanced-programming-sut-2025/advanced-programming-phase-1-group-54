package model.items.plants;

import java.util.HashMap;

public class Crop extends Plant {

    public static HashMap<String,Crop> crops;

    private boolean oneTime;
    private boolean canBecomeGiant;

    public boolean isOneTime() {
        return oneTime;
    }

    public boolean isCanBecomeGiant() {
        return canBecomeGiant;
    }

    public void setOneTime(boolean oneTime) {
        this.oneTime = oneTime;
    }

    public void setCanBecomeGiant(boolean canBecomeGiant) {
        this.canBecomeGiant = canBecomeGiant;
    }

}
