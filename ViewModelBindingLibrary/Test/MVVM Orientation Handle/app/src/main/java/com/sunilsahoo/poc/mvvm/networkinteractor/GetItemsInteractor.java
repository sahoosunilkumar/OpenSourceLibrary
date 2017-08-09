package com.sunilsahoo.poc.mvvm.networkinteractor;

import com.sunilsahoo.poc.mvvm.favorite.Item;

import java.util.List;

public interface GetItemsInteractor {

    void findItems(Callback listener);

    interface Callback {
        void onFinished(List<Item> items, String message);
    }
}
