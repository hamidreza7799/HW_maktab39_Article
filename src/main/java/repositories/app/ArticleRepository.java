package repositories.app;

import base.repositories.BaseRepository;
import domains.app.Article;

import java.util.Set;

public interface ArticleRepository extends BaseRepository<Article,Integer> {

    Set<Article> findByCreatorUsername(String username);

    Article findByCreatorUsernameArticleId(String username , int articleId);
}
