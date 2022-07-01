package com.tgc.bullsAndCows.Utils;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class GameUtils {

    private int cows = 0;
    private int bulls = 0;

    public void getBullsAndCowsCount(int userAnswer, int rightAnswer) {

        StringBuilder answerBuilder = new StringBuilder(String.valueOf(userAnswer));
        StringBuilder rightAnswerBuilder = new StringBuilder(String.valueOf(rightAnswer));

        String answerString = String.valueOf(userAnswer);
        String rightAnswerString = String.valueOf(rightAnswer);

        for (int i = 0; i < 4; ++i) {
            if (answerString.charAt(i) == rightAnswerString.charAt(i)) {
                bulls++;
                answerBuilder.setCharAt(i, ' ');
                rightAnswerBuilder.setCharAt(i, ' ');
            }
        }
        answerString = answerBuilder.toString();
        rightAnswerString = rightAnswerBuilder.toString();

        for (int i = 0; i < 4; ++i) {
            if (answerString.charAt(i) == ' ')
                continue;
            int countLeft = countBetween(answerString.charAt(i), answerString, i);
            int countInComputerNumber = countBetween(
                    answerString.charAt(i), rightAnswerString, rightAnswerString.length());
            if (countInComputerNumber != 0
                    && countLeft < countInComputerNumber)
                cows++;
        }
    }

    private static int countBetween(char what, String where, int to) {
        int count = 0;
        for (int i = 0; i < to; i++) {
            if (where.charAt(i) == what)
                count++;
        }
        return count;
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
