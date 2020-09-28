package services;

import base.services.BaseService;
import domains.app.Tag;

import java.util.Set;

public interface TagService extends BaseService<Tag,Integer> {

    boolean printTags();

    Tag createNewTag();

    Set<Tag> chooseTags();

    Tag findByTitle(String title);
}
