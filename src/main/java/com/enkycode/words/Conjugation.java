package com.enkycode.words;

public class Conjugation {
    private String[] conjugations;
    private boolean isIrregular;

    public String[] getConjugations() {
        return conjugations;
    }
    public boolean getIsIrregular() {
        return isIrregular;
    }
    public String getFirstPersonSingularConjugation() {
        return conjugations[0];
    }
    public String getSecondPersonSingularConjugation() {
        return conjugations[1];
    }
    public String getThirdPersonSingularConjugation() {
        return conjugations[2];
    }
    public String getFirstPersonPluralConjugation() {
        return conjugations[3];
    }
    public String getSecondPersonPluralConjugation() {
        return conjugations[4];
    }
    public String getThirdPersonPluralConjugation() {
        return conjugations[5];
    }
}
