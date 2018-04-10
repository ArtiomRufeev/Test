package com.omertex.test.ui.detail;

import com.omertex.test.model.Data;
import com.omertex.test.ui.base.MvpPresenter;
import com.omertex.test.ui.base.MvpView;

public interface DetailContract {
 
    interface View extends MvpView {
        void fillInData(Data data);
    }

    interface Presenter extends MvpPresenter<View> {
        void onDataRetrieved(Data data);
    }
}