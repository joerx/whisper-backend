package io.yodo.whisper.service;

import io.yodo.whisper.commons.web.error.NoSuchEntityException;
import io.yodo.whisper.entity.Shout;
import io.yodo.whisper.repository.ShoutRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShoutServiceImpl implements ShoutService {

    private final ShoutRepository repo;

    public ShoutServiceImpl(@Qualifier("jpaShoutRepo") ShoutRepository repo) {
        this.repo = repo;
    }

    @Override
    @Transactional
    public Page<Shout> findAll(PageRequest pr) {
        return repo.findAll(pr);
    }

    @Override
    @Transactional
    public Shout findById(int id) {
        return mustFindShout(id);
    }

    @Override
    @Transactional
    public Shout create(Shout shout) {
        shout.setId(0);
        return repo.create(shout);
    }

    @Override
    @Transactional
    public Shout update(int id, Shout shout) {
        mustFindShout(id);
        return repo.update(shout);
    }

    @Override
    @Transactional
    public Shout deleteById(int id) {
        Shout shout = mustFindShout(id);
        return repo.delete(shout);
    }

    @Override
    @Transactional
    public long countAll() {
        return repo.countAll();
    }

    private Shout mustFindShout(int id) {
        Shout shout = repo.findById(id);
        if (shout == null) {
            throw new NoSuchEntityException("No shout with id " + id);
        }
        return shout;
    }
}
