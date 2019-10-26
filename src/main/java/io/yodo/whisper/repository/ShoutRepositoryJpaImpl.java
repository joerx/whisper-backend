package io.yodo.whisper.repository;

import io.yodo.whisper.entity.Shout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Qualifier("jpaShoutRepo")
public class ShoutRepositoryJpaImpl implements ShoutRepository {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final EntityManager entityManager;

    public ShoutRepositoryJpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Shout> findAll() {
        log.debug("Find all shouts using JPA API");
        return entityManager.createQuery("from Shout", Shout.class).getResultList();
    }

    @Override
    public Shout findById(int id) {
        log.debug("Find a shout using JPA API");
        return entityManager.find(Shout.class, id);
    }

    @Override
    public Shout create(Shout shout) {
        log.debug("Create a shout using JPA API");
        return entityManager.merge(shout);
    }

    @Override
    public Shout update(Shout shout) {
        log.debug("Update a shout using JPA API");
        return entityManager.merge(shout);
    }

    @Override
    public Shout delete(Shout shout) {
        log.debug("Delete a shout using JPA API");
        entityManager.remove(shout);
        return shout;
    }
}
