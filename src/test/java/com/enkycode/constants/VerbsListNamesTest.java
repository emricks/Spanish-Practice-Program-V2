package com.enkycode.constants;

import com.enkycode.words.Verb;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class VerbsListNamesTest {
    @Test
    @DisplayName("Testing toSentenceForm returns proper value")
    public void testToSentenceForm() {
        Assertions.assertEquals("20 - Top 20 Most Common Verbs", VerbsListNames.COMMON20.toSentenceForm());
        Assertions.assertEquals("60 - Top 60 Most Common Verbs", VerbsListNames.COMMON60.toSentenceForm());
        Assertions.assertEquals("100 - Top 100 Most Common Verbs", VerbsListNames.COMMON100.toSentenceForm());
    }

    @Test
    @DisplayName("Testing fromString returns proper value")
    public void testFromString() {
        Assertions.assertEquals(VerbsListNames.COMMON20, VerbsListNames.fromString("20"));
        Assertions.assertEquals(VerbsListNames.COMMON60, VerbsListNames.fromString("60"));
        Assertions.assertEquals(VerbsListNames.COMMON100, VerbsListNames.fromString("100"));
    }
}
