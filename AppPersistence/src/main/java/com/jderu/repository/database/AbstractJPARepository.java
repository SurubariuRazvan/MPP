package com.jderu.repository.database;

import com.jderu.Entity;
import com.jderu.repository.CRUDRepository;
import com.jderu.repository.validation.CRUDValidator;
import com.jderu.repository.validation.ValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public abstract class AbstractJPARepository<ID extends Serializable, E extends Entity<ID>> implements CRUDRepository<ID, E> {
    protected static final Logger logger = LogManager.getLogger();
    protected final CRUDValidator<E> validator;
    protected final Class<E> entityType;
    protected final CrudRepository<E, ID> repo;

    AbstractJPARepository(CRUDValidator<E> validator, Class<E> entityType, CrudRepository<E, ID> repo) {
        this.validator = validator;
        this.entityType = entityType;
        this.repo = repo;
    }


    @Override
    public E findOne(ID id) {
        if (id == null)
            throw new IllegalArgumentException("id is null");
        logger.info("Find one " + entityType.getName() + " " + id);
        return repo.findById(id).orElse(null);
    }

    @Override
    public Iterable<E> findAll() {
        logger.info("Find all " + entityType.getName());
        return repo.findAll();
    }

    @Override
    public E save(E entity) throws ValidationException {
        if (entity == null)
            throw new IllegalArgumentException("entity is null");
        logger.info("Save " + entityType.getName() + " " + entity.getId());
        validator.validate(entity);

        if (entity.getId() != null && this.findOne(entity.getId()) != null)
            return entity;
        repo.save(entity);
        return null;
    }

    @Override
    public E delete(ID id) {
        if (id == null)
            throw new IllegalArgumentException("id is null");
        logger.info("Delete " + entityType.getName() + " " + id);
        E entity = this.findOne(id);

        //TODO check for constraints violations
        if (entity != null)
            repo.deleteById(id);
        return entity;
    }

    @Override
    public E update(E entity) {
        if (entity == null)
            throw new IllegalArgumentException("entity is null");
        logger.info("Update " + entityType.getName() + " with id:" + entity.getId() + " with:" + entity.toString());
        validator.validate(entity);
        if (this.findOne(entity.getId()) == null)
            return entity;
        repo.save(entity);
        return null;
    }
}
