package base.services;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

public interface BaseService<E , PK extends Serializable> {
    E save(E e);

    E findById(PK id);

    boolean deleteById(PK id);

    Set<E> findAll();

    Set<E> findByIdsIn(Collection<PK> ids);
}
