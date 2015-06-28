package toi.parser;

import toi.entity.Article;
import toi.entity.Comment;

import java.util.Date;
import java.util.List;

/**
 * Created by dhval on 3/23/15.
 */
public class TOIObject {
    Long msid;
    String url;
    String tittle;
    List<User> users;

    static class User {
        Long id;
        String uid;
        String sso;
        String first;
        String last;
        String city;
        String comment;
        Long commentId;
        Date date;

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", uid='" + uid + '\'' +
                    ", sso='" + sso + '\'' +
                    ", first='" + first + '\'' +
                    ", last='" + last + '\'' +
                    ", city='" + city + '\'' +
                    ", comment='" + comment + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "TOIObject{" +
                "msid=" + msid +
                ", url='" + url + '\'' +
                ", tittle='" + tittle + '\'' +
                ", users=" + users +
                '}';
    }

    static Article toArticle(TOIObject object) {
        Article article = new Article();
        article.setUrl(object.url);
        article.setText(object.tittle);
        article.setArticleId(object.msid);
        return article ;
    }
    static Comment toComment(TOIObject object, TOIObject.User user) {
        Comment comment = new Comment();
        comment.setArticleId(object.msid);
        comment.setDate(user.date);
        comment.setText(user.comment);
        comment.setCommentId(user.commentId);
        comment.setUserId(user.id);
        return comment ;
    }
    static toi.entity.User toUser(TOIObject object, TOIObject.User user) {
        toi.entity.User u = new toi.entity.User();
        u.setUserId(user.id);
        u.setFirst(user.first);
        u.setLast(user.last);
        u.setSso(user.sso);
        u.setUid(user.uid);
        u.setCity(user.city);
        return u ;
    }


}
