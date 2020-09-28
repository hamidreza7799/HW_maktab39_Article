package base.services.impl;

import base.domains.BaseEntity;
import base.repositories.BaseRepository;
import base.services.BaseService;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

public class BaseServiceImpl<E extends BaseEntity<PK>,PK extends Serializable,Repository extends BaseRepository<E,PK>> implements BaseService<E , PK> {
    protected Repository repository;

    public BaseServiceImpl(Repository repository){
        this.repository = repository;
    }

    @Override
    public E save(E e) {
        return this.repository.save(e);
    }

    @Override
    public E findById(PK id) {
        return this.repository.findById(id);
    }

    @Override
    public boolean deleteById(PK id) {
        return this.repository.deleteById(id);
    }

    @Override
    public Set<E> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Set<E> findByIdsIn(Collection<PK> ids) {
        return this.repository.findByIdsIn(ids);
    }
}
