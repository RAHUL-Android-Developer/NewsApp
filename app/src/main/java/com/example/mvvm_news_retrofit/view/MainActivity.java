package com.example.mvvm_news_retrofit.view;

import static androidx.navigation.fragment.FragmentKt.findNavController;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.mvvm_news_retrofit.Model.Article;
import com.example.mvvm_news_retrofit.R;
import com.example.mvvm_news_retrofit.adapter.ArticleAdapter;
import com.example.mvvm_news_retrofit.view_model.ArticleViewModel;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recycler_View;
    private ProgressBar progress_Bar;
    private LinearLayoutManager layoutManager;
    private ArrayList<Article> articleArrayList = new ArrayList<>();

    ArticleViewModel articleViewModel;

    private ArticleAdapter articleAdapter;

    private ShimmerFrameLayout shimmerFrameLayout;
    private TextView  refesh;

    private SwipeRefreshLayout swipeRefreshLayout;
    private BottomNavigationView navigationView;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        refesh=(TextView) findViewById(R.id.refersh);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        navigationView = findViewById(R.id.bottomNavigationView);

        swipeRefreshLayout.setOnRefreshListener(() -> {
            refreshArticles();
            swipeRefreshLayout.setRefreshing(true);
        });



//        navigationView.setOnItemSelectedListener(item -> {
//            switch (item.getItemId()){
//                case R.id.foryou:
//
//                    break;
//                case R.id.HeadLine:
//                    break;
//                case R.id.Following:
//                    break;
//                case R.id.newstatsd:
//                    break;
//            }
//
//            return true;
//        });


        refesh.setOnClickListener(v -> {
            Animation rotateAnim = AnimationUtils.loadAnimation(this, R.anim.rotate_button);
            Animation clickAnim = AnimationUtils.loadAnimation(this, R.anim.button_click_anim);

            AnimationSet animSet = new AnimationSet(true);

            animSet.addAnimation(rotateAnim);
            animSet.addAnimation(clickAnim);

            v.startAnimation(animSet);

            refreshArticles();
        });

        init();
        getArticles();
    }

    private void getArticles() {

        articleViewModel.getBashboardNewsResponseLiveData().observe(this, articleResponse -> {

            if (articleResponse != null && articleResponse.getArticles() != null
                    && !articleResponse.getArticles().isEmpty()) {
                progress_Bar.setVisibility(View.GONE);
                shimmerFrameLayout.stopShimmer();
                shimmerFrameLayout.setVisibility(View.GONE);
                swipeRefreshLayout.setRefreshing(false);
                recycler_View.setVisibility(View.VISIBLE);
                List<Article> articlesList = articleResponse.getArticles();
                articleArrayList.addAll(articlesList);
                articleAdapter.notifyDataSetChanged();
            }
        });
    }

    private void init() {

        shimmerFrameLayout = findViewById(R.id.shimmerLayout);
        shimmerFrameLayout.startShimmer();
        progress_Bar = findViewById(R.id.progess_bar);
        recycler_View = findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(MainActivity.this);
        recycler_View.setLayoutManager(layoutManager);

        recycler_View.setHasFixedSize(true);
        articleAdapter = new ArticleAdapter(MainActivity.this, articleArrayList);
        recycler_View.setAdapter(articleAdapter);

        articleViewModel = ViewModelProviders.of(this).get(ArticleViewModel.class);
    }



    private void refreshArticles() {
        shimmerFrameLayout.setVisibility(View.VISIBLE);
        shimmerFrameLayout.startShimmer();
        recycler_View.setVisibility(View.GONE);

        articleArrayList.clear();
        articleAdapter.notifyDataSetChanged();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                getArticles();
            }
        }, 2000);
    }


//    public void replaceFragmet(Fragment fragment) {
//
//    }
}