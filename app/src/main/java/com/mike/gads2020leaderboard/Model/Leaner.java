package com.mike.gads2020leaderboard.Model;

public class Leaner {
    String name,country,badgeurl;
    int score,hours;

    public Leaner() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBadgeurl() {
        return badgeurl;
    }

    public void setBadgeurl(String badgeurl) {
        this.badgeurl = badgeurl;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }
}
