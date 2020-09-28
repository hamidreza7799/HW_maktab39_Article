package repositories.app;

import base.repositories.BaseRepository;
import domains.app.Tag;

public interface TagRepository extends BaseRepository<Tag,Integer> {

    Tag findByTitle(String title);
}
