package com.enkycode;

import com.enkycode.config.CSVConfigLoader;
import com.enkycode.words.Verb;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        List<Verb> verbList = CSVConfigLoader.getVerbs("configFiles/verbsC20.txt");
        Scanner input = new Scanner(System.in);

        System.out.println("What tense?");
        System.out.println("Present");
        System.out.println("Preterite");
        System.out.println("Imperfect");
        String tense = input.nextLine();

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
                    System.out.println("Correct!");
                    System.out.println();
                    Thread.sleep(1000);
                }
            }
        }
        System.out.println("Score: " + score + " out of " + counter);
    }

    public static String[] getQuestion(Verb verb, String tense) {
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
            case "Present" -> verb.getPresentConjugations()[num];
            case "Preterite" -> verb.getPreteriteConjugations()[num];
            case "Imperfect" -> verb.getImperfectConjugations()[num];
            default -> "Error";
        };
        return new String[]{form, word, answer};
    }
}