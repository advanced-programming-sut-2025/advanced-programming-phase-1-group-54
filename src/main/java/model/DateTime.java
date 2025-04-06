package model;

import model.enums.WeekDay;

public class DateTime {
    private int year;
    private int month;
    private int day;
    private int hour;
    private WeekDay weekDay;

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
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

    public void increaseMonth(int amount) {
        weekDay = WeekDay.values()[(weekDay.ordinal() + amount * 30)%7];
        year += (amount + month)/12;
        month = (amount + month)%12;
        if(month == 0){
            month = 12;
        }
    }

    public void increaseDay(int amount) {
        weekDay = WeekDay.values()[(amount + day + weekDay.ordinal())%7];
        increaseMonth((amount + day)/30);
        day = (amount + day)%30;
        if(day == 0){
            day = 30;
        }
    }

    public void increaseHour(int amount) {
        increaseDay((amount + hour)/24);
        hour = (amount + hour)%24;
    }

    public DateTime() {
        this.year = 0;
        this.month = 1;
        this.day = 1;
        this.hour = 9;
        this.weekDay = WeekDay.Monday;
    }
}
