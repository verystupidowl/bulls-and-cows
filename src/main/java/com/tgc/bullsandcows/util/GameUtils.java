package com.tgc.bullsandcows.util;

import com.tgc.bullsandcows.dto.Sort;
import com.tgc.bullsandcows.entity.Game;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@UtilityClass
public class GameUtils {

    public static Comparator<Game> gameComparator(Sort sort) {
        return (g1, g2) -> switch (sort.getField()) {
            case "startTime" ->
                    sort.getOrder().equals("ASC") ? g1.getStartTime().compareTo(g2.getStartTime()) : g2.getStartTime().compareTo(g1.getStartTime());
            case "attempts" ->
                    sort.getOrder().equals("ASC") ? g1.getSteps().size() - g2.getSteps().size() : g2.getSteps().size() - g1.getSteps().size();
            default ->
                    sort.getOrder().equals("ASC") ? (int) (g1.getId() - g2.getId()) : (int) (g2.getId() - g1.getId());
        };
    }

    public static int generateRandomNumber() {
        StringBuilder answer = new StringBuilder();
        while (answer.length() < 4) {
            List<Integer> integers = new ArrayList<>();
            for (int i = 0; i < 10; i++)
                integers.add(i);
            Collections.shuffle(integers);
            for (int i = 0; i < 4; i++)
                answer.append(integers.get(i));
        }
        return Integer.parseInt(answer.toString());
    }
}
