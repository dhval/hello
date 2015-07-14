package sample.toi.entity;

import javax.persistence.*;

/**
 * Created by dhval on 3/23/15.
 */
@Entity
@Table(name = "TOI_ARTICLE")
public class Article {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "article_id")
    Long articleId;

    @Column(name = "url")
    String url;

    @Column(name = "text")
    String text;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
