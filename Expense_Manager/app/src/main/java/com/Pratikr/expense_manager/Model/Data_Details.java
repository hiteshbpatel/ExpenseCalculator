package com.supriyalahade.expense_manager.Model;

/**
 * Created by BIDWAI on 14-09-2016.
 */
public class Data_Details {

    String date;
    String item;
    String cost;

    public Data_Details() {
    }

    public Data_Details(String date, String item, String cost) {
        this.date = date;
        this.item = item;
        this.cost = cost;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}
