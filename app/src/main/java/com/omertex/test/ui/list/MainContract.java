package com.omertex.test.ui.list;

import com.omertex.test.model.Data;
import com.omertex.test.ui.base.MvpPresenter;
import com.omertex.test.ui.base.MvpView;

import java.util.ArrayList;

public interface MainContract {
 
    interface View extends MvpView {
        void fillInData(ArrayList<Data> data);
        void openDetailScreen(Data data);
        void showProgress();
        void hideProgress();
        void showDefaultError();
        void hideError();
    }

    interface Presenter extends MvpPresenter<View> {
        void onRefresh();
        void onRetry();
        void loadData();
        void onItemClicked(Data data);
    }
}