package com.enkycode.constants;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TenseTest {
    @Test
    public void testToSentenceCase() {
        Assertions.assertEquals("Preterite", Tense.PRETERITE.toSentenceCase());
        Assertions.assertEquals("Future", Tense.FUTURE.toSentenceCase());
        Assertions.assertEquals("Present Perfect", Tense.PRESENT_PERFECT.toSentenceCase());
    }

    @Test
    public void testFromString() {
        Assertions.assertEquals(Tense.PRETERITE, Tense.fromString("Preterite"));
        Assertions.assertEquals(Tense.FUTURE, Tense.fromString("Future"));
        Assertions.assertEquals(Tense.PRESENT_PERFECT, Tense.fromString("present pErFEct"));
    }
}
