package com.sunilsahoo.viewmodelbinding;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sunilsahoo.viewmodelbinding.common.IView;
import com.sunilsahoo.viewmodelbinding.common.ViewModel;
import com.sunilsahoo.viewmodelbinding.common.ViewModelBinder;

import java.util.List;

/**
 * Base class for all fragments dealing with viewmodel
 */
public abstract class ViewModelFragment extends Fragment implements IView {

    private final ViewModelBinder mViewModelBinder = new
            ViewModelBinder();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mViewModelBinder.onCreate(this, savedInstanceState);
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mViewModelBinder.onCreate(this, savedInstanceState);
        return mViewModelBinder.getBinding().getRoot();
    }

    @Override
    public void onDestroyView() {
        mViewModelBinder.onDestroyView(this);
        super.onDestroyView();
    }


    @Override
    public void onDestroy() {
        mViewModelBinder.onDestroy(this);
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        mViewModelBinder.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }


    @Override
    public Context getContext() {
        return getActivity();
    }


    @Override
    public ViewModel getViewModel(int variableId) {
        for (ViewModel viewModel : mViewModelBinder.getViewModel()) {
            if (viewModel.getVariableId() == variableId) {
                return viewModel;
            }
        }
        return null;
//        return mViewModelBinder.getViewModel();
    }

    @Override
    public List<? extends ViewModel> getViewModels() {
        return mViewModelBinder.getViewModel();
    }


    public ViewDataBinding getBinding() {
        return mViewModelBinder.getBinding();
    }
}
