package repositories.app.impl;

import base.repositories.impl.BaseRepositoryImpl;
import domains.app.Article;
import domains.app.User;
import repositories.app.ArticleRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.Set;

public class ArticleRepositoryImpl extends BaseRepositoryImpl<Article,Integer> implements ArticleRepository {

    public ArticleRepositoryImpl(EntityManager entityManager){
        super(Article.class,entityManager);
    }

    @Override
    public Set<Article> findByCreatorUsername(String username) {
        try {
            TypedQuery<User> findCustomUser = this.entityManager.createQuery("select u from User as u where u.username=:username", User.class);
            findCustomUser.setParameter("username", username);
            User customUser = findCustomUser.getSingleResult();
            TypedQuery<Article> findArticles = this.entityManager.createQuery("select a from Article as a where a.creator=:customUser", Article.class);
            findArticles.setParameter("customUser", customUser);
            return new HashSet<>(findArticles.getResultList());
        }catch (NoResultException ex){
            return new HashSet<>();
        }
    }

    @Override
    public Article findByCreatorUsernameArticleId(String username, int articleId) {
        try {
            TypedQuery<User> findCustomUser = this.entityManager.createQuery("select u from User as u where u.username=:username", User.class);
            findCustomUser.setParameter("username", username);
            User customUser = findCustomUser.getSingleResult();
            TypedQuery<Article> findArticle = this.entityManager.createQuery("select a from Article as a where a.id=:id and a.creator=:customUser", Article.class);
            findArticle.setParameter("id", articleId);
            findArticle.setParameter("customUser", customUser);
            return findArticle.getSingleResult();
        }catch (NoResultException ex){
            return null;
        }
    }
}
