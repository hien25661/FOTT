package co.hien.fott.android.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.hien.fott.android.FottApplication;
import co.hien.fott.android.R;
import co.hien.fott.android.adapters.FeedAdapter;
import co.hien.fott.android.api.Loader;
import co.hien.fott.android.api.ResultCallBackApi;
import co.hien.fott.android.database.SQLiteHelper;
import co.hien.fott.android.helper.Consts;
import co.hien.fott.android.helper.event.ChooseFeedItemEvent;
import co.hien.fott.android.model.Article;

public class FeedActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener,ResultCallBackApi {

    @Bind(R.id.drawer_layout)
    DrawerLayout drawer;
    @Bind(R.id.nav_view)
    NavigationView navigationView;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.rcvArticle)
    RecyclerView rcvArticle;

    private Loader loader;
    private ArrayList<Article> articles = new ArrayList<>();
    private FeedAdapter feedAdapter;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        ButterKnife.bind(this);
        initView();
        loadData();
    }

    private void initView() {
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        //Setup Recycler view
        rcvArticle.setHasFixedSize(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvArticle.setLayoutManager(linearLayoutManager);
    }

    private void loadData() {
        loader = Loader.getInstance();
        if(FottApplication.getInstance().isNetworkConnected()){
            loader.getArticlesList(this);
        }else {
            loadDataOffline();
        }

    }

    private void loadDataOffline() {
        articles = FottApplication.getInstance().getDbHelper().getListArticle();
        bindDataList(articles);
    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void success(Object... params) {
        if(params!=null && params[0] !=null){
            articles = (ArrayList<Article>) params[0];
            setListData(articles);
        }
    }

    private void setListData(ArrayList<Article> articles) {
        if(articles == null || articles.size() == 0) return;
        bindDataList(articles);
        FottApplication.getInstance().getDbHelper().deleteAllArticle();
        FottApplication.getInstance().getDbHelper().insertListArticle(articles);
    }

    private void bindDataList(ArrayList<Article> articles){
        if(articles == null || articles.size() == 0) return;
        feedAdapter = new FeedAdapter(articles);
        rcvArticle.setAdapter(feedAdapter);
    }

    @Override
    public void failed() {

    }

    @Subscribe
    public void showDetailFeed(ChooseFeedItemEvent event) {
        if(event!=null && event.getArticle()!=null){
            Intent t = new Intent(FeedActivity.this, DetailFeedActivity.class);
            t.putExtra(Consts.PARAM_FEED_ITEM,event.getArticle());
            startActivity(t);
        }
    }

}
