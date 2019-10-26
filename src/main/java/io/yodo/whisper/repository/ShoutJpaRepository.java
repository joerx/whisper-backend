package io.yodo.whisper.repository;

import io.yodo.whisper.entity.Shout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoutJpaRepository extends JpaRepository<Shout, Integer> {
}
