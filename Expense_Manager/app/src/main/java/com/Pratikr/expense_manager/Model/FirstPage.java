package com.supriyalahade.expense_manager.Model;

/**
 * Created by BIDWAI on 27-07-2016.
 */
public class FirstPage {

    String name;

    int image;

    String date;

    float value;

    public FirstPage() {

    }

    public FirstPage(String name) {
        this.name = name;
    }

    public FirstPage(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public FirstPage(String date,String name, float value ) {
        this.name = name;
        this.value = value;
        this.date = date;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
       this.date = date;
    }



    public void setName(String name) {
        this.name = name;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }



}
