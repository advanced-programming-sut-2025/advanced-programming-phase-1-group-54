package io.github.stardewmini.model;

public interface HourCheck {
    // this interface is for classes that change state based on what time of day it is; like shops.
    void checkHour(int time);
}
