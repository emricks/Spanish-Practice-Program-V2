package com.enkycode.constants;

public enum Tense {
    PRESENT,
    PRETERITE,
    IMPERFECT,
    FUTURE,
    CONDITIONAL,
    PRESENT_PERFECT;

    public String toSentenceCase() {
        if (name().contains("_")) {
            String p1 = name().split("_")[0];
            String p2 = name().split("_")[1];
            p1 = p1.charAt(0) + p1.substring(1).toLowerCase();
            p2 = p2.charAt(0) + p2.substring(1).toLowerCase();
            return p1 + " " + p2;
        }
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
    public static Tense fromString(String tense) {
        return valueOf(tense.replace(" ", "_").toUpperCase());
    }
}
