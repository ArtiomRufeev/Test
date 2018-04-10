package com.omertex.test.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.omertex.moovies.R;
import com.omertex.test.model.Data;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DataDetailActivity extends AppCompatActivity implements DetailContract.View {

    public static final String EXTRA_KEY_DATA = "key_data";

    @BindView(R.id.tv_name)
    TextView tvName;

    @BindView(R.id.iv_photo)
    ImageView ivPoster;

    private DetailPresenter presenter;

    public static void start(Context context, Data data) {
        Intent intent = new Intent(context, DataDetailActivity.class);
        intent.putExtra(EXTRA_KEY_DATA, data);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        Data data = ((Data) getIntent().getExtras().getSerializable(EXTRA_KEY_DATA));

        presenter = new DetailPresenter();
        presenter.attachView(this);
        presenter.onDataRetrieved(data);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void fillInData(Data data) {
        setTitle(data.getName());
        RequestOptions options = new RequestOptions();
        options.fitCenter();

        Glide.with(this).load(data.getImageUrl()).apply(options).into(ivPoster);
        tvName.setText(data.getName());
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}

