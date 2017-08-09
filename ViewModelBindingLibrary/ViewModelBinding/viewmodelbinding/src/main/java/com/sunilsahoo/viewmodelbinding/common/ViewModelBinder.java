package com.sunilsahoo.viewmodelbinding.common;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;

import java.util.List;

/**
 * Class dealing with fragment/activity lifecycle
 * Created by sunilkumarsahoo on 12/19/16.
 */
public class ViewModelBinder {

    private static final String TAG = "ViewModelBinder";
    private String mDataId;
    private ViewDataBinding mBinding;
    private ViewDataHolder mViewDataHolder;
    private ViewModelState mViewModelState = new ViewModelState();

    /**
     * handle oncreate() of activity and fragment only
     *
     * @param view
     * @param savedInstanceState
     */
    public void onCreate(IView view, Bundle
            savedInstanceState) {
        mViewDataHolder = view.onBindViewDataHolder();
        if (mViewDataHolder == null || mViewDataHolder.getViewModelArr() ==
                null || mViewDataHolder.getViewModelArr().isEmpty() ||
                mViewModelState.isCreated || !isValidType(view)) {
            Log.w(TAG, "Return as condition mismatch (config is null or view " +
                    "type mismatch or view is already created)");
            return;
        }

        mViewModelState.isCreated = true;
        initializeView(view, mViewDataHolder.getLayoutId());

        mDataId = mDataId != null ? mDataId : (
                (savedInstanceState == null) ? BinderUtils.getUniqueId() :
                        savedInstanceState.getString
                                (BinderUtils.getViewModelIdField
                                        (getBinding())));
        onBindViewModel(view, mViewDataHolder);
    }

    /**
     * checks whether view type is valid
     * @param view
     * @return true if view type is valid ie activity or fragment
     */
    private boolean isValidType(IView view) {
        return view instanceof Activity || view instanceof Fragment;
    }

    private void initializeView(IView view, int layoutId) {
        if (view instanceof Activity)
            mBinding = DataBindingUtil.setContentView(((Activity) view),
                    layoutId);
        else {
            mBinding = DataBindingUtil.inflate(LayoutInflater.from(view
                            .getContext()), layoutId,
                    null, false);
        }
    }

    /**
     * on fragment view is destroyed
     *
     * @param fragment
     */
    public void onDestroyView(@NonNull Fragment fragment) {
        if (mViewDataHolder == null || fragment.getActivity() == null) return;
        cleanup(fragment.getActivity());
    }

    /**
     * Fragment or activity is destroyed
     *
     * @param object
     */
    public void onDestroy(@NonNull Object object) {
        if (object instanceof Fragment) {
            cleanup((Fragment) object);
        } else if (object instanceof Activity) {
            cleanup((Activity) object);
        }

    }

    /**
     * cleanup resources
     *
     * @param fragment
     */
    private void cleanup(@NonNull Fragment fragment) {
        if (mViewDataHolder == null) return;
        if ((fragment.getActivity().isFinishing()) || (fragment.isRemoving() &&
                !mViewModelState.isOnSaveInstanceStateCalled)) {
            removeViewModel();
        }
        mViewModelState.isCreated = false;
    }

    private void cleanup(@NonNull Activity activity) {
        if (mViewDataHolder == null || mViewDataHolder.getViewModelArr() ==
                null)
            return;

        for (ViewModel viewModel : mViewDataHolder.getViewModelArr()) {
            if (activity.isFinishing()) {
                viewModel.onViewDetached();
                removeViewModel();
            } else
                viewModel.onViewDetached();
        }
        mViewModelState.isCreated = false;
    }

    public List<? extends ViewModel> getViewModel() {
        return mViewDataHolder.getViewModelArr();
    }

    public void onSaveInstanceState(@NonNull Bundle bundle) {
        if (mViewDataHolder == null || mViewDataHolder.getViewModelArr() ==
                null)
            return;

        for (ViewModel viewModel : mViewDataHolder.getViewModelArr()) {
            bundle.putString(BinderUtils.getViewModelIdField(getBinding()),
                    mDataId);
            if (viewModel != null) {
                mViewModelState.isOnSaveInstanceStateCalled = true;
            }
        }
    }

    /**
     * retrieves binding
     *
     * @return mBinding
     */
    public ViewDataBinding getBinding() {
        return mBinding;
    }

    /**
     * Remove ViewModel
     */
    private void removeViewModel() {
        if (!mViewModelState.isModelRemoved) {
            ViewModelProvider.getInstance().unregister(mDataId);
            for (ViewModel viewModel : mViewDataHolder.getViewModelArr()) {
                viewModel.onViewModelDestroyed();
            }
            mViewModelState.isModelRemoved = true;
            mViewModelState.isCreated = false;
        }
    }

    /**
     * binds viewmodel with variable
     *
     * @param view
     * @param viewDataHolder
     */
    private void onBindViewModel(IView view, ViewDataHolder viewDataHolder) {
        mViewDataHolder = ViewModelProvider.getInstance().get
                (mDataId);
        if (mViewDataHolder == null) {
            mViewDataHolder = viewDataHolder;
            ViewModelProvider.getInstance().register(mDataId, mViewDataHolder);
        }
        mViewModelState.isOnSaveInstanceStateCalled = false;
        for (ViewModel viewModel : mViewDataHolder.getViewModelArr()) {
            viewModel.bindView(view);
            if (getBinding().setVariable(viewModel.getVariableId()
                    , viewModel)) {
                viewModel.executePendingTasks();
            }
        }
    }

    private class ViewModelState {
        public boolean isModelRemoved;
        public boolean isOnSaveInstanceStateCalled;
        public boolean isCreated;
    }
}
