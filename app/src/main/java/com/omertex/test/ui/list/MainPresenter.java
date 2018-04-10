package com.omertex.test.ui.list;

import com.omertex.test.db.DBHelper;
import com.omertex.test.model.Data;
import com.omertex.test.model.DataListResponse;
import com.omertex.test.network.DataApiService;
import com.omertex.test.network.DataServiceHelper;
import com.omertex.test.util.StringImageMapper;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;

    @Override
    public void attachView(MainContract.View mvpView) {
        view = mvpView;
        view.fillInData(DBHelper.retrieveDataFromRealm());
        loadData();
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void onRefresh() {
        loadData();
    }

    @Override
    public void loadData() {
        view.showProgress();
        view.hideError();
        loadStrings();
    }

    private void loadStrings() {
        DataApiService api = DataServiceHelper.getApiService();

        Call<DataListResponse> call = api.getStringsList();
        call.enqueue(new Callback<DataListResponse>() {
            @Override
            public void onResponse(Call<DataListResponse> call, Response<DataListResponse> response) {
                if (response.isSuccessful()) {
                    ArrayList<Data> strings = response.body();
                    loadImages(strings);
                } else {
                    view.showDefaultError();
                }
            }

            @Override
            public void onFailure(Call<DataListResponse> call, Throwable t) {
                view.showDefaultError();
                view.hideProgress();
            }
        });
    }

    private void loadImages(final ArrayList<Data> strings) {
        DataApiService api = DataServiceHelper.getApiService();

        Call<DataListResponse> call = api.getImagesList();
        call.enqueue(new Callback<DataListResponse>() {
            @Override
            public void onResponse(Call<DataListResponse> call, Response<DataListResponse> response) {
                view.hideProgress();
                if (response.isSuccessful()) {
                    ArrayList<Data> images = response.body();
                    ArrayList<Data> resultData = StringImageMapper.mapStringsAndImages(strings, images);
                    DBHelper.saveDataToRealm(resultData);
                    view.fillInData(resultData);
                } else {
                    view.showDefaultError();
                }
            }

            @Override
            public void onFailure(Call<DataListResponse> call, Throwable t) {
                view.showDefaultError();
                view.hideProgress();
            }
        });
    }

    @Override
    public void onRetry() {
        loadData();
    }

    @Override
    public void onItemClicked(Data data) {
        view.openDetailScreen(data);
    }
}
