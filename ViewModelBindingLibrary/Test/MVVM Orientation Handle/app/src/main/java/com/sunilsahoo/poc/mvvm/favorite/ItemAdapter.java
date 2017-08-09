package com.sunilsahoo.poc.mvvm.favorite;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.sunilsahoo.poc.mvvm.R;
import com.sunilsahoo.poc.mvvm.databinding.ItemRowBinding;
import com.sunilsahoo.viewmodelbinding.ViewModelRecyclerViewAdapter;

/**
 * Created by sunilkumarsahoo on 12/17/16.
 */

public class ItemAdapter extends ViewModelRecyclerViewAdapter<ItemRowBinding> {
    private ObservableList<Item> list = new ObservableArrayList<>();

    public ItemAdapter(ObservableList<Item> list) {
        super(R.layout.item_row);
        this.list = list;
        this.list.addOnListChangedCallback
                (super.getObservableCallback());
    }

    @Override
    public void onBindViewHolder(com.sunilsahoo.viewmodelbinding.common
                                             .BaseRecyclerViewHolder<ItemRowBinding> holder, int position) {
        holder.binding.setItem(list.get
                (position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}