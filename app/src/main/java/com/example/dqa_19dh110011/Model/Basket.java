package com.example.dqa_19dh110011.Model;

import java.io.Serializable;
import java.util.HashMap;

public class Basket implements Serializable {
    public HashMap<String, FoodBasket> foods;
    public double totalPrice;
    public int totalItem;

    public Basket(HashMap<String, FoodBasket> foodBasket, double price, int quantity) {
        foods = foodBasket;
        totalPrice = price;
        totalItem = quantity;
    }


    public void addFood(FoodBasket food) {
        foods.put(food.getFoodKey(), food);
    }

    public FoodBasket getFood(String key) {
        return foods.get(key);
    }

    public void calculateBasket() {
        totalPrice = 0;
        totalItem = 0;
        for (FoodBasket foodBasket : foods.values()) {
            totalPrice += (foodBasket.price * foodBasket.quantity);
            totalItem += foodBasket.quantity;
        }
    }
    public String getTotalPrice() {
        return totalPrice + " VND";
    }

    public int getTotalItem() {
        return totalItem;
    }

    @Override
    public String toString() {
        return "Basket{" +
                "foods=" + foods +
                ", totalPrice=" + totalPrice +
                ", totalItem=" + totalItem +
                '}';
    }
}

