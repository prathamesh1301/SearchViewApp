package com.example.searchviewapp;

public class FoodModel {
    private String foodName;
    private int foodImg;

    public FoodModel(String foodName, int foodImg) {
        this.foodName = foodName;
        this.foodImg = foodImg;
    }

    public String getFoodName() {
        return foodName;
    }

    public int getFoodImg() {
        return foodImg;
    }
}
