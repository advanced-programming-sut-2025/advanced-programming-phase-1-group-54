package model;

import model.enums.Season;
import model.enums.WeekDay;

public class DateTime {
    private int year;
    private int day;
    private int hour;
    private WeekDay weekDay;
    private Season season;

    public int getYear() {
        return year;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public WeekDay getWeekDay() {
        return weekDay;
    }

    public Season getSeason() {
        return season;
    }

    public void increaseDay(int amount) {
        weekDay = WeekDay.values()[(amount + day + weekDay.ordinal())%7];
        season = Season.values()[(((amount + day)/28)+ season.ordinal())%4];
        day = (amount + day)%28;
        if(day == 0){
            day = 28;
        }
    }

    public void increaseHour(int amount) {
        increaseDay((amount + hour)/24);
        hour = (amount + hour)%24;
    }

    public DateTime() {
        this.year = 0;
        this.day = 1;
        this.hour = 9;
        this.weekDay = WeekDay.Monday;
    }
}
