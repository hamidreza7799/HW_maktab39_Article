package base.services;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

public interface BaseService<E , PK extends Serializable> {
    E save(E e);

    E findById(PK id);

    boolean deleteById(PK id);

    Set<E> findAll();

    Set<E> findAll(Predicate<E> filter);

    <T> Set<T> findAll(Function<E,T> mapper);

    Set<E> findByIdsIn(Collection<PK> ids);
}
