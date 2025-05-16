package model;

import model.enums.Season;
import model.enums.WeekDay;

public class DateTime {
    private int year;
    private int day;
    private int hour;
    private WeekDay weekDay;
    private Season season;

    private static final int START_HOUR = 9;
    private static final int END_HOUR = 22;
    private static final int HOURS_IN_DAY = 24;
    private static final int DAY_TIME = END_HOUR - START_HOUR;
    private static final int NIGHT_TIME = HOURS_IN_DAY - DAY_TIME;
    private static final int DAYS_IN_WEEK = WeekDay.values().length;
    private static final int DAYS_IN_SEASON = 28;
    private static final int SEASONS_IN_YEAR = Season.values().length;

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

    public static int getStartHour() {
        return START_HOUR;
    }

    public static int getEndHour() {
        return END_HOUR;
    }

    public static int getHoursInDay() {
        return HOURS_IN_DAY;
    }

    public static int getDayTime() {
        return DAY_TIME;
    }

    public static int getNightTime() {
        return NIGHT_TIME;
    }

    public static int getDaysInWeek() {
        return DAYS_IN_WEEK;
    }

    public static int getDaysInSeason() {
        return DAYS_IN_SEASON;
    }

    public static int getSeasonsInYear() {
        return SEASONS_IN_YEAR;
    }

    public void increaseDay(int amount) {
        weekDay = WeekDay.values()[(amount + weekDay.ordinal())%DAYS_IN_WEEK];
        year += (((amount + day)/DAYS_IN_SEASON)+ season.ordinal())/SEASONS_IN_YEAR;
        season = Season.values()[(((amount + day)/DAYS_IN_SEASON)+ season.ordinal())%SEASONS_IN_YEAR];
        day = (amount + day)%DAYS_IN_SEASON;
        if(day == 0){
            day = DAYS_IN_SEASON;
        }
    }

    public void increaseHour(int amount) {
        increaseDay((amount + hour - START_HOUR) / DAY_TIME);
        hour = START_HOUR + (amount + hour - START_HOUR) % DAY_TIME;
    }

    public DateTime() {
        this.year = 0;
        this.day = 1;
        this.hour = 9;
        this.weekDay = WeekDay.MONDAY;
        this.season = Season.SPRING;
    }

    public DateTime(DateTime dateTime) { // basically clone()
        this.year = dateTime.year;
        this.day = dateTime.day;
        this.hour = dateTime.hour;
        this.weekDay = dateTime.weekDay;
        this.season = dateTime.season;
    }

}
