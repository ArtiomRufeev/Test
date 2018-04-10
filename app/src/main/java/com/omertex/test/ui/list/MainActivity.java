package com.omertex.test.ui.list;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.omertex.moovies.R;
import com.omertex.test.db.DBHelper;
import com.omertex.test.model.Data;
import com.omertex.test.ui.detail.DataDetailActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements DataAdapter.OnItemClickListener, MainContract.View {

    @BindView(R.id.rv_data)
    RecyclerView rvData;

    @BindView(R.id.swipe_to_refresh)
    SwipeRefreshLayout swipeRefresh;

    @BindView(R.id.layout_error)
    LinearLayout layoutError;

    private DataAdapter dataAdapter;

    private MainContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBHelper.initRealm(this);
        ButterKnife.bind(this);
        presenter = new MainPresenter();
        initRecyclerView();
        presenter.attachView(this);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.onRefresh();
            }
        });
    }

    private void initRecyclerView() {
        dataAdapter = new DataAdapter(this);
        rvData.setAdapter(dataAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(rvData.getContext(), LinearLayoutManager.VERTICAL, false);
        rvData.setLayoutManager(layoutManager);
    }

    @Override
    public void showDefaultError() {
        boolean emptyCache = dataAdapter.getItemCount() == 0;
        layoutError.setVisibility(emptyCache ? View.VISIBLE : View.GONE);
        if (!emptyCache) {
            Toast.makeText(this, R.string.loading_error, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void hideError() {
        layoutError.setVisibility(View.GONE);
    }

    @Override
    public void onItemClick(Data data) {
        presenter.onItemClicked(data);
    }

    @OnClick(R.id.btn_retry)
    void onRetryClicked() {
        presenter.onRetry();
    }

    @Override
    public void fillInData(ArrayList<Data> data) {
        dataAdapter.setItems(data);
    }

    @Override
    public void openDetailScreen(Data data) {
        DataDetailActivity.start(this, data);
    }

    @Override
    public void showProgress() {
        swipeRefresh.setRefreshing(true);
    }


    @Override
    public void hideProgress() {
        swipeRefresh.setRefreshing(false);
    }
}
