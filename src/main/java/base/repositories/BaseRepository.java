package base.repositories;

import base.domains.BaseEntity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

public interface BaseRepository<E extends BaseEntity<PK>, PK extends Serializable> {

    E save(E e);

    E findById(PK id);

    boolean deleteById(PK id);

    Set<E> findAll();

    Set<E> findByIdsIn(Collection<PK> ids);
}
