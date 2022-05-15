package com.example.dqa_19dh110011.Model;

import com.example.dqa_19dh110011.Model.Food;

public class FoodBasket extends Food {
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int quantity;
    public double sum;
    public Food food;

    public FoodBasket() {
        super();
    }

    public FoodBasket(String name, String image, int price, int quantity, int sum) {
        this.name = name;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
        this.sum = sum;
    }

    public FoodBasket(Food food, int quantity, double sum){
        this.name = food.getName();
        this.image = food.getImage();
        this.price = food.getPrice();
        this.rate = food.getRate();
        this.resKey = food.getResKey();
        this.foodKey = food.getFoodKey();
        this.quantity = quantity;
        this.sum = sum;
    }
    public void increase() {
        quantity++;
        sum = price * quantity;
    }

    public void decrease() {
        if (quantity > 0) {
            quantity--;
            sum = price * quantity;
        }
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public double getSum() {
        return sum ;
    }

    public int getQuantity(){
        return quantity;
    }



    @Override
    public String toString() {
        return "FoodBasket{" +
                "quantity=" + quantity +
                ", sum=" + sum +
                ", resKey='" + resKey + '\'' +
                '}';
    }
}
