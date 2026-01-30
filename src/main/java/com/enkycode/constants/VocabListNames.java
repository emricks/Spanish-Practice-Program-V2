package com.enkycode.constants;

public enum VocabListNames {
    COMMON20,
    COMMON40,
    COMMON60,
    COMMON80,
    COMMON100;

    public String toSentenceForm() {
        int num = Integer.parseInt(name().substring(6));
        return num + " - " + (num-19) + "-" + num + " Most Common Words";
    }
    public static VocabListNames fromString(String s) {
        return VocabListNames.valueOf("COMMON" + s);
    }
}
