package com.sunilsahoo.poc.mvvm.networkinteractor;

import android.text.TextUtils;

import java.util.Timer;
import java.util.TimerTask;

public class LoginInteractorImpl implements LoginInteractor {

    @Override
    public void login(final String username, final String password, final
    OnLoginFinishedListener listener) {

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                loginStatus(username, password, listener);
            }
        }, 2000);


    }

    public void loginStatus(final String username, final String password,
                            final OnLoginFinishedListener listener) {

        boolean error = false;
        if (TextUtils.isEmpty(username)) {
            listener.onUsernameError();
            error = true;
        }
        if (TextUtils.isEmpty(password)) {
            listener.onPasswordError();
            error = true;
        }
        if (!error) {
            listener.onSuccess();
        }
    }
}
