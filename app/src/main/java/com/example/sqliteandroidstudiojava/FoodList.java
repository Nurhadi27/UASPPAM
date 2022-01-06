package com.example.sqliteandroidstudiojava;
public class FoodList {

    private int id;
    private String name;
    private int score;
    private String description;
    private String url;

    public FoodList() {
    }

    public FoodList(String name, int score, String description, String url) {
        this.name = name;
        this.score = score;
        this.description = description;
        this.url = url;
    }

    public FoodList(int id, String name, int score, String description, String url) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.description = description;
        this.url = url;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
