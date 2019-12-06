package io.yodo.whisper.repository;

import io.yodo.whisper.entity.Shout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
@Qualifier("jpaShoutRepo")
public class JPAShoutRepositoryImpl implements ShoutRepository {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final EntityManager entityManager;

    public JPAShoutRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Page<Shout> findAll(PageRequest pr) {
        long total = entityManager
                .createQuery("select count(s.id) from Shout s", Long.class)
                .getSingleResult();

        List<Shout> shouts = entityManager
                .createQuery("from Shout", Shout.class)
                .setFirstResult((int)pr.getOffset()) // wcpgw?
                .setMaxResults(pr.getPageSize())
                .getResultList();

        return new PageImpl<>(shouts, pr, total);
    }

    @Override
    public Shout findById(int id) {
        return entityManager.find(Shout.class, id);
    }

    @Override
    public Shout create(Shout shout) {
        return entityManager.merge(shout);
    }

    @Override
    public Shout update(Shout shout) {
        return entityManager.merge(shout);
    }

    @Override
    public Shout delete(Shout shout) {
        entityManager.remove(shout);
        return shout;
    }

    @Override
    public long countAll() {
        return entityManager
                .createQuery("select count(s) from Shout s", Long.class)
                .getSingleResult();
    }
}
