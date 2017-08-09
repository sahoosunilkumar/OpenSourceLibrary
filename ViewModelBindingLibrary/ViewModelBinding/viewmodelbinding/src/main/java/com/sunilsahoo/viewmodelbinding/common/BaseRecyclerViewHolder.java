package com.sunilsahoo.viewmodelbinding.common;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * base class view holder for recyclerview
 * Created by sunilkumarsahoo on 12/19/16.
 */
public class BaseRecyclerViewHolder<T extends ViewDataBinding> extends
        RecyclerView.ViewHolder {
    public final T binding;

    public BaseRecyclerViewHolder(T binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}
