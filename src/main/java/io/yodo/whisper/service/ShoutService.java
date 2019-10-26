package io.yodo.whisper.service;

import io.yodo.whisper.entity.Shout;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ShoutService {

    List<Shout> findAll();

    Shout findById(int id);

    Shout create(Shout shout);

    @Transactional
    Shout update(int id, Shout shout);

    Shout deleteById(int shoutId);
}
