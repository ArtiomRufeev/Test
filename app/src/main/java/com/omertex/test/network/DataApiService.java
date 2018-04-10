package com.omertex.test.network;

import com.omertex.test.model.DataListResponse;

import retrofit2.Call;
import retrofit2.http.GET;


public interface DataApiService {

    @GET("/test/strings")
    Call<DataListResponse> getStringsList();

    @GET("/test/images")
    Call<DataListResponse> getImagesList();
}
