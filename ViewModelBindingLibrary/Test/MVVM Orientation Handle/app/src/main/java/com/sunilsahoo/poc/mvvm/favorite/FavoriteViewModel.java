package com.sunilsahoo.poc.mvvm.favorite;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;

import com.sunilsahoo.poc.mvvm.BR;
import com.sunilsahoo.poc.mvvm.common.MessageHelper;
import com.sunilsahoo.poc.mvvm.networkinteractor.GetItemsInteractor;
import com.sunilsahoo.poc.mvvm.networkinteractor.GetItemsInteractorImpl;
import com.sunilsahoo.viewmodelbinding.common.ViewModel;

import java.util.List;

/**
 * Created by sunilkumarsahoo on 12/9/16.
 */

public class FavoriteViewModel extends ViewModel {

    public ObservableField<Boolean> showProgress = new ObservableField<>(false);
    private GetItemsInteractor findItemsInteractor = new
            GetItemsInteractorImpl();
    private String title = "";
    private ObservableList<Item> favoriteList = new ObservableArrayList<>();
    private GetItemsInteractor.Callback callback = new
            GetItemsInteractorCallback();
    private MessageHelper messageHelper;

    public FavoriteViewModel(int variableId, MessageHelper messageHelper) {
        super(variableId);
        this.messageHelper = messageHelper;
        this.addOnPropertyChangedCallback(new PropertyChangeCallback());
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    public ObservableList<Item> getFavoriteList() {
        return favoriteList;
    }

    public void setFavoriteList(List<Item> favoriteList) {
        this.favoriteList.clear();
        this.favoriteList.addAll(favoriteList);
    }


    public void onItemClicked(int position) {
        String message = getFavoriteList().get
                (position)
                .getName()
                + " is selected ";
        if (messageHelper != null) {
            messageHelper.showMessage(message);
        }
    }

    public void getItems() {
        showProgress.set(true);
        findItemsInteractor.findItems(callback);
    }


    public void onSuccess(List<Item> items) {
        setFavoriteList(items);
        showProgress.set(false);
    }


    public void onFailure(String message) {
        showProgress.set(false);
        messageHelper.showMessage(message);
    }

    class GetItemsInteractorCallback implements GetItemsInteractorImpl
            .Callback {

        @Override
        public void onFinished(List<Item> items, String message) {
            if ((items != null) && !items.isEmpty()) {
                onSuccess(items);
            } else {
                onFailure(message);
            }
        }
    }

    class PropertyChangeCallback extends OnPropertyChangedCallback {

        @Override
        public void onPropertyChanged(Observable observable, int i) {
            if (i == BR.title) {
                getItems();
            }
        }
    }
}

