package com.enkycode;

import com.enkycode.config.JSONConfigLoader;
import com.enkycode.quiz.VerbsQuizRunner;
import com.enkycode.quiz.VocabularyQuizRunner;

import java.util.*;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner input = new Scanner(System.in);
        JSONConfigLoader.loadStats();
        while (true) {
            System.out.println();
            System.out.println("Please choose an option.\nPractice Verbs\nPractice Vocabulary\nView Stats\nReset Stats\nPractice Suggestion\nQuit");

            String choice = input.nextLine().toLowerCase();
            if (choice.contains("vocab")) {
                VocabularyQuizRunner.runQuiz();
            } else if (choice.contains("verb")) {
                VerbsQuizRunner.runQuiz();
            } else if (choice.contains("stat")) {
                JSONConfigLoader.stats.printStats();
            } else if (choice.contains("quit")) {
                JSONConfigLoader.statsToJson();
                System.exit(0);
            } else if (choice.contains("reset")) {
                JSONConfigLoader.resetStats();
                JSONConfigLoader.stats.printStats();
            } else if (choice.contains("suggest")) {
                JSONConfigLoader.stats.printSuggestion();
            } else {
                System.out.println("Invalid input. Try again.");
            }
        }
    }
}