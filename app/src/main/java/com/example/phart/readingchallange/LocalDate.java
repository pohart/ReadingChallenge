package com.example.phart.readingchallange;

/**
 * Created by PHART on 3/25/2018.
 */

public class LocalDate {
    private int year;
    private int month;
    private int day;

    public LocalDate(int year, int month, int day) {

        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(final int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(final int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(final int day) {
        this.day = day;
    }

    public int compareTo(final LocalDate dateCompleted) {
        int compareTo = this.year - dateCompleted.year;
        if (compareTo != 0) {
            return compareTo;
        }
        compareTo = this.month - dateCompleted.month;
        if (compareTo != 0) {
            return compareTo;
        }
        return this.day - dateCompleted.day;

    }
}
