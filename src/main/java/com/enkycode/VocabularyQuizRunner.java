package com.enkycode;

import com.enkycode.config.ConfigLoader;
import com.enkycode.config.JSONConfigLoader;
import com.enkycode.words.VocabWord;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VocabularyQuizRunner extends QuizRunner {
    public static void runQuiz() throws InterruptedException {
        ConfigLoader configLoader = new JSONConfigLoader();
        Scanner input = new Scanner(System.in);

        System.out.println("Which vocabulary list?");
        System.out.println("20 - 1-20 Most Common Words\n40 - 21-40 Most Common Words");
        List<VocabWord> vocabWords = configLoader.getVocabWords("configFiles/vocabC"+input.nextLine()+".json");

        System.out.println("Which mode?");
        System.out.println("ES - English->Spanish");
        System.out.println("SE - Spanish->English");
        String mode = input.nextLine();

        System.out.println("How many words?");
        int counter = input.nextInt();
        input.nextLine();

        List<VocabWord> incorrect = new ArrayList<>();
        double score = 0;
        if (mode.equalsIgnoreCase("ES")) {
            for (int j = 0; j < counter; j++) {
                VocabWord vocab = vocabWords.get((int) (Math.random() * vocabWords.size()));
                String word = vocab.getWord();
                String englishDisplay = vocab.getEnglishDisplay();
                System.out.println(englishDisplay + " (English -> Spanish)");
                String userAnswer = input.nextLine();
                boolean correct = checkESAnswer(userAnswer, word);
                if (correct) {
                    score++;
                    System.out.println();
                    System.out.println("Correct");
                    System.out.println();
                    Thread.sleep(1000);
                } else {
                    System.out.println();
                    System.out.println("Incorrect");
                    System.out.println();
                    System.out.println(word);
                    System.out.println();
                    incorrect.add(vocab);
                    Thread.sleep(3000);
                }
            }
            if (!incorrect.isEmpty()) {
                System.out.println("Redo questions for half credit");
                Thread.sleep(2000);
                System.out.println();
                for (VocabWord vocab : incorrect) {
                    String word = vocab.getWord();
                    String englishDisplay = vocab.getEnglishDisplay();
                    System.out.println(englishDisplay + " (English -> Spanish)");

                    String userAnswer = input.nextLine();
                    boolean correct = checkESAnswer(userAnswer, word);
                    if (correct) {
                        score += 0.5;
                        System.out.println();
                        System.out.println("Correct");
                    } else {
                        System.out.println();
                        System.out.println("Incorrect");
                        System.out.println();
                        System.out.println(word);
                    }
                    System.out.println();
                    Thread.sleep(1000);
                }
            }
            printScoreMessage(counter, score);
        } else {



            for (int j = 0; j < counter; j++) {
                VocabWord vocab = vocabWords.get((int) (Math.random() * vocabWords.size()));
                String word = vocab.getWord();
                String[] translations = vocab.getTranslations();
                String englishDisplay = vocab.getEnglishDisplay();
                System.out.println(word + " (Spanish -> English)");
                String userAnswer = input.nextLine();
                boolean correct = checkSEAnswer(userAnswer, translations);
                if (correct) {
                    score++;
                    System.out.println();
                    System.out.println("Correct");
                    System.out.println();
                    Thread.sleep(1000);
                } else {
                    System.out.println();
                    System.out.println("Incorrect");
                    System.out.println();
                    System.out.println(englishDisplay);
                    System.out.println();
                    incorrect.add(vocab);
                    Thread.sleep(3000);
                }
            }
            if (!incorrect.isEmpty()) {
                System.out.println("Redo questions for half credit");
                Thread.sleep(2000);
                System.out.println();
                for (VocabWord vocab : incorrect) {
                    String word = vocab.getWord();
                    String englishDisplay = vocab.getEnglishDisplay();
                    String[] translations = vocab.getTranslations();
                    System.out.println(word + " (Spanish -> English)");

                    String userAnswer = input.nextLine();
                    boolean correct = checkSEAnswer(userAnswer, translations);
                    if (correct) {
                        score += 0.5;
                        System.out.println();
                        System.out.println("Correct");
                    } else {
                        System.out.println();
                        System.out.println("Incorrect");
                        System.out.println();
                        System.out.println(englishDisplay);
                    }
                    System.out.println();
                    Thread.sleep(1000);
                }
            }
            printScoreMessage(counter, score);
        }
    }

    private static boolean checkESAnswer(String userAnswer, String word) {
        return userAnswer.toLowerCase().trim().contains(word.toLowerCase());
    }
    private static boolean checkSEAnswer(String userAnswer, String[] translations) {
        boolean correct = false;
        for (String translation : translations) {
            if (userAnswer.toLowerCase().trim().contains(translation.toLowerCase())) {
                correct = true;
                break;
            }
        }
        return correct;
    }
}
