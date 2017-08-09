package com.sunilsahoo.viewmodelbinding.common;

import android.app.Activity;
import android.content.Context;
import android.databinding.ViewDataBinding;

import java.util.List;

/**
 * Created by sunilkumarsahoo on 12/19/16.
 */
public interface IView {
    Context getContext();

    ViewDataBinding getBinding();

    Activity getActivity();

    ViewDataHolder onBindViewDataHolder();

    List<? extends ViewModel> getViewModels();

    <T extends ViewModel> T getViewModel(int viewModelId);
}
