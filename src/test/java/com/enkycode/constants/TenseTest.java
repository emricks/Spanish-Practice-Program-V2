package com.enkycode.constants;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TenseTest {
    @Test
    public void testToSentenceCase() {
        Assertions.assertEquals("Preterite", Tense.PRETERITE.toSentenceCase());
    }
}
