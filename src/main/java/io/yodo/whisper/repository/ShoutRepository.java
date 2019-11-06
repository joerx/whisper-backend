package io.yodo.whisper.repository;

import io.yodo.whisper.entity.Shout;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ShoutRepository {

    Page<Shout> findAll(PageRequest pr);

    Shout findById(int id);

    Shout create(Shout shout);

    Shout update(Shout shout);

    Shout delete(Shout shout);
}
