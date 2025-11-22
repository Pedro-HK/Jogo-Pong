package com.example;


public class Player {
    private int id;
    private String name;
    private int wins;
    private int defeats;

    public Player(int id, String name, int wins, int defeats) {
        this.id = id;
        this.name = name;
        this.wins = wins;
        this.defeats = defeats;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getDefeats() {
        return defeats;
    }

    public void setDefeats(int defeats) {
        this.defeats = defeats;
    }
}
