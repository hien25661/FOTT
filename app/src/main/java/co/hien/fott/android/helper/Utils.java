package co.hien.fott.android.helper;

import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import co.hien.fott.android.model.Article;

/**
 * Created by nguyenvanhien on 11/28/17.
 */

public class Utils {

    public static String getFormatTime(String dateTime) {

        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd.MM.yyyy HH:mm");
        DateTime dt = formatter.parseDateTime(dateTime);
        DateFormat dateFormat = new SimpleDateFormat("dd MMMM hh:mm");
        return dateFormat.format(dt.toDate());
    }

    public static void showImageFromUrlIntoView(Context context, String url, ImageView imv) {
        if (url!=null) {
            Glide.with(context).load(url)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .centerCrop()
                    .into(imv);
        }
    }

    public static Article getArticleFromJson(String json){
        Gson gson = new Gson();
        if(json == null) return null;
        Article article = gson.fromJson(json, Article.class);
        return article;
    }
}
