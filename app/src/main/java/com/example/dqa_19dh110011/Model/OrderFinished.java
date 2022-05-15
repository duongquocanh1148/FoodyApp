package com.example.dqa_19dh110011.Model;

import com.example.dqa_19dh110011.Model.FoodBasket;

import java.util.ArrayList;
import java.util.List;

public class OrderFinished extends FoodBasket {
    String orderID;
    String orderDate;
    String orderSum;
    int orderStatus;
    String userUID;
    ArrayList<FoodBasket> foodBaskets;
    public OrderFinished() {
    }

    public OrderFinished(String orderID, String orderDate, String orderSum, int orderStatus, String userUID, ArrayList<FoodBasket> foodBaskets) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.orderSum = orderSum;
        this.orderStatus = orderStatus;
        this.userUID = userUID;
        this.foodBaskets = foodBaskets;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderSum() {
        return orderSum;
    }

    public void setOrderSum(String orderSum) {
        this.orderSum = orderSum;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getUserUID() {
        return userUID;
    }

    public void setUserUID(String userUID) {
        this.userUID = userUID;
    }

    public ArrayList<FoodBasket> getFoodBaskets() {
        return foodBaskets;
    }

    public void setFoodBaskets(ArrayList<FoodBasket> foodBaskets) {
        this.foodBaskets = foodBaskets;
    }
}
