package com.sunilsahoo.poc.mvvm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.sunilsahoo.poc.mvvm.favorite.FavoriteActivity;
import com.sunilsahoo.poc.mvvm.favorite.FavoriteViewModel;
import com.sunilsahoo.poc.mvvm.favorite.Item;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;

//import com.sunilsahoo.poc.mvvm.favorite.FavoritePresenterImpl;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class FavoriteViewModelTest {
    @Test
    public void launchActivityTest() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        Intent intent = new Intent(appContext, FavoriteActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Activity activity = InstrumentationRegistry.getInstrumentation()
                .startActivitySync(intent);

        assertNotNull(activity);
    }


    @Test
    public void updateFavoritesTitleTest() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        Intent intent = new Intent(appContext, FavoriteActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Activity activity = InstrumentationRegistry.getInstrumentation()
                .startActivitySync(intent);

        FavoriteActivity favoriteActivity = (FavoriteActivity) activity;
        Thread.sleep(5000);
        FavoriteViewModel viewModel = favoriteActivity
                .getViewModel(BR.favoriteViewModel);
        String insertTitle = "Recent List";
        viewModel.setTitle(insertTitle);


        Thread.sleep(10000);
    }


    @Test
    public void updateFavoritesListTest() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        Intent intent = new Intent(appContext, FavoriteActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Activity activity = InstrumentationRegistry.getInstrumentation()
                .startActivitySync(intent);

        FavoriteActivity favoriteActivity = (FavoriteActivity) activity;
        Thread.sleep(5000);
        FavoriteViewModel viewModel = favoriteActivity
                .getViewModel(BR.favoriteViewModel);
        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item("Tesco Bread", "4.0", "50%"));
        viewModel.setFavoriteList(itemList);
        Thread.sleep(5000);
    }

    @Test
    public void updateFavoritesListFailureTest() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        Intent intent = new Intent(appContext, FavoriteActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Activity activity = InstrumentationRegistry.getInstrumentation()
                .startActivitySync(intent);

        FavoriteActivity favoriteActivity = (FavoriteActivity) activity;
        Thread.sleep(5000);
        FavoriteViewModel viewModel =  favoriteActivity
                .getViewModel(BR.favoriteViewModel);
        viewModel.onFailure("Server is Unavailable");
        Thread.sleep(5000);
    }

}
