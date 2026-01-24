package com.enkycode.words;

public class Verb extends Word {
    private String[] presentConjugations;
    private String[] preteriteConjugations;
    private String[] imperfectConjugations;
    private String[] futureConjugations;
    private String[] conditionalConjugations;
    private Boolean[] isIrregular;

    public Verb(String word) {
        super(word);
    }
    public String[] getPresentConjugations() {
        return presentConjugations;
    }
    public String[] getPreteriteConjugations() {
        return preteriteConjugations;
    }
    public String[] getImperfectConjugations() {
        return imperfectConjugations;
    }
    public String[] getFutureConjugations() {
        return futureConjugations;
    }
    public String[] getConditionalConjugations() {
        return conditionalConjugations;
    }
    public Boolean[] getIsIrregular() {
        return isIrregular;
    }
}
