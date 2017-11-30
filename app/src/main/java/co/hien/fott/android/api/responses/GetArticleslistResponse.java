package co.hien.fott.android.api.responses;

import java.util.ArrayList;

import co.hien.fott.android.model.Article;
import co.hien.fott.android.model.BaseModel;

/**
 * Created by nguyenvanhien on 11/28/17.
 */

public class GetArticleslistResponse extends BaseModel {
    private String status;
    private ArrayList<Article> content;
    private long serverTime;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Article> getContent() {
        return content;
    }

    public void setContent(ArrayList<Article> content) {
        this.content = content;
    }

    public long getServerTime() {
        return serverTime;
    }

    public void setServerTime(long serverTime) {
        this.serverTime = serverTime;
    }
}
