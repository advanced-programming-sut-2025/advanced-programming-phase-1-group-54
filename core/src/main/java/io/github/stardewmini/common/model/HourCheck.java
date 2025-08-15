package io.github.stardewmini.common.model;

public interface HourCheck {
    // this interface is for classes that change state based on what time of day it is; like shops.
    void checkHour(int time);
}
