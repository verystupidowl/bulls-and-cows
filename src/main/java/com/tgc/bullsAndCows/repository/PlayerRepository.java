package com.tgc.bullsAndCows.repository;

import com.tgc.bullsAndCows.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
}
