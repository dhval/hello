package sample.toi.entity;

import org.hibernate.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by dhval on 3/23/15.
 */
@Component
public class MySQLDAO {

    private static final Logger LOG = LoggerFactory.getLogger(MySQLDAO.class);

    @Autowired
    private SessionFactory sessionFactory;

    private HibernateTemplate hibernateTemplate() {
        return new HibernateTemplate(sessionFactory);
    }

    public Article findArticleById(final Long id) {
        List<Article> results = (List<Article>) hibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery("from Article a where a.articleId = :articleId");
                query.setParameter("articleId", id);
                List<Article> results = query.list();
                return results;
            }
        });
        if (results.isEmpty())
            return null;
        return results.get(0);
    }

    public User findUserById(final Long id) {
        List<User> results = (List<User>) hibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery("from User a where a.userId = :userId");
                query.setParameter("userId", id);
                List<User> results = query.list();
                return results;
            }
        });
        if (results.isEmpty())
            return null;
        return results.get(0);
    }

    public Comment findCommentById(final Long id) {
        List<Comment> results = (List<Comment>) hibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery("from Comment a where a.commentId = :commentId");
                query.setParameter("commentId", id);
                List<Comment> results = query.list();
                return results;
            }
        });
        if (results.isEmpty())
            return null;
        return results.get(0);
    }

    private Object saveObj(Object obj){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(obj);
        tx.commit();;
        session.close();
        return obj;
    }

    public Article save(Article article){
        Article result = findArticleById(article.getArticleId());
        if (result != null)
            return result;
        return (Article) saveObj(article);
    }
    public User save(User user){
        User result = findUserById(user.getUserId());
        if (result != null)
            return result;
        return (User) saveObj(user);
    }
    public Comment save(Comment comment){
        Comment result = findCommentById(comment.getCommentId());
        if (result != null)
            return result;
        return (Comment) saveObj(comment);
    }
}
