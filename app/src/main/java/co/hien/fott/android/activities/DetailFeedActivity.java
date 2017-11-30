package co.hien.fott.android.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.hien.fott.android.FottApplication;
import co.hien.fott.android.R;
import co.hien.fott.android.adapters.ItemContentAdapter;
import co.hien.fott.android.database.SQLiteHelper;
import co.hien.fott.android.helper.Consts;
import co.hien.fott.android.helper.Utils;
import co.hien.fott.android.model.Article;

/**
 * Created by nguyenvanhien on 11/28/17.
 */

public class DetailFeedActivity extends BaseActivity {
    @Bind(R.id.imvThumbnail)
    ImageView imvThumbnail;
    @Bind(R.id.tvTitle)
    TextView tvTitle;
    @Bind(R.id.tvIngress)
    TextView tvIngress;
    @Bind(R.id.tvTime)
    TextView tvTime;
    @Bind(R.id.rcvItemContent)
    RecyclerView rcvItemContent;

    private Article article;
    private ItemContentAdapter itemContentAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_feed);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initView() {
        //Setup Recycler view
        rcvItemContent.setHasFixedSize(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvItemContent.setLayoutManager(linearLayoutManager);
    }

    private void initData() {
        article = getIntent().getParcelableExtra(Consts.PARAM_FEED_ITEM);
        if(article!=null){
            Utils.showImageFromUrlIntoView(this,article.getImage(),imvThumbnail);
            tvTitle.setText(article.getTitle());
            tvIngress.setText(article.getIngress());
            tvTime.setText(article.getDateTime());

            if(article.getContent()!=null){
                itemContentAdapter = new ItemContentAdapter(article.getContent());
                rcvItemContent.setAdapter(itemContentAdapter);
            }
        }

    }

    @OnClick(R.id.imvBack)
    public void backToPrevious(){
        onBackPressed();
    }

    public SQLiteHelper getDbHelper() {
        return ((FottApplication)getApplication()).getDbHelper();
    }
}
