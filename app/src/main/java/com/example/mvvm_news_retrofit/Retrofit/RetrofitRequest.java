package com.example.mvvm_news_retrofit.Retrofit;

import static com.example.mvvm_news_retrofit.constants.AppConstant.BASE_URL;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitRequest {

    private static Retrofit  retrofit;

    public static Retrofit getRetrofitInstance()
    {
        if(retrofit==null)
        {
            return new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
