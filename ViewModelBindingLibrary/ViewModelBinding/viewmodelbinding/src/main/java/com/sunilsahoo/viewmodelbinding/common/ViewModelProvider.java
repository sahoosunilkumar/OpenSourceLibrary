package com.sunilsahoo.viewmodelbinding.common;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by sunilkumarsahoo on 12/19/16.
 */
class ViewModelProvider {
    private final ConcurrentHashMap<String, ViewDataHolder>
            mViewModels;

    private ViewModelProvider() {
        mViewModels = new ConcurrentHashMap<>();
    }

    public static ViewModelProvider getInstance() {
        return HelperHolder.INSTANCE;
    }

    /**
     * unregisters viewmodel using id
     *
     * @param viewModelId
     */
    public void unregister(String viewModelId) {
        mViewModels.remove(viewModelId);
    }

    /**
     * retrieves viewmodel associated with id
     *
     * @param viewModelId
     * @return
     */
    public ViewDataHolder get(String viewModelId) {
        return mViewModels.get(viewModelId);
    }

    /**
     * registers viewmodel with id
     *
     * @param viewModelId
     * @param viewDataHolder
     */
    public void register(String viewModelId, ViewDataHolder viewDataHolder) {
        mViewModels.put(viewModelId, viewDataHolder);
    }

    /**
     * Provides the lazy-loaded Singleton instance.
     */
    private static class HelperHolder {
        private static final ViewModelProvider INSTANCE = new
                ViewModelProvider();
    }
}
