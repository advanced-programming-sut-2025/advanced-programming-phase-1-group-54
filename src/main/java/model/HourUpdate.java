package model;

public interface HourUpdate {
    // this interface is for classes that change state after a fixed amount of time has passed, like a timer.
    void nextHourUpdate(int amount);
}
