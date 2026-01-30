package com.enkycode;

import com.enkycode.quiz.VerbsQuizRunner;
import com.enkycode.quiz.VocabularyQuizRunner;

import java.util.*;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner input = new Scanner(System.in);
        System.out.println("Please choose an activity.\nVerbs\nVocabulary");
        while (true) {
            String choice = input.nextLine().toLowerCase();
            if (choice.contains("vocab")) {
                VocabularyQuizRunner.runQuiz();
                break;
            } else if (choice.contains("verb")) {
                VerbsQuizRunner.runQuiz();
                break;
            } else {
                System.out.println("Invalid input, try again.");
            }
        }
    }
}