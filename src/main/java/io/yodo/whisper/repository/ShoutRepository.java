package io.yodo.whisper.repository;

import io.yodo.whisper.entity.Shout;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ShoutRepository {

    List<Shout> findAll();

    Shout findById(int id);

    Shout create(Shout shout);

    Shout update(Shout shout);

    Shout delete(Shout shout);
}
