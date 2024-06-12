package com.ufv.dis.front.models;

public class Character {

    private int id;

    private String name;

    private String ki;

    private String maxKi;

    private String race;

    private String image;

    private String description;

    public Character(int id, String name, String ki, String maxKi, String race, String image, String description) {
        this.id = id;
        this.name = name;
        this.ki = ki;
        this.maxKi = maxKi;
        this.race = race;
        this.image = image;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getKi() {
        return ki;
    }

    public void setKi(String ki) {
        this.ki = ki;
    }

    public String getMaxKi() {
        return maxKi;
    }

    public void setMaxKi(String maxKi) {
        this.maxKi = maxKi;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Character{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ki='" + ki + '\'' +
                ", maxKi='" + maxKi + '\'' +
                ", race='" + race + '\'' +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
