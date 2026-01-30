package com.enkycode.constants;

public enum VerbsListNames {
    COMMON20,
    COMMON40,
    COMMON60,
    COMMON80,
    COMMON100;

    public String toSentenceForm() {
        int num = Integer.parseInt(name().substring(6));
        return num + " - Top " + num + " Most Common Verbs";
    }

    public static VerbsListNames fromString(String s) {
        return VerbsListNames.valueOf("COMMON" + s);
    }
}
