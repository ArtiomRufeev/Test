package com.omertex.test.ui.base;

public abstract class BasePresenter<T extends MvpView> implements MvpPresenter<T> {
 
    private T view;
 
    @Override
    public void attachView(T mvpView) {
        view = mvpView;
    }
 
    @Override
    public void detachView() {
        view = null;
    }
 
    public T getView() {
        return view;
    }
 
    protected boolean isViewAttached() {
        return view != null;
    }
}