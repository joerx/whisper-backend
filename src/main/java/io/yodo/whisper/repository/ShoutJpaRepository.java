package io.yodo.whisper.repository;

import io.yodo.whisper.entity.Shout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

public interface ShoutJpaRepository extends JpaRepository<Shout, Integer> {
}
