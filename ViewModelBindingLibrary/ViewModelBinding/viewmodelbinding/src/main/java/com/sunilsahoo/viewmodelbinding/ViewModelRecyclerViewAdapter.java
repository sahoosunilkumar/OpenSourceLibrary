package com.sunilsahoo.viewmodelbinding;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableList;
import android.databinding.ViewDataBinding;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.sunilsahoo.viewmodelbinding.common.BaseRecyclerViewHolder;

abstract public class ViewModelRecyclerViewAdapter<T extends ViewDataBinding>
        extends
        RecyclerView.Adapter<BaseRecyclerViewHolder<T>> {
    @LayoutRes
    private final int mLayoutId;
    private Handler adapterUpdateHandler = new Handler(Looper.getMainLooper());
    private RecyclerViewUpdater updater;
    private ObservableList.OnListChangedCallback<ObservableList<Object>>
            callback = new
            ObservableList.OnListChangedCallback<ObservableList<Object>>() {
                @Override
                public void onChanged(ObservableList<Object> sender) {
                    adapterUpdateHandler.post(updater);
                }

                @Override
                public void onItemRangeChanged(ObservableList<Object> sender,
                                               int
                                                       positionStart, int
                                                       itemCount) {
                    adapterUpdateHandler.post(updater);
                }

                @Override
                public void onItemRangeInserted(ObservableList<Object>
                                                        sender, int
                                                        positionStart, int
                                                        itemCount) {
                    adapterUpdateHandler.post(updater);
                }

                @Override
                public void onItemRangeMoved(ObservableList<Object> sender, int
                        fromPosition, int toPosition, int itemCount) {
                    adapterUpdateHandler.post(updater);
                }

                @Override
                public void onItemRangeRemoved(ObservableList<Object> sender,
                                               int
                                                       positionStart, int
                                                       itemCount) {
                    adapterUpdateHandler.post(updater);
                }
            };

    public ViewModelRecyclerViewAdapter(@LayoutRes int layoutId) {
        mLayoutId = layoutId;
        this.updater = new RecyclerViewUpdater(this);
    }

    @Override
    public BaseRecyclerViewHolder<T> onCreateViewHolder(ViewGroup parent, int
            viewType) {
        return new BaseRecyclerViewHolder<>((T) DataBindingUtil.inflate
                (LayoutInflater.from(parent.getContext()),
                        mLayoutId, parent, false));
    }

    public ObservableList.OnListChangedCallback getObservableCallback() {
        return callback;
    }

    private class RecyclerViewUpdater implements Runnable {
        RecyclerView.Adapter adapter;

        RecyclerViewUpdater(RecyclerView.Adapter adapter) {
            this.adapter = adapter;
        }

        @Override
        public void run() {
            adapter.notifyDataSetChanged();
        }
    }
}
