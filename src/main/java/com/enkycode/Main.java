package com.enkycode;

import java.util.*;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner input = new Scanner(System.in);
        System.out.println("Please choose an activity.\nVerbs\nVocabulary");
        while (true) {
            if (input.nextLine().toLowerCase().contains("vocab")) {
                VocabularyQuizRunner.runQuiz();
                break;
            } else if (input.nextLine().toLowerCase().contains("verb")) {
                VerbsQuizRunner.runQuiz();
                break;
            } else {
                System.out.println("Invalid input, try again.");
            }
        }
    }
}