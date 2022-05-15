package com.example.dqa_19dh110011.Model;

import java.io.Serializable;

public class Food implements Serializable, Comparable<Food> {
    String name;
    String image;
    int price;
    int rate;
    String resKey;
    String foodKey;

    public Food() {
    }

    public Food(String name, String image, int price, int rate, String resKey, String foodKey) {
        this.name = name;
        this.image = image;
        this.price = price;
        this.rate = rate;
        this.resKey = resKey;
        this.foodKey = foodKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getResKey() {
        return resKey;
    }

    public void setResKey(String resKey) {
        this.resKey = resKey;
    }

    public String getFoodKey() {
        return foodKey;
    }

    public void setFoodKey(String foodKey) {
        this.foodKey = foodKey;
    }

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", image=" + image +
                ", price=" + price +
                ", rate=" + rate +
                ", resKey='" + resKey + '\'' +
                ", foodKey='" + foodKey + '\'' +
                '}';
    }

    @Override
    public int compareTo(Food food) {
        return Integer.compare(food.rate, rate);
    }
}