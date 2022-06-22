package com.tgc.bullsAndCows;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class MainGame {

    private int cows = 0;
    private int bulls = 0;

    public void mainGame(int answer, int rightAnswer) {

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
    }

    private static int countBetween(char what, String where, int to) {
        int count = 0;
        for (int i = 0; i < to; i++) {
            if (where.charAt(i) == what)
                count++;
        }
        return count;
    }

    public int getCows() {
        return cows;
    }

    public void setCows(int cows) {
        this.cows = cows;
    }

    public int getBulls() {
        return bulls;
    }

    public void setBulls(int bulls) {
        this.bulls = bulls;
    }
}
