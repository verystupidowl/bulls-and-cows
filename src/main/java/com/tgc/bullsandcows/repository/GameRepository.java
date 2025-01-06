package com.tgc.bullsandcows.repository;

import com.tgc.bullsandcows.entity.Game;
import com.tgc.bullsandcows.entity.Player;
import lombok.NonNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    @NonNull
    @EntityGraph(attributePaths = "steps")
    Optional<Game> findById(@NonNull Long id);

    List<Game> findGamesByPlayer(Player player);
}
