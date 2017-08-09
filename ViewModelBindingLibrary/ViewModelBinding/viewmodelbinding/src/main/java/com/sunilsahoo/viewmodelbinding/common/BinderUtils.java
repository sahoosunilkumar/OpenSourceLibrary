package com.sunilsahoo.viewmodelbinding.common;

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;

import java.util.UUID;

/**
 * Created by sunilkumarsahoo on 12/19/16.
 */

class BinderUtils {
    private BinderUtils() {

    }

    @NonNull
    public static String getViewModelIdField(ViewDataBinding viewDataBinding) {
        return "_lib_vd_id_" + viewDataBinding.getClass()
                .getName();
    }

    public static String getUniqueId() {
        return UUID.randomUUID().toString();
    }
}
