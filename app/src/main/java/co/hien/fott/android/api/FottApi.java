package co.hien.fott.android.api;


import co.hien.fott.android.api.responses.GetArticleslistResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * Created by nguyenvanhien on 11/28/17.
 */

public interface FottApi {

    @GET("article/get_articles_list")
    Call<GetArticleslistResponse> getArticlesList();

}
