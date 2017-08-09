package com.sunilsahoo.viewmodelbinding.common;

import android.databinding.BaseObservable;
import android.databinding.ViewDataBinding;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.CallSuper;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;


/**
 * ViewModel base class. Every ViewModel must extend this class
 *
 * @param
 */
public abstract class ViewModel extends
        BaseObservable {
    private IView mView;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private Queue<Runnable> taskQueue = new ConcurrentLinkedQueue<>();
    private int variableId;

    public ViewModel(int variableId) {
        this.variableId = variableId;
    }

    /**
     * Called after the View is being destroyed and therefore detached from
     * the ViewModel
     */
    @CallSuper
    public void onViewDetached() {
        mView = null;
    }

    /**
     * Called after this ViewModel instance was destroyed and removed from
     * cache
     * <p>
     * This is a place to do any cleanup to avoid memory leaks
     */
    @CallSuper
    public void onViewModelDestroyed() {
        mView = null;
        taskQueue.clear();
    }

    public IView getView() {
        return mView;
    }


    /**
     * @return Data Binding instance
     */
    public <T extends ViewDataBinding> T getBinding() {
        if (getView() != null)
            return (T) getView().getBinding();
        else
            return null;
    }

    /**
     * perform task on ui thread if view exists, otherwise add to queue
     *
     * @param action
     */
    public final void runOnUiThread(Runnable action) {
        if (getView() != null)
            action.run();
        else
            taskQueue.add(action);
    }

    /**
     * @return Handler
     */
    public Handler getHandler() {
        return mHandler;
    }


    /**
     * Bind a new View instance
     *
     * @param viewInterface View
     */
    public void bindView(IView viewInterface) {
        mView = viewInterface;
    }

    public void executePendingTasks() {
        while (!taskQueue.isEmpty()) {
            runOnUiThread(taskQueue.poll());
        }
    }

    public int getVariableId() {
        return variableId;
    }
}
