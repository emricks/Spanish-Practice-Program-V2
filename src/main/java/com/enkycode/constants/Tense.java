package com.enkycode.constants;

public enum Tense {
    PRESENT,
    PRETERITE,
    IMPERFECT,
    FUTURE,
    CONDITIONAL;

    public String toSentenceCase() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}
