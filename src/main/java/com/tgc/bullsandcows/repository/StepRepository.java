package com.tgc.bullsandcows.repository;

import com.tgc.bullsandcows.entity.Game;
import com.tgc.bullsandcows.entity.Step;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StepRepository extends JpaRepository<Step, Long> {

    List<Step> findAllByGame(Game game);
}
