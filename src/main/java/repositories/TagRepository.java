package repositories;

import base.repositories.BaseRepository;
import domains.Tag;

public interface TagRepository extends BaseRepository<Tag,Integer> {

    Tag findByTitle(String title);
}
