package com.dsaved.gadsleaderboard.model;

public class SkillIQModel {
    private String name, country, badgeUrl;
    private int score;

    public SkillIQModel(String name, int score, String country, String badgeUrl) {
        this.name = name;
        this.score = score;
        this.country = country;
        this.badgeUrl = badgeUrl;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }

    public String getCountry() {
        return country;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }
}
