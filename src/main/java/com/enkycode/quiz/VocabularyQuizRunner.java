package com.enkycode.quiz;

import com.enkycode.config.ConfigLoader;
import com.enkycode.config.JSONConfigLoader;
import com.enkycode.constants.VocabListNames;
import com.enkycode.words.VocabWord;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VocabularyQuizRunner extends QuizRunner {
    public static void runQuiz() throws InterruptedException {
        ConfigLoader configLoader = new JSONConfigLoader();
        Scanner input = new Scanner(System.in);

        VocabListNames listName;
        // Repeats asking which list to use until a valid one is named.
        while (true) {
            System.out.println("What list?");
            for (VocabListNames l : VocabListNames.values()) {
                System.out.println(l.toSentenceForm());
            }
            try {
                listName = VocabListNames.fromString(input.nextLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println();
                System.out.println("Invalid list, please try again");
                System.out.println();
            }
        }
        List<VocabWord> vocabWords = configLoader.getVocabWords(switch (listName) {
            case COMMON20 -> "configFiles/vocabC20.json";
            case COMMON40 -> "configFiles/vocabC40.json";
            case COMMON60 -> "configFiles/vocabC60.json";
            case COMMON80 -> "configFiles/vocabC80.json";
            case COMMON100 -> "configFiles/vocabC100.json";
        });

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
                VocabWord vocab = vocabWords.get(getIndex(vocabWords.size()));
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
