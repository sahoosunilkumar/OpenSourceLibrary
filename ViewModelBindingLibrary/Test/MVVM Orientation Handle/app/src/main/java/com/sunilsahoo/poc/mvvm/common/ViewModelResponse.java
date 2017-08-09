package com.sunilsahoo.poc.mvvm.common;

/**
 * Created by sunilkumarsahoo on 12/15/16.
 */

public class ViewModelResponse<T> {
    private String message;
    private T result;
    private boolean failure;

    public ViewModelResponse(String message, T result, boolean failure) {
        this.message = message;
        this.result = result;
        this.failure = failure;
    }

    public String getMessage() {
        return message;
    }

    public T getResult() {
        return result;
    }

    public boolean isFailure() {
        return failure;
    }
}
