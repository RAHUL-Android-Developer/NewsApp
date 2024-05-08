package com.example.mvvm_news_retrofit.Repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvm_news_retrofit.Response.ArticleResponse;
import com.example.mvvm_news_retrofit.Retrofit.ApiRequest;
import com.example.mvvm_news_retrofit.Retrofit.RetrofitRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ArticleRepository {

    private static final String TAG=ArticleRepository.class.getSimpleName();

    private final ApiRequest   apiRequest;



    public ArticleRepository()
    {
        apiRequest= RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }

    public LiveData<ArticleResponse> getDashboardNews()
    {
        final MutableLiveData<ArticleResponse>  data=new MutableLiveData<>();
        apiRequest.getTopHeadlines()
                .enqueue(new Callback<ArticleResponse>() {
                    @Override
                    public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {
                        if(response.body()!=null)
                        {
                            data.setValue(response.body());

                        }
                    }

                    @Override
                    public void onFailure(Call<ArticleResponse> call, Throwable t) {
                            data.setValue(null);
                    }
                });
        return data;
    }

}
