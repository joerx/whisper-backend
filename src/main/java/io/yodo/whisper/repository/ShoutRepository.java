package io.yodo.whisper.repository;

import io.yodo.whisper.entity.Shout;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ShoutRepository {

    List<Shout> findAll();

    Shout findById(int id);

    void create(Shout shout);

    void update(Shout shout);

    void delete(Shout shout);
}
