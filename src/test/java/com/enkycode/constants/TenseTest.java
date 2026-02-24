package com.enkycode.constants;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TenseTest {
    @Test
    public void testToSentenceCase() {
        Assertions.assertEquals("Preterite", Tense.PRETERITE.toSentenceCase());
        Assertions.assertEquals("Future", Tense.FUTURE.toSentenceCase());
        Assertions.assertEquals("Present", Tense.PRESENT.toSentenceCase());
    }

    @Test
    public void testFromString() {
        Assertions.assertEquals(Tense.PRETERITE, Tense.fromString("PRETERITE"));
        Assertions.assertEquals(Tense.FUTURE, Tense.fromString("future"));
        Assertions.assertEquals(Tense.PRESENT, Tense.fromString("PrESeNt"));
    }
}
