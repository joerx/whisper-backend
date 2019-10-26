package io.yodo.whisper.service;

import io.yodo.whisper.entity.Shout;
import io.yodo.whisper.error.NoSuchEntityException;
import io.yodo.whisper.repository.ShoutJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ShoutServiceImpl implements ShoutService {

    private final ShoutJpaRepository repo;

    public ShoutServiceImpl(ShoutJpaRepository repo) {
        this.repo = repo;
    }

    @Override
    @Transactional
    public List<Shout> findAll() {
        return repo.findAll();
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
        return repo.save(shout);
    }

    @Override
    @Transactional
    public Shout update(int id, Shout shout) {
        mustFindShout(id);
        shout.setId(id);
        return repo.save(shout);
    }

    @Override
    @Transactional
    public Shout deleteById(int id) {
        Shout shout = mustFindShout(id);
        repo.delete(shout);
        return shout;
    }

    private Shout mustFindShout(int id) {
        Optional<Shout> shout = repo.findById(id);
        if (!shout.isPresent()) {
            throw new NoSuchEntityException("No shout with id " + id);
        }
        return shout.get();
    }
}
