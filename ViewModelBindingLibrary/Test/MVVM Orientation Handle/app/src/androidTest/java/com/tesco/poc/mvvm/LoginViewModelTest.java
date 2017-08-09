package com.sunilsahoo.poc.mvvm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.sunilsahoo.poc.mvvm.login.LoginActivity;
import com.sunilsahoo.poc.mvvm.login.LoginViewModel;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotNull;

//import com.sunilsahoo.poc.mvvm.favorite.FavoritePresenterImpl;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class LoginViewModelTest {
    @Test
    public void launchActivityTest() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        Intent intent = new Intent(appContext, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Activity activity = InstrumentationRegistry.getInstrumentation()
                .startActivitySync(intent);

        assertNotNull(activity);
    }


    @Test
    public void loginFailurePasswordTest() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        Intent intent = new Intent(appContext, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Activity activity = InstrumentationRegistry.getInstrumentation()
                .startActivitySync(intent);
        LoginActivity favoriteActivity = (LoginActivity) activity;
        Thread.sleep(2000);
        LoginViewModel viewModel = favoriteActivity
                .getViewModel(BR.loginViewModel);
        viewModel.loginBean.setPassword("");
        viewModel.loginBean.setUserName("Tesco");
        viewModel.onLoginClick();
        Thread.sleep(2000);
    }

    @Test
    public void loginFailureUsernameTest() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        Intent intent = new Intent(appContext, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Activity activity = InstrumentationRegistry.getInstrumentation()
                .startActivitySync(intent);
        LoginActivity favoriteActivity = (LoginActivity) activity;
        Thread.sleep(2000);
        LoginViewModel viewModel = favoriteActivity
                .getViewModel(BR.loginViewModel);
        viewModel.loginBean.setUserName("");
        viewModel.loginBean.setPassword("Tesco1234");
        viewModel.onLoginClick();
        Thread.sleep(2000);
    }

    @Test
    public void loginSuccessTest() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        Intent intent = new Intent(appContext, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Activity activity = InstrumentationRegistry.getInstrumentation()
                .startActivitySync(intent);
        LoginActivity favoriteActivity = (LoginActivity) activity;
        Thread.sleep(2000);
        LoginViewModel viewModel = favoriteActivity
                .getViewModel(BR.loginViewModel);
        viewModel.loginBean.setUserName("Tesco");
        viewModel.loginBean.setPassword("Tesco");
        viewModel.onLoginClick();
        Thread.sleep(5000);
    }

}
