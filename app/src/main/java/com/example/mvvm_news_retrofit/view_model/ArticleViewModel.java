package com.example.mvvm_news_retrofit.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mvvm_news_retrofit.Repository.ArticleRepository;
import com.example.mvvm_news_retrofit.Response.ArticleResponse;

public class ArticleViewModel extends AndroidViewModel {

    private ArticleRepository articleRepository;
    private LiveData<ArticleResponse>  articleResponseLiveData;


    public ArticleViewModel(@NonNull Application application) {

        super(application);

        articleRepository=new ArticleRepository();
        this.articleResponseLiveData=articleRepository.getDashboardNews();
    }


    public LiveData<ArticleResponse> getBashboardNewsResponseLiveData()
    {
        return articleResponseLiveData;
    }

}
