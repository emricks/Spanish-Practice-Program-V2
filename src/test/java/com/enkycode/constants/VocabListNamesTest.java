package com.enkycode.constants;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class VocabListNamesTest {
    @Test
    @DisplayName("Testing toSentenceForm returns proper value.")
    public void testToSentenceForm() {
        Assertions.assertEquals("20 - 1-20 Most Common Words", VocabListNames.COMMON20.toSentenceForm());
        Assertions.assertEquals("60 - 41-60 Most Common Words", VocabListNames.COMMON60.toSentenceForm());
        Assertions.assertEquals("100 - 81-100 Most Common Words", VocabListNames.COMMON100.toSentenceForm());
    }

    @Test
    @DisplayName("Testing fromString returns proper value.")
    public void testFromString() {
        Assertions.assertEquals(VocabListNames.COMMON20, VocabListNames.fromString("20"));
        Assertions.assertEquals(VocabListNames.COMMON60, VocabListNames.fromString("60"));
        Assertions.assertEquals(VocabListNames.COMMON100, VocabListNames.fromString("100"));
    }
}
