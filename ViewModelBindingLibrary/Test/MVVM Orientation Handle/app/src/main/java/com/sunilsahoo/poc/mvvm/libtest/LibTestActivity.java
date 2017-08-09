package com.sunilsahoo.poc.mvvm.libtest;

import android.util.Log;

import com.sunilsahoo.poc.mvvm.BR;
import com.sunilsahoo.poc.mvvm.R;
import com.sunilsahoo.viewmodelbinding.ViewModelActivity;
import com.sunilsahoo.viewmodelbinding.common.ViewDataHolder;
import com.sunilsahoo.viewmodelbinding.common.ViewModel;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by jakubkinst on 10/11/15.
 */
public class LibTestActivity extends ViewModelActivity {

	@Override
	protected void onPostResume() {
		super.onPostResume();
		LibTestViewModel mainViewModel = getViewModel(BR.libTestViewModel);
//		User user = getViewModel(BR.userViewModel);
//		Log.d("Sunil", mainViewModel+" list :"+mainViewModel.itemList);
	}

	@Override
	public ViewDataHolder onBindViewDataHolder() {
		List<ViewModel> list = new ArrayList<>();
		list.add(new LibTestViewModel(BR.libTestViewModel));
		list.add(new User(BR.userViewModel));
		return new ViewDataHolder(R.layout.lib_test,list);
	}

}
