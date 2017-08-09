package com.sunilsahoo.poc.mvvm.libtest;

import android.view.View;

import com.sunilsahoo.viewmodelbinding.common.ViewModel;


public class SampleDialogViewModel extends ViewModel {
	SampleDialogListener mListener;

	public SampleDialogViewModel(int variableId) {
		super(variableId);
	}


	public interface SampleDialogListener {
		void onButtonClicked();
	}


	public void onClickedButton(View v) {
		if(mListener != null)
			mListener.onButtonClicked();
	}


	public void setListener(SampleDialogListener listener) {
		mListener = listener;
	}
}
