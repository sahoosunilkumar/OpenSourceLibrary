package com.sunilsahoo.poc.mvvm.favorite;

import android.databinding.BindingAdapter;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sunilsahoo.poc.mvvm.R;
import com.sunilsahoo.poc.mvvm.common.BaseActivity;
import com.sunilsahoo.poc.mvvm.databinding.ActivityMainBinding;
import com.sunilsahoo.viewmodelbinding.BR;
import com.sunilsahoo.viewmodelbinding.common.ViewDataHolder;
import com.sunilsahoo.viewmodelbinding.common.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class FavoriteActivity extends BaseActivity {
    ActivityMainBinding activityMainBinding;

    @BindingAdapter("bind:list")
    public static void bindList(RecyclerView view, FavoriteViewModel
            viewModel) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(view
                .getContext());
        view.setLayoutManager(layoutManager);
        view.setAdapter(new ItemAdapter(viewModel.getFavoriteList()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager
                (getApplicationContext());
        activityMainBinding = ((ActivityMainBinding) getBinding());
        activityMainBinding.recyclerView.setLayoutManager(mLayoutManager);
        activityMainBinding.recyclerView.setItemAnimator(new
                DefaultItemAnimator());

        activityMainBinding.recyclerView.addOnItemTouchListener(new
                RecyclerTouchListener
                (getApplicationContext(), activityMainBinding.recyclerView,
                        new RecyclerTouchListener.ClickListener() {
                            @Override
                            public void onClick(View view, int position) {
                                activityMainBinding
                                        .getFavoriteViewModel
                                                ().onItemClicked(position);
                            }

                            @Override
                            public void onLongClick(View view, int position) {

                            }
                        }));
    }

    @Override
    protected void onResume() {
        super.onResume();
        FavoriteViewModel viewModel = getViewModel(BR.favoriteViewModel);
        viewModel.setTitle(getResources().getString(R.string.title_favorite));
    }


    @Override
    public ViewDataHolder onBindViewDataHolder() {
        List<FavoriteViewModel> viewModelList = new ArrayList<>();
        viewModelList.add(new FavoriteViewModel(BR.favoriteViewModel, this));
        return new ViewDataHolder(R.layout.activity_main, viewModelList);
    }
}
