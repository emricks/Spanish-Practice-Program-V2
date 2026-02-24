package com.enkycode.constants;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Objects;
import java.util.stream.Stream;

public class ToSentenceFormTest {
    @ParameterizedTest
    @MethodSource("testArgs")
    public void testToSentenceForm(String expected, VerbsListNames listName, Boolean shouldMatch) {
        boolean isMatch = Objects.equals(listName.toSentenceForm(), expected);
        Assertions.assertEquals(isMatch, shouldMatch);
    }

    private static Stream<Arguments> testArgs() {
        return Stream.of(
                Arguments.of("20 - Top 20 Most Common Verbs", VerbsListNames.COMMON20, true),
                Arguments.of("60 - Top 60 Most Common Verbs", VerbsListNames.COMMON60, true),
                Arguments.of("100 - Top 100 Most Common Verbs", VerbsListNames.COMMON100, true),
                Arguments.of("foo", VerbsListNames.COMMON20, false)
        );
    }
}
