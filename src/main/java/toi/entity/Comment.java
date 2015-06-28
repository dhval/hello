package toi.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by dhval on 3/23/15.
 */
@Entity
@Table(name = "TOI_COMMENT")
public class Comment {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "comment_Id")
    private Long commentId;

    @Column(name = "text")
    String text;

    @Column(name = "date")
    Date date;

    @Column(name = "article_id")
    Long articleId;

    @Column(name = "user_id")
    Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }
}
