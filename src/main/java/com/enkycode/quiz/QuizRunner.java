package com.enkycode.quiz;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class QuizRunner {
    public static List<Integer> usedIndices = new ArrayList<>();
    // there is no runQuiz method since it works differently for each class.
    public static void printScoreMessage(int counter, double score) {
        System.out.println("Score: " + score + " out of " + counter + "! (" + Math.round(score*100/counter) + "%)");
        if (score > counter) {
            System.out.println("How did you get a higher score than the number of questions?");
        } else if (score == counter) {
            System.out.println("Congratulations! You got a perfect score!");
        } else if (score >= 0.95*counter) {
            System.out.println("Great job! Almost perfect!");
        } else if (score >= 0.9*counter) {
            System.out.println("Great job!");
        } else if (score >= 0.8*counter) {
            System.out.println("Good job!");
        } else if (score >= 0.6*counter) {
            System.out.println("Keep practicing!");
        } else if (score >= 0.4*counter) {
            System.out.println("Learn this tense, or try practicing a different tense!");
        } else if (score > 0) {
            System.out.println("Learn the tense before doing this activity!");
        } else if (score == 0) {
            System.out.println("Congratulations! You're super good at being wrong!");
        } else {
            System.out.println("How did you do so badly that you got a negative score?");
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
