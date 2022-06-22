package com.tgc.bullsAndCows;

import com.tgc.bullsAndCows.model.Step;

import java.util.Date;

public class MainGame {

    public static Step mainGame(int answer, int rightAnswer) {
        int bulls = 0, cows = 0;

        StringBuilder answerBuilder = new StringBuilder(String.valueOf(answer));
        StringBuilder rightAnswerBuilder = new StringBuilder(String.valueOf(rightAnswer));

        String answerString = String.valueOf(answer);
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


        return new Step(cows, bulls, answer, new Date().getTime());
    }

    private static int countBetween(char what, String where, int to) {
        int count = 0;
        for (int i = 0; i < to; i++) {
            if (where.charAt(i) == what)
                count++;
        }
        return count;
    }
}
