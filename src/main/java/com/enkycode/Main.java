package com.enkycode;

import com.enkycode.config.ConfigLoader;
import com.enkycode.config.JSONConfigLoader;
import com.enkycode.constants.Tense;
import com.enkycode.words.Verb;

import java.util.*;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        ConfigLoader configLoader = new JSONConfigLoader();
        Scanner input = new Scanner(System.in);
        List<Verb> verbList;
        while (true) {
            System.out.println("What list?");
            System.out.println("Common 20");
            System.out.println("Common 40");
            String listType = input.nextLine();
            verbList = switch (listType) {
                case "Common 20" -> configLoader.getVerbs("configFiles/verbsC20.json");
                case "Common 40" -> configLoader.getVerbs("configFiles/verbsC20.json", "configFiles/verbsC40.json");
                default -> null;
            };
            if (verbList == null) {
                System.out.println();
                System.out.println("Invalid list type, please try again");
                System.out.println();
            } else {
                break;
            }
        }
        Tense tense;
        while (true) {
            System.out.println("What tense?");
            for (Tense t : Tense.values()) {
                System.out.println(t.toSentenceCase());
            }
            try {
                tense = Tense.valueOf(input.nextLine().toUpperCase());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid tense, please try again");
            }
        }



        System.out.println("How many verbs?");
        int counter = input.nextInt();
        input.nextLine();

        List<String[]> incorrect = new ArrayList<>();
        double score = 0;
        for (int j = 0; j < counter; j++) {
            Verb verb = verbList.get((int)(Math.random() * verbList.size()));
            String[] question = getQuestion(verb, tense);
            System.out.println(question[0] + " *" + question[1] + "* (" + tense + ")");
            if (input.nextLine().trim().equals(question[2])) {
                score++;
                System.out.println();
                System.out.println("Correct");
                System.out.println();
                Thread.sleep(1000);
            } else {
                System.out.println();
                System.out.println("Incorrect");
                System.out.println();
                System.out.println(question[2]);
                System.out.println();
                incorrect.add(question);
                Thread.sleep(3000);
            }
        }
        if (!incorrect.isEmpty()) {
            System.out.println("Redo questions for half credit");
            for (String[] question : incorrect) {
                System.out.println(question[0] + " *" + question[1] + "* (" + tense + ")");
                if (input.nextLine().trim().equals(question[2])) {
                    score += 0.5;
                    System.out.println();
                    System.out.println("Correct");
                } else {
                    System.out.println();
                    System.out.println("Incorrect");
                    System.out.println();
                    System.out.println(question[2]);
                }
                System.out.println();
                Thread.sleep(1000);
            }
        }
        System.out.println("Score: " + score + " out of " + counter);
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

    public static String[] getQuestion(Verb verb, Tense tense) {
        String[] singular = {"Él", "Ella", "Usted"};
        String[] plural = {"Ellos", "Ellas", "Ustedes"};
        int num = (int)(Math.random() * 6);
        String form = switch (num) {
            case 0 -> "Yo";
            case 1 -> "Tú";
            case 2 -> singular[(int)(Math.random()*3)];
            case 3 -> "Nosotros";
            case 4 -> "Vosotros";
            case 5 -> plural[(int)(Math.random()*3)];
            default -> "Error";
        };
        String word = verb.getWord();
        String answer = switch (tense) {
            case PRESENT -> verb.getPresentConjugations()[num];
            case PRETERITE -> verb.getPreteriteConjugations()[num];
            case IMPERFECT -> verb.getImperfectConjugations()[num];
            case FUTURE -> verb.getFutureConjugations()[num];
            case CONDITIONAL -> verb.getConditionalConjugations()[num];
        };
        return new String[]{form, word, answer};
    }
}