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
import co.hien.fott.android.model.Item;

/**
 * Created by nguyenvanhien on 11/28/17.
 */

public class ItemContentAdapter extends RecyclerView.Adapter<ItemContentAdapter.ItemViewHolder> {

    private ArrayList<Item> items = new ArrayList<>();
    private Context mContext;
    public ItemContentAdapter(ArrayList<Item> list){
        this.items = list;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_content, parent, false);
        ItemViewHolder vh = new ItemViewHolder(v);
        mContext = v.getContext();
        return vh;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        if (position >= 0 && position < items.size()) {
            final Item item = items.get(position);
            if(item != null){
                holder.tvSubject.setText(item.getSubject());
                holder.tvDescription.setText(item.getDescription());
            }
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tvSubject)
        TextView tvSubject;
        @Bind(R.id.tvDescription)
        TextView tvDescription;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
