package model;

public class DateTime {
    private int year;
    private int month;
    private int day;
    private int hour;

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

    public void increaseMonth(int amount) {
        year += (amount + month)/12;
        month = (amount + month)%12;
        if(month == 0){
            month = 12;
        }
    }

    public void increaseDay(int amount) {
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
}
