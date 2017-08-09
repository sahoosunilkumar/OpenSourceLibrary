package com.sunilsahoo.poc.mvvm.libtest;

import android.databinding.ObservableField;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.sunilsahoo.poc.mvvm.databinding.LibTestBinding;
import com.sunilsahoo.viewmodelbinding.common.ViewModel;

import java.util.ArrayList;
import java.util.List;


public class LibTestViewModel extends ViewModel {

	public ObservableField<String> name = new ObservableField<>();
	private SampleDialogFragment dialog;
	public List<Item> itemList = new ArrayList<>();

	public LibTestViewModel(int variableId) {
		super(variableId);
	}


	public void onClickGreetButton(View v) {
		LibTestBinding binding = getBinding();
		name.set(binding.nameEditText.getText().toString());
		itemList.add(new Item(name.get()+" "+System.currentTimeMillis(), 100, "10%"));
		itemList.add(new Item(name.get()+" "+System.currentTimeMillis(), 90, "5%"));
		itemList.add(new Item(name.get()+" "+System.currentTimeMillis(), 70, "35%"));
		Log.d("Sunil", binding.getUserViewModel().getName()+" item list on click :"+itemList);
	}


	public void onClickedShowDialogFragmentButton(View v) {
		dialog = SampleDialogFragment.newInstance();

		dialog.setListener(new SampleDialogViewModel.SampleDialogListener() {
			@Override
			public void onButtonClicked() {
				if(getView() != null)
				Toast.makeText(getView().getContext(), "Button in dialog clicked", Toast.LENGTH_SHORT).show();
			}
		});
		dialog.show(((AppCompatActivity) getView().getActivity()).getSupportFragmentManager(), "sample");
	}


}
