package com.sunilsahoo.viewmodelbinding;

import android.app.Activity;
import android.content.Context;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.sunilsahoo.viewmodelbinding.common.IView;
import com.sunilsahoo.viewmodelbinding.common.ViewModel;
import com.sunilsahoo.viewmodelbinding.common.ViewModelBinder;

import java.util.List;

/**
 * Base class for all activities dealing with viewmodel
 */
public abstract class ViewModelActivity extends AppCompatActivity implements
        IView {
    private final ViewModelBinder mViewModelBinder = new
            ViewModelBinder();

    @Override
    public void onDestroy() {
        mViewModelBinder.onDestroy(this);
        super.onDestroy();
    }

    @Override
    public <T extends ViewModel> T getViewModel(int variableId) {
        for (ViewModel viewModel : mViewModelBinder.getViewModel()) {
            if (viewModel.getVariableId() == variableId) {
                return (T) viewModel;
            }
        }
        return null;
    }

    @Override
    public List<? extends ViewModel> getViewModels() {
        return mViewModelBinder.getViewModel();
    }

    @Override
    public ViewDataBinding getBinding() {
        return mViewModelBinder.getBinding();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        mViewModelBinder.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModelBinder.onCreate(this, savedInstanceState);
    }

    @Override
    public Context getContext() {
        return getActivity();
    }

    @Override
    public Activity getActivity() {
        return this;
    }


}
