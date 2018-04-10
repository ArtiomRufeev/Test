package com.omertex.test.ui.detail;

import com.omertex.test.model.Data;


public class DetailPresenter implements DetailContract.Presenter {

    private DetailContract.View view;

    @Override
    public void attachView(DetailContract.View mvpView) {
        view = mvpView;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void onDataRetrieved(Data data) {
        view.fillInData(data);
    }
}
