package com.sunilsahoo.poc.mvvm.libtest;

/**
 * Created by sunilkumarsahoo on 12/17/16.
 */

public class Item {
    private String name;
    private double price;
    private String offer;

    Item(String name, double price, String offer){
        this.name = name;
        this.price = price;
        this.offer = offer;
    }

    public String getOffer() {
        return offer;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}
