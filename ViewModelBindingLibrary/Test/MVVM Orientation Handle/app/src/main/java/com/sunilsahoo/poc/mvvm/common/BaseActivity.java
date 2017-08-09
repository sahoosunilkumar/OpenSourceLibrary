package com.sunilsahoo.poc.mvvm.common;

import android.widget.Toast;

import com.sunilsahoo.viewmodelbinding.ViewModelActivity;

/**
 * Created by sunilkumarsahoo on 12/14/16.
 */

public abstract class BaseActivity extends ViewModelActivity implements
        MessageHelper {

    @Override
    public void showMessage(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(BaseActivity.this, message, Toast
                        .LENGTH_LONG)
                        .show();
            }
        });
    }

    public MessageHelper getMessageHelper() {
        return this;
    }
}
