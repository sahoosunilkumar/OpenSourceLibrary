package com.sunilsahoo.viewmodelbinding.common;

import android.support.annotation.LayoutRes;

import java.util.List;

public class ViewDataHolder {
    @LayoutRes
    private int layoutId;

    private List<? extends ViewModel> viewModelList;

    public ViewDataHolder(@LayoutRes int layoutId, List<? extends ViewModel> viewModelList) {
        this.viewModelList = viewModelList;
        this.layoutId = layoutId;
    }

    public int getLayoutId() {
        return layoutId;
    }

    public List<? extends ViewModel> getViewModelArr() {
        return viewModelList;
    }

}
