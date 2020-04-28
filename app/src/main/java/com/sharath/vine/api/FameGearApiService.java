package com.sharath.vine.api;


import com.sharath.vine.response.HomeListResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface FameGearApiService {

    @POST("/api/list_product.php")
    Call<HomeListResponse> getHomeList(@Body RequestBody body);

}
