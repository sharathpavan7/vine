package com.sharath.vine;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.sharath.vine.api.FameGearApiAdapter;
import com.sharath.vine.api.FameGearApiService;
import com.sharath.vine.response.HomeListResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getHomeList(String userId, int perPageRecord, final int pageNo, String searchString) {

        if (/*Utils.checkInternetConenction(getActivity())*/true) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("userid", userId);
                jsonObject.put("perpagerecord", perPageRecord);
                jsonObject.put("pageno", pageNo);
                jsonObject.put("search", searchString);
                FameGearApiService api = FameGearApiAdapter.getClient(this).create(FameGearApiService.class);
                RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),
                        jsonObject.toString());
                Call<HomeListResponse> call = api.getHomeList(body);

                call.enqueue(new Callback<HomeListResponse>() {
                    @Override
                    public void onResponse(Call<HomeListResponse> call, Response<HomeListResponse> response) {
                        //swipeContainer.setRefreshing(false);
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                if (response.body().response == 1) {
                                    String message = response.body().message;
                                    if (message.equalsIgnoreCase("success")) {

                                        int cartCount = response.body().cartCount;
                                        /*prefManager.setCartCount(cartCount);
                                        if (genList == null)
                                            genList = new ArrayList<>();
                                        if (featuredlist == null)
                                            featuredlist = new ArrayList<>();
                                        if (hotKeysList == null)
                                            hotKeysList = new ArrayList<>();
                                        genList = response.body().homeList;
                                        totalPages = response.body().totalPages;
                                        currentPage = response.body().currentPage;
                                        featuredlist = response.body().featuredList;
                                        hotKeysList = response.body().hotKeyList;*/

                                    } else {

                                    }
                                } else {
                                    //noData
                                }
                            } else {
                                try {
                                    Log.d(TAG, response.errorBody().string());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            return;
                        }

                    }

                    @Override
                    public void onFailure(Call<HomeListResponse> call, Throwable t) {
                        t.printStackTrace();
                    }
                });

            } catch (JSONException e) {
                e.printStackTrace();
            }
        } //else
            //Utils.displayDialog(getActivity(), getString(R.string.no_nw));
    }
}
