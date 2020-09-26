package repositories;

import base.repositories.BaseRepository;
import domains.Article;

import java.util.List;

public interface ArticleRepository extends BaseRepository<Article,Integer> {

    List<Article> findByCreatorUsername(String username);

    Article findByCreatorUsernameArticleId(String username , int articleId);
}
