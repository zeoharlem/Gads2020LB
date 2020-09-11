package com.zeoharlem.gads.gads2020lb.Services;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceBuilder {

    private static final String URL = "https://gadsapi.herokuapp.com/";

    //Create Logger
    private static HttpLoggingInterceptor loggingInterceptor    = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    //Create OkHttp Client
    private static OkHttpClient.Builder okHttpBuilder           = new OkHttpClient.Builder().addInterceptor(loggingInterceptor);

    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create()).client(okHttpBuilder.build());

    private static Retrofit retrofit        = builder.build();

    public static <S> S buildService(Class<S> serviceType){
        return retrofit.create(serviceType);
    }
}
