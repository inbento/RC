package com.example.rc.models;

public class King {
    private int imageRes;
    private String name;
    private String description;
    private int faction;

    public King(int imageRes, String name, String description, int faction) {
        this.imageRes = imageRes;
        this.name = name;
        this.description = description;
        this.faction = faction;
    }

    // Getters
    public int getImageRes() { return imageRes; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getFaction() { return faction; }
}