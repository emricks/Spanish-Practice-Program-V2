package com.enkycode.words;

import com.enkycode.constants.Tense;

public class Verb extends Word {
    private Conjugation presentConjugations;
    private Conjugation preteriteConjugations;
    private Conjugation imperfectConjugations;
    private Conjugation futureConjugations;
    private Conjugation conditionalConjugations;
    private Conjugation presentPerfectConjugations;

    public Verb(String word) {
        super(word);
    }
    public String[] getPresentConjugations() {
        return presentConjugations.getConjugations();
    }
    public String[] getPreteriteConjugations() {
        return preteriteConjugations.getConjugations();
    }
    public String[] getImperfectConjugations() {
        return imperfectConjugations.getConjugations();
    }
    public String[] getFutureConjugations() {
        return futureConjugations.getConjugations();
    }
    public String[] getConditionalConjugations() {
        return conditionalConjugations.getConjugations();
    }
    public String[] getPresentPerfectConjugations() {
        return presentPerfectConjugations.getConjugations();
    }
    public boolean getIsIrregular(Tense tense) {
        return switch (tense) {
            case PRESENT -> presentConjugations.getIsIrregular();
            case PRETERITE -> preteriteConjugations.getIsIrregular();
            case IMPERFECT -> imperfectConjugations.getIsIrregular();
            case FUTURE -> futureConjugations.getIsIrregular();
            case CONDITIONAL -> conditionalConjugations.getIsIrregular();
            case PRESENT_PERFECT ->  presentPerfectConjugations.getIsIrregular();
        };
    }
}
