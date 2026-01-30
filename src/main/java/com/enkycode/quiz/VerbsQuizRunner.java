package com.enkycode.quiz;

import com.enkycode.config.ConfigLoader;
import com.enkycode.config.JSONConfigLoader;
import com.enkycode.constants.VerbsListNames;
import com.enkycode.constants.Tense;
import com.enkycode.words.Verb;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VerbsQuizRunner extends QuizRunner {
    public static void runQuiz() throws InterruptedException {
        ConfigLoader configLoader = new JSONConfigLoader();
        Scanner input = new Scanner(System.in);

        VerbsListNames listName;
        // Repeats asking which list to use until a valid one is named.
        while (true) {
            System.out.println("What list?");
            for (VerbsListNames l : VerbsListNames.values()) {
                System.out.println(l.toSentenceForm());
            }
            try {
                listName = VerbsListNames.fromString(input.nextLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println();
                System.out.println("Invalid list, please try again");
                System.out.println();
            }
        }

        Tense tense;
        // Repeats asking which tense to use until a valid one is named
        while (true) {
            System.out.println("What tense?");
            for (Tense t : Tense.values()) {
                System.out.println(t.toSentenceCase());
            }
            try {
                tense = Tense.valueOf(input.nextLine().toUpperCase());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println();
                System.out.println("Invalid tense, please try again");
                System.out.println();
            }
        }

        // Gets the list of Verb objects to use, uses different JSON files based on the list chosen previously.
        List<Verb> verbList = configLoader.getVerbs(switch (listName) {
            case COMMON20 -> new String[]{"configFiles/verbsC20.json"};
            case COMMON40 -> new String[]{"configFiles/verbsC20.json", "configFiles/verbsC40.json"};
            case COMMON60 -> new String[]{"configFiles/verbsC20.json", "configFiles/verbsC40.json", "configFiles/verbsC60.json"};
            case COMMON80 -> new String[]{"configFiles/verbsC20.json", "configFiles/verbsC40.json", "configFiles/verbsC60.json", "configFiles/verbsC80.json"};
            case COMMON100 -> new String[]{"configFiles/verbsC20.json", "configFiles/verbsC40.json", "configFiles/verbsC60.json", "configFiles/verbsC80.json", "configFiles/verbsC100.json"};
        });

        // Choose whether to include regular verbs, irregular verbs, or both depending on the tense.
        System.out.println("(EXPERIMENTAL) What to include?");
        System.out.println("Regulars\nIrregulars\nBoth");
        String include = input.nextLine();

        System.out.println("How many verbs?");
        int counter = input.nextInt();
        input.nextLine();

        List<String[]> incorrect = new ArrayList<>();
        double score = 0;
        for (int j = 0; j < counter; j++) {
            Verb verb;
            while (true) {
                // Loops through, choosing verbs until one that matches the user's irregular/regular/both preference is chosen.
                verb = verbList.get(getIndex(verbList.size()));
                if (include.toUpperCase().contains("IR")) {
                    if (verb.getIsIrregular(tense)) {
                        break;
                    }
                } else if (include.toUpperCase().contains("REG")) {
                    if (!verb.getIsIrregular(tense)) {
                        break;
                    }
                } else {
                    break;
                }
            }

            // gets the pronoun, word, and correct answer for printing and input checking.
            String[] question = getQuestion(verb, tense);
            System.out.println(question[0] + " *" + question[1] + "* (" + tense.toSentenceCase() + ")");
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
        // Allows the user to re-answer questions they got wrong before.
        if (!incorrect.isEmpty()) {
            System.out.println("Redo questions for half credit");
            Thread.sleep(2000);
            System.out.println();
            for (String[] question : incorrect) {
                System.out.println(question[0] + " *" + question[1] + "* (" + tense.toSentenceCase() + ")");
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

        // prints a score and a message based on it
        printScoreMessage(counter, score);
    }
    public static String[] getQuestion(Verb verb, Tense tense) {
        // Gets the form based on random numbers
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

        // Gets the correct conjugation based on the form and tense
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
