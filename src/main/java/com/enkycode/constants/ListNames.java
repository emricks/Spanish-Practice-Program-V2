package com.enkycode.constants;

public enum ListNames {
    COMMON20,
    COMMON40,
    COMMON60,
    COMMON80,
    COMMON100;

    public String toSentenceForm() {
        return name().charAt(0) + name().substring(1, 6).toLowerCase() + " " + name().substring(6);
    }
}
