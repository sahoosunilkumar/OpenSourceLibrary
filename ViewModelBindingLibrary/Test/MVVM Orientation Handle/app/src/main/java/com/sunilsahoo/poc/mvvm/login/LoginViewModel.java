package com.sunilsahoo.poc.mvvm.login;

import android.databinding.ObservableField;

import com.sunilsahoo.poc.mvvm.networkinteractor.LoginInteractor;
import com.sunilsahoo.poc.mvvm.networkinteractor.LoginInteractorImpl;
import com.sunilsahoo.viewmodelbinding.common.ViewModel;

public class LoginViewModel extends ViewModel implements LoginInteractor
        .OnLoginFinishedListener {
    public ObservableField<Boolean> showProgress = new ObservableField<>(false);
    public LoginBean loginBean = new LoginBean();
    private LoginInteractor loginInteractor;
    private ILoginView loginView;

    public LoginViewModel(int variableId, ILoginView loginView) {
        super(variableId);
        this.loginView = loginView;
        this.loginInteractor = new LoginInteractorImpl();
    }

    public void validateCredentials() {
        showProgress.set(true);
        loginInteractor.login(loginBean.getUserName(), loginBean.getPassword
                (), this);
    }


    @Override
    public void onUsernameError() {
        if (loginView != null) {
            this.loginView.setUsernameError();
        }
        showProgress.set(false);
    }

    @Override
    public void onPasswordError() {
        if (loginView != null) {
            this.loginView.setPasswordError();
        }
        showProgress.set(false);
    }

    @Override
    public void onSuccess() {
        if (loginView == null) {
            return;
        }
        loginView.navigateToHome();
    }

    public void onLoginClick() {
        validateCredentials();
    }

    public void onLibTestClick() {
        if (loginView == null) {
            return;
        }
        loginView.navigateToTest();
    }
}
