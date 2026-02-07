package com.enkycode.constants;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class VerbsListNamesTest {
    @Test
    @DisplayName("Testing toSentenceForm returns proper value")
    public void testToSentenceForm() {
        Assertions.assertEquals("20 - " + "Top 20 Most Common Verbs", VerbsListNames.COMMON20.toSentenceForm());
    }
}
