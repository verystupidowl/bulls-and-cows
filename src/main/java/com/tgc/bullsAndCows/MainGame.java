package com.tgc.bullsAndCows;

import com.tgc.bullsAndCows.model.Step;

public class MainGame {

    public static Step mainGame(Step step, int rightAnswer) {
        char[] answerChar = String.valueOf(step.getAnswer()).toCharArray();
        char[] rightAnswerChar = String.valueOf(rightAnswer).toCharArray();
        int bulls = 0;
        int cows = 0;
        for (char value : answerChar) {
            for (char c : rightAnswerChar) {
                if (value == c) {
                    cows++;
                }
            }
        }
        for (int i = 0; i < answerChar.length; i++) {
            if (answerChar[i] == rightAnswerChar[i])
                bulls++;
        }
        cows = cows - bulls;
        return new Step(cows, bulls, step.getAnswer(), step.getTime());
    }
}
