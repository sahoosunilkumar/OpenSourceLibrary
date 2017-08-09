package com.sunilsahoo.poc.mvvm.login;

import android.content.Intent;

import com.sunilsahoo.poc.mvvm.R;
import com.sunilsahoo.poc.mvvm.databinding.ActivityLoginBinding;
import com.sunilsahoo.poc.mvvm.favorite.FavoriteActivity;
import com.sunilsahoo.poc.mvvm.libtest.LibTestActivity;
import com.sunilsahoo.viewmodelbinding.BR;
import com.sunilsahoo.viewmodelbinding.ViewModelActivity;
import com.sunilsahoo.viewmodelbinding.common.ViewDataHolder;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends ViewModelActivity implements ILoginView {

    @Override
    public void setUsernameError() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ((ActivityLoginBinding) getBinding()).username.setError
                        (getString(R.string.username_error));
            }
        });

    }

    @Override
    public void setPasswordError() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ((ActivityLoginBinding) getBinding()).password.setError
                        (getString(R.string.password_error));
            }
        });
    }

    @Override
    public void navigateToHome() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getBaseContext(), FavoriteActivity
                        .class));
                finish();
            }
        });
    }

    @Override
    public void navigateToTest() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getBaseContext(), LibTestActivity
                        .class));
                finish();
            }
        });
    }
    @Override
    public ViewDataHolder onBindViewDataHolder() {

        List<LoginViewModel> list = new ArrayList<>();
        list.add(new LoginViewModel(BR.loginViewModel, this));
        return new ViewDataHolder(R.layout.activity_login, list);
    }
}
