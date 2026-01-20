package com.enkycode.words;

public class Verb extends Word {
    private final String presentConjugationCode;
    private final String preteriteConjugationCode;
    private final String imperfectConjugationCode;
    private String[] presentConjugations;
    private String[] preteriteConjugations;
    private String[] imperfectConjugations;

    public Verb(String word, String presentCC, String preteriteCC, String imperfectCC) {
        super(word);
        presentConjugationCode = presentCC;
        preteriteConjugationCode = preteriteCC;
        imperfectConjugationCode = imperfectCC;
    }

    public void setPresentConjugations(String[] conjugations) {
        presentConjugations = conjugations;
    }
    public void setPreteriteConjugations(String[] conjugations) {
        preteriteConjugations = conjugations;
    }
    public void setImperfectConjugations(String[] conjugations) {
        imperfectConjugations = conjugations;
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

    public String getPresentConjugationCode() {
        return presentConjugationCode;
    }
    public String getPreteriteConjugationCode() {
        return preteriteConjugationCode;
    }
    public String getImperfectConjugationCode() {
        return imperfectConjugationCode;
    }
}