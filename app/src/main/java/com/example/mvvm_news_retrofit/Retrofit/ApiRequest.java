package com.example.mvvm_news_retrofit.Retrofit;

import static com.example.mvvm_news_retrofit.constants.AppConstant.API_KEY;

import com.example.mvvm_news_retrofit.Response.ArticleResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiRequest {

//    @GET("top-headlines?country=in&category=business&apiKey="+API_KEY)
//    @GET("top-headlines?country=in&category=entertainment&apiKey="+API_KEY)
    @GET("top-headlines?country=in&apiKey="+API_KEY)
//    @GET("top-headlines?country=in&category=everything?q=Apple&from=2024-05-08&sortBy=popularity &apiKey="+API_KEY)
//
    Call<ArticleResponse> getTopHeadlines();
}
