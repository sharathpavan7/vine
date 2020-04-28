package com.sharath.vine.api;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FameGearApiAdapter {

    private static final String BASE_URL = "https://winehouse.giggadaproducts.com";

    private static Retrofit retrofit = null;
    private static Retrofit regionRetrofit = null;
    private static volatile OkHttpClient okHttpClient;

    public static Retrofit getClient(Context context) {
        OkHttpClient okHttpClient = getHttpClient(context);
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }


    private static OkHttpClient getHttpClient(Context context) {
        if (okHttpClient != null) return okHttpClient;
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder()
                .retryOnConnectionFailure(true)
                //.socketFactory(new RestrictedSocketFactory(1024*1024))
                .connectTimeout(10, TimeUnit.MINUTES)
                //.callTimeout(0, TimeUnit.MINUTES)
                .writeTimeout(10, TimeUnit.MINUTES)
                .readTimeout(10, TimeUnit.MINUTES);
      /*  builder.interceptors().add(new Interceptor() {
            @NonNull
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                return onOnIntercept(chain, listener);
            }
        });*/

        Interceptor loggingInterceptor = getHttpLoggingInterceptor(context);
        if (loggingInterceptor != null) {
            builder.addInterceptor(loggingInterceptor);
        }
        okHttpClient = builder.build();
        return okHttpClient;
    }

   private static Interceptor getHttpLoggingInterceptor(Context context) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(getHttpLoggingLevel(context));
        return interceptor;
    }

    private static HttpLoggingInterceptor.Level getHttpLoggingLevel(Context context) {
        try {
            return HttpLoggingInterceptor.Level.valueOf(String.valueOf(HttpLoggingInterceptor.Level.BODY));
        } catch (Throwable t) {
            //Ignore
        }
        return HttpLoggingInterceptor.Level.NONE;
    }

}

