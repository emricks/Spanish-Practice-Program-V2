package com.enkycode;

import com.enkycode.config.ConfigLoader;
import com.enkycode.config.JSONConfigLoader;
import com.enkycode.constants.Tense;
import com.enkycode.constants.ListNames;
import com.enkycode.words.Verb;

import java.util.*;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        ConfigLoader configLoader = new JSONConfigLoader();
        Scanner input = new Scanner(System.in);

        ListNames listName;
        // Repeats asking which list to use until a valid one is named.
        while (true) {
            System.out.println("What list?");
            for (ListNames l : ListNames.values()) {
                System.out.println(l.toSentenceForm());
            }
            try {
                listName = ListNames.valueOf(toListEnumForm(input.nextLine()));
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

        // Assigns a number based on the tense, used for lists containing only regulars/irregulars of a certain tense.
        int tenseNumber = switch (tense) {
            case PRESENT -> 0;
            case PRETERITE -> 1;
            case IMPERFECT -> 2;
            case FUTURE -> 3;
            case CONDITIONAL -> 4;
        };

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
                verb = verbList.get((int)(Math.random() * verbList.size()));
                if (include.toUpperCase().contains("IR")) {
                    if (verb.getIsIrregular()[tenseNumber]) {
                        break;
                    }
                } else if (include.toUpperCase().contains("REG")) {
                    if (!verb.getIsIrregular()[tenseNumber]) {
                        break;
                    }
                } else {
                    break;
                }
            }

            // gets the pronoun, word, and correct answer for printing and input checking.
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
        // Allows the user to re-answer questions they got wrong before.
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

        // prints a score and a message based on it
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

    // Converts a string to a form suitable for the ListNames enum
    private static String toListEnumForm(String s) {
        return s.substring(0, 6).toUpperCase() + s.substring(7);
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