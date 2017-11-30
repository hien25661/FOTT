package co.hien.fott.android.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.hien.fott.android.R;
import co.hien.fott.android.helper.EventBusUtils;
import co.hien.fott.android.helper.Utils;
import co.hien.fott.android.helper.event.ChooseFeedItemEvent;
import co.hien.fott.android.model.Article;

/**
 * Created by nguyenvanhien on 11/28/17.
 */

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.FeedViewHolder> {

    private ArrayList<Article> articles = new ArrayList<>();
    private Context mContext;
    public FeedAdapter(ArrayList<Article> list){
        this.articles = list;
    }

    @Override
    public FeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_feed, parent, false);
        FeedViewHolder vh = new FeedViewHolder(v);
        mContext = v.getContext();
        return vh;
    }

    @Override
    public void onBindViewHolder(FeedViewHolder holder, int position) {
        if (position >= 0 && position < articles.size()) {
            final Article article = articles.get(position);
            if(article != null){
                holder.tvTitle.setText(article.getTitle());
                holder.tvTime.setText(article.getDateTime());
                holder.tvContent.setText(article.getIngress());
                Utils.showImageFromUrlIntoView(mContext,article.getImage(),holder.imvThumbnail);

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        EventBusUtils.getDefault().post(new ChooseFeedItemEvent(article));
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    class FeedViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.imvThumbnail)
        ImageView imvThumbnail;
        @Bind(R.id.tvTitle)
        TextView tvTitle;
        @Bind(R.id.tvTime)
        TextView tvTime;
        @Bind(R.id.tvContent)
        TextView tvContent;

        public FeedViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
