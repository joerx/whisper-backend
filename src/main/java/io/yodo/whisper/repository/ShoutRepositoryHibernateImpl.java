package io.yodo.whisper.repository;

import io.yodo.whisper.entity.Shout;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

@Repository
public class ShoutRepositoryHibernateImpl implements ShoutRepository {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final EntityManager entityManager;

    @Autowired
    public ShoutRepositoryHibernateImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Shout> findAll() {
        Session sess = entityManager.unwrap(Session.class);
        Query<Shout> q = sess.createQuery("from Shout s", Shout.class);
        return q.getResultList();
    }

    @Override
    public Shout findById(int id) {
        Session sess = entityManager.unwrap(Session.class);
        return sess.get(Shout.class, id);
    }

    @Override
    public void create(Shout shout) {
        Session sess = entityManager.unwrap(Session.class);
        sess.save(shout);
    }

    @Override
    public void update(Shout shout) {
        Session sess = entityManager.unwrap(Session.class);
        sess.merge(shout);
    }

    @Override
    public void delete(Shout shout) {
        Session sess = entityManager.unwrap(Session.class);
        sess.delete(shout);
    }
}
