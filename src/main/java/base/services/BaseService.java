package base.services;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface BaseService<E , PK extends Serializable> {
    E save(E e);

    E findById(PK id);

    boolean deleteById(PK id);

    List<E> findAll();

    List<E> findByIdsIn(Collection<PK> ids);
}
