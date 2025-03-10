package com.tgc.bullsandcows.repository;

import com.tgc.bullsandcows.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    Optional<Player> findByName(String name);
    Optional<Player> findByEmail(String email);
}
