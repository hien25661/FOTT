package co.hien.fott.android.helper.event;

import co.hien.fott.android.model.Article;

/**
 * Created by nguyenvanhien on 11/28/17.
 */

public class ChooseFeedItemEvent {
    private Article article;

    public ChooseFeedItemEvent(Article article) {
        this.article = article;
    }

    public Article getArticle() {
        return article;
    }
}
