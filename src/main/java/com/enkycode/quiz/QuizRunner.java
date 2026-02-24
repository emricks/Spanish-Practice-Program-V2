package com.enkycode.quiz;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class QuizRunner {
    public static List<Integer> usedIndices = new ArrayList<>();
    // there is no runQuiz method since it works differently for each class.
    public static void printScoreMessage(double accuracy, double score) {
        int roundedAccuracy = (int) Math.round(accuracy*100);
        System.out.println("Score: " + score + " out of " + Math.round(score/accuracy) + "! (" + roundedAccuracy + "%)");
        if (accuracy > 1) {
            System.out.println("How did you get a higher score than 100%?");
        } else if (accuracy == 1) {
            System.out.println("Congratulations! You got a perfect score!");
        } else if (accuracy >= 0.95) {
            System.out.println("Great job! Almost perfect!");
        } else if (accuracy >= 0.9) {
            System.out.println("Great job!");
        } else if (accuracy >= 0.8) {
            System.out.println("Good job!");
        } else if (accuracy >= 0.6) {
            System.out.println("Keep practicing!");
        } else if (accuracy >= 0.4) {
            System.out.println("Learn this tense, or try practicing a different tense!");
        } else if (accuracy > 0) {
            System.out.println("Learn the tense before doing this activity!");
        } else if (accuracy == 0) {
            System.out.println("Congratulations! You're super good at being wrong!");
        } else {
            System.out.println("How did you do so badly that you got less than 0% accuracy?");
        }
    }
    public static int getCounter() {
        Scanner input = new Scanner(System.in);
        int counter;
        while (true) {
            try {
                System.out.println("How many verbs?");
                counter = input.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println("Invalid number, please try again");
                System.out.println();
            }
        }
        input.nextLine();
        return counter;
    }
    public static int getIndex(int len) {
        int num;
        while (true) {
            num = (int) (Math.random() * len);
            if (usedIndices.size() >= len) {
                for (int i = 0; i < len/2; i++) {
                    usedIndices.removeFirst();
                }
            }
            if (!usedIndices.contains(num)) {
                usedIndices.add(num);
                return num;
            }
        }
    }


}
