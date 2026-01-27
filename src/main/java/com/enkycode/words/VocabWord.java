package com.enkycode.words;

public class VocabWord extends Word {
    private String[] validTranslations;
    private String englishDisplay;

    public VocabWord(String word) {
        super(word);
    }

    public String[] getTranslations() {
        return validTranslations;
    }
    public String getEnglishDisplay() {
        return englishDisplay;
    }
}
