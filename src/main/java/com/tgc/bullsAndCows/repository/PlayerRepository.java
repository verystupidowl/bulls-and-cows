package com.tgc.bullsAndCows.repository;

import com.tgc.bullsAndCows.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Integer> {

    Optional<Player> findByName(String name);
}
