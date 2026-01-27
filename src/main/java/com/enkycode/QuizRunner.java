package com.enkycode;

public class QuizRunner {
    // there is no runQuiz method since it works differently for each child class.
    static void printScoreMessage(int counter, double score) {
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
}
