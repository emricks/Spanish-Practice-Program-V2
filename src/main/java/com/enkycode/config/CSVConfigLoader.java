package com.enkycode.config;

import com.enkycode.words.Verb;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVConfigLoader {

    public static List<Verb> getVerbs(String filepath) {
        List<Verb> verbs = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Assuming comma delimiter
                String[] parts = line.split(",");

                if (parts.length >= 2) {
                    Verb verb = new Verb(parts[0], parts[1].trim(), parts[2].trim(), parts[3].trim());
                    conjugate(verb);
                    verbs.add(verb);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return verbs;
    }

    public static void conjugate(Verb verb) {
        verb.setPresentConjugations(conjugatePresent(verb.getWord(), verb.getPresentConjugationCode()));
        verb.setPreteriteConjugations(conjugatePreterite(verb.getWord(), verb.getPreteriteConjugationCode()));
        verb.setImperfectConjugations(conjugateImperfect(verb.getWord(), verb.getImperfectConjugationCode()));
    }

    private static String[] conjugatePresent(String word, String code) {
        String[] conjugations;
        String stem = word.substring(0, word.length()-2);
        String end = word.substring(word.length()-2);
        boolean independent = false;

        if (code.contains("go/")) {
            // conjugates go + stem changer verbs
            String change = code.substring(3);
            int vIndex = findLastVowel(stem);
            String modifiedStem = stem.substring(0, vIndex) + change + stem.substring(vIndex+1);

            conjugations = switch (end) {
                case "ar" -> new String[]{"go", "as", "a", "amos", "áis", "an"};
                case "er" -> new String[]{"go", "es", "e", "emos", "éis", "en"};
                case "ir" -> new String[]{"go", "es", "e", "imos", "ís", "en"};
                default -> new String[]{"ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR"};
            };
            for (int i=0;i<6;i++) {
                if (i==0||i==3||i==4) {
                    conjugations[i] = stem + conjugations[i];
                } else {
                    conjugations[i] = modifiedStem + conjugations[i];
                }
            }
            independent = true;

        } else if (code.contains("-")) {
            // conjugates verbs with just one irregular spot
            int idx = code.indexOf("-");
            String change = code.substring(idx+1);
            conjugations = switch (end) {
                case "ar" -> new String[]{change, "as", "a", "amos", "áis", "an"};
                case "er" -> new String[]{change, "es", "e", "emos", "éis", "en"};
                case "ir" -> new String[]{change, "es", "e", "imos", "ís", "en"};
                default -> new String[]{"ERROR", "ERROR", "ERROR", "ERROR", "ERROR"};
            };

        } else if (code.contains(".")) {
            // conjugates verbs without an irregularity
            conjugations = normalPresentConjugation(end);

        } else if (code.contains("X")) {
            // conjugates verbs too irregular for other categories
            conjugations = code.substring(2).split(":");
            independent = true;
        } else {
            // conjugates simple stem changing verbs
            int vIndex = findLastVowel(stem);
            String modifiedStem = stem.substring(0, vIndex) + code + stem.substring(vIndex+1);

            conjugations = normalPresentConjugation(end);
            for (int i=0;i<6;i++) {
                if (i==3||i==4) {
                    conjugations[i] = stem + conjugations[i];
                } else {
                    conjugations[i] = modifiedStem + conjugations[i];
                }
            }
            independent = true;
        }

        // add the stems if it didn't add the stems during conjugation already
        if (!independent) {
            for (int i=0;i<6;i++) {
                if (!(i==0 && code.contains("-"))) {
                    conjugations[i] = stem + conjugations[i];
                }
            }
        }
        return conjugations;
    }

    private static String[] normalPresentConjugation(String end) {
        String[] conjugations;
        conjugations = switch (end) {
            case "ar" -> new String[]{"o", "as", "a", "amos", "áis", "an"};
            case "er" -> new String[]{"o", "es", "e", "emos", "éis", "en"};
            case "ir" -> new String[]{"o", "es", "e", "imos", "ís", "en"};
            default -> new String[]{"ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR"};
        };
        return conjugations;
    }

    private static String[] conjugatePreterite(String word, String code) {
        String[] conjugations;
        String stem = word.substring(0, word.length()-2);
        String end = word.substring(word.length()-2);
        boolean independent = false;
        if (code.contains("I")) {
            // conjugates verbs that have an i in place of the first vowel
            int vIndex = findFirstVowel(stem);
            String modifiedStem = stem.substring(0, vIndex) + "i" + stem.substring(vIndex + 1);
            conjugations = switch (end) {
                case "ar" -> new String[]{"é", "aste", "ó", "amos", "asteis", "aron"};
                case "er", "ir" -> new String[]{"í", "iste", "ió", "imos", "isteis", "ieron"};
                default -> new String[]{"ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "I"};
            };
            for (int i = 0; i < 6; i++) {
                if (i == 2 || i == 5) {
                    conjugations[i] = modifiedStem + conjugations[i];
                } else {
                    conjugations[i] = stem + conjugations[i];
                }
            }
            independent = true;
        } else if (code.contains("car")) {
            // conjugates verbs ending in car
            conjugations = normalPreteriteConjugation(end);
            for (int i = 0; i < 6; i++) {
                if (i == 0) {
                    conjugations[i] = stem.substring(0, stem.length() - 1) + "qué";
                } else {
                    conjugations[i] = stem + conjugations[i];
                }
            }
            independent = true;
        } else if (code.contains("gar")) {
            // conjugates verbs ending in gar
            conjugations = normalPreteriteConjugation(end);
            for (int i = 0; i < 6; i++) {
                if (i == 0) {
                    conjugations[i] = stem.substring(0, stem.length()-1) + "gué";
                } else {
                    conjugations[i] = stem + conjugations[i];
                }
            }
            independent = true;
        } else if (code.contains("zar")) {
            // conjugates verbs ending in zar
            conjugations = normalPresentConjugation(stem);
            for (int i = 0; i < 6; i++) {
                if (i == 0) {
                    conjugations[i] = stem.substring(0, stem.length() - 1) + "cé";
                } else {
                    conjugations[i] = stem + conjugations[i];
                }
            }
            independent = true;
        } else if (code.contains(".")) {
            conjugations = normalPreteriteConjugation(end);
        } else if (code.contains("X")) {
            conjugations = code.substring(2).split(":");
            independent = true;
        } else if (code.contains("W")) {
            conjugations = new String[]{"í", "íste", "yó", "ímos", "ísteis", "yeron"};
        } else {
            // conjugates verbs with a different stem than usual
            conjugations = new String[]{"e", "iste", "o", "imos", "isteis", "ieron"};
            for (int i = 0; i < 6; i++) {
                conjugations[i] = code + conjugations[i];
            }
            independent = true;
        }

        // if the conjugation didn't give it a stem, add the stem on
        if (!independent) {
            for (int i=0;i<6;i++) {
                conjugations[i] = stem + conjugations[i];
            }
        }
        return conjugations;
    }

    private static String[] normalPreteriteConjugation(String end) {
        return switch (end) {
            case "ar" -> new String[]{"é", "aste", "ó", "amos", "asteis", "aron"};
            case "er", "ir" -> new String[]{"í", "iste", "ió", "imos", "isteis", "ieron"};
            default -> new String[]{"ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR"};
        };
    }

    private static String[] conjugateImperfect(String word, String code) {
        String stem = word.substring(0, word.length()-2);
        String end = word.substring(word.length()-2);
        return switch (word) {
            case "ser", "ir", "ver" -> code.substring(2).split(":");
            default -> end.equals("ar") ? new String[]{stem+"aba",stem+"abas",stem+"aba",stem+"ábamos",stem+"abais",stem+"aban"} : new String[]{stem+"ía",stem+"ías",stem+"ía",stem+"íamos",stem+"íais",stem+"ían"};
        };
    }

    private static int findLastVowel(String word) {
        for (int i=word.length()-1;i>0;i--) {
            if (word.charAt(i)=='a'||word.charAt(i)=='e'||word.charAt(i)=='i'||word.charAt(i)=='o'||word.charAt(i)=='u' ) {
                return i;
            }
        }
        return -1;
    }
    private static int findFirstVowel(String word) {
        for (int i=0;i<word.length();i++) {
            if (word.charAt(i)=='a'||word.charAt(i)=='e'||word.charAt(i)=='i'||word.charAt(i)=='o'||word.charAt(i)=='u' ) {
                return i;
            }
        }
        return -1;
    }
}
