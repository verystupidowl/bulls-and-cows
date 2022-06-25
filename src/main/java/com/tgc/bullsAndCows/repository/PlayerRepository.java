package com.tgc.bullsAndCows.repository;

import com.tgc.bullsAndCows.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
}
