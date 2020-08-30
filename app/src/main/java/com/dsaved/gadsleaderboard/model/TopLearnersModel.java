package com.dsaved.gadsleaderboard.model;

public class TopLearnersModel {
    private String name, country, badgeUrl;
    private int hours;

    public TopLearnersModel(String name, int hours, String country, String badgeUrl) {
        this.name = name;
        this.hours = hours;
        this.country = country;
        this.badgeUrl = badgeUrl;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }

    public String getCountry() {
        return country;
    }

    public int getHours() {
        return hours;
    }

    public String getName() {
        return name;
    }
}
