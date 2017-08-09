package com.sunilsahoo.poc.mvvm.networkinteractor;

import com.sunilsahoo.poc.mvvm.favorite.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class GetItemsInteractorImpl implements GetItemsInteractor {
    @Override
    public void findItems(final Callback callback) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                callback.onFinished(createArrayList(), "");
            }
        }, 2000);
    }

    private List<Item> createArrayList() {
        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item("Apple", "40rs/pc", "10% OFF"));
        itemList.add(new Item("Banana", "8rs/pc", "5% OFF"));
        itemList.add(new Item("Kiwi", "100rs/pc", "10% OFF"));
        itemList.add(new Item("Orange", "35rs/pc", "8% OFF"));
        itemList.add(new Item("Pineapple", "15rs/pc", "20% OFF"));
        itemList.add(new Item("Coconut", "10rs/pc", "5% OFF"));

        return itemList;
    }
}
