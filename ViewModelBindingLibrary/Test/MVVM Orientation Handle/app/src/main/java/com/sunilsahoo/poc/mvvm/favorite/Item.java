package com.sunilsahoo.poc.mvvm.favorite;

/**
 * Created by sunilkumarsahoo on 12/9/16.
 */

public class Item {
    private String name;
    private String price;
    private String offer;

    public Item(String name, String price, String offer) {
        this.name = name;
        this.price = price;
        this.offer = offer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }
}
