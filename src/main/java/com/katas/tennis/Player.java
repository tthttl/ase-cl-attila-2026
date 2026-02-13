package com.katas.tennis;

public class Player {
    private final String name;
    private int points;

    public Player(String name) {
        this.name = name;
        this.points = 0;
    }

    public void addPoint() {
        this.points++;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }
}