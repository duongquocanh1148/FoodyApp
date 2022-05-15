package com.example.dqa_19dh110011.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Cart {
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "foodKey")
    String foodKey;
    @ColumnInfo(name = "foodName")
    String foodName;
    @ColumnInfo(name = "foodPrice")
    int foodPrice;
    @ColumnInfo(name= "image")
    String foodImage;
    @ColumnInfo(name = "rate")
    int foodRate;
    @ColumnInfo(name = "reskey")
    String resKey;
    @ColumnInfo(name = "quantity")
    int quantity;
    @ColumnInfo(name = "sum")
    double sum;
    @Ignore
    public Cart() {
    }

    public Cart(String foodKey, String foodName, int foodPrice, String foodImage, int foodRate, String resKey, int quantity, double sum) {
        this.foodKey = foodKey;
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.foodImage = foodImage;
        this.foodRate = foodRate;
        this.resKey = resKey;
        this.quantity = quantity;
        this.sum = sum;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoodKey() {
        return foodKey;
    }

    public void setFoodKey(String foodKey) {
        this.foodKey = foodKey;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(int foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(String foodImage) {
        this.foodImage = foodImage;
    }

    public int getFoodRate() {
        return foodRate;
    }
    public void setFoodRate(int foodRate) {
        this.foodRate = foodRate;
    }

    public String getResKey() {
        return resKey;
    }

    public void setResKey(String resKey) {
        this.resKey = resKey;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
}

