package co.hien.fott.android.api;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import co.hien.fott.android.BuildConfig;
import co.hien.fott.android.api.responses.GetArticleslistResponse;
import co.hien.fott.android.helper.enums.StatusEnum;
import co.hien.fott.android.model.Article;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nguyenvanhien on 11/28/17.
 */

public class Loader {
    private static Loader instance;

    public static Loader getInstance() {
        if (instance == null) {
            instance = new Loader();
        }
        return instance;
    }

    public void getArticlesList(final ResultCallBackApi callback) {
        FottService.getService().getArticlesList().enqueue(new Callback<GetArticleslistResponse>() {
            @Override
            public void onResponse(Call<GetArticleslistResponse> call, Response<GetArticleslistResponse> response) {
                if(response != null && response.body()!=null && response.body().getStatus().equals(StatusEnum.SUCCESS.toString()))
                {
                    ArrayList<Article> listArticle = new ArrayList<>();
                    if(response.body().getContent()!=null && response.body().getContent().size() > 0){
                        listArticle = response.body().getContent();
                    }
                    callback.success(listArticle);
                }else {
                    callback.failed();
                }
            }

            @Override
            public void onFailure(Call<GetArticleslistResponse> call, Throwable t) {
                Log.e("getArticlesList", "" + t.getMessage());
                callback.failed();
            }
        });
    }

}
