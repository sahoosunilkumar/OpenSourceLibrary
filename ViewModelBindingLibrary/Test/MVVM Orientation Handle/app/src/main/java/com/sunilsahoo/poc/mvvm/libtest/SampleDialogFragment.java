package com.sunilsahoo.poc.mvvm.libtest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.sunilsahoo.poc.mvvm.BR;
import com.sunilsahoo.poc.mvvm.R;
import com.sunilsahoo.viewmodelbinding.ViewModelDialogFragment;
import com.sunilsahoo.viewmodelbinding.common.ViewDataHolder;
import com.sunilsahoo.viewmodelbinding.common.ViewModel;

import java.util.ArrayList;
import java.util.List;


public class SampleDialogFragment extends ViewModelDialogFragment {

	private SampleDialogViewModel.SampleDialogListener mListener;


	public static SampleDialogFragment newInstance() {
		Bundle args = new Bundle();
		SampleDialogFragment fragment = new SampleDialogFragment();
		fragment.setArguments(args);
		return fragment;
	}


	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		((SampleDialogViewModel)getViewModel(BR.viewModel)).setListener(mListener);
	}


	public void setListener(SampleDialogViewModel.SampleDialogListener listener) {
		mListener = listener;
	}
//
//	@Override
//	public SampleDialogViewModel onBindViewModel() {
//		return new SampleDialogViewModel(cz.kinst.jakub.sample.viewmodelbinding.BR.viewModel, R.layout.dialog_sample);
//	}

	@Override
	public ViewDataHolder onBindViewDataHolder() {
		ViewModel[] arr = new ViewModel[1];
		List<SampleDialogViewModel> list = new ArrayList<>();
		list.add(new SampleDialogViewModel(BR.viewModel));
		return new ViewDataHolder(R.layout.dialog_sample,list);
	}
}
