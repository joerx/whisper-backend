package io.yodo.whisper.repository;

import io.yodo.whisper.entity.Shout;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Qualifier("hibernateShoutRepo")
public class ShoutRepositoryHibernateImpl implements ShoutRepository {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final EntityManager entityManager;

    @Autowired
    public ShoutRepositoryHibernateImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Shout> findAll() {
        log.debug("Find all shouts using hibernate API");
        Session sess = entityManager.unwrap(Session.class);
        Query<Shout> q = sess.createQuery("from Shout s", Shout.class);
        return q.getResultList();
    }

    @Override
    public Shout findById(int id) {
        log.debug("Find a shout using hibernate API");
        Session sess = entityManager.unwrap(Session.class);
        return sess.get(Shout.class, id);
    }

    @Override
    public Shout create(Shout shout) {
        log.debug("Create a shout using hibernate API");
        Session sess = entityManager.unwrap(Session.class);
        sess.save(shout);
        return shout;
    }

    @Override
    public Shout update(Shout shout) {
        log.debug("Update a shout using hibernate API");
        Session sess = entityManager.unwrap(Session.class);
        sess.merge(shout);
        return shout;
    }

    @Override
    public Shout delete(Shout shout) {
        log.debug("Delete a shout using hibernate API");
        Session sess = entityManager.unwrap(Session.class);
        sess.delete(shout);
        return shout;
    }
}
