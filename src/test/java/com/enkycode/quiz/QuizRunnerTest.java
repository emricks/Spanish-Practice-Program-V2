package com.enkycode.quiz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.github.stefanbirkner.systemlambda.SystemLambda;


import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

public class QuizRunnerTest {
    @Test
    @DisplayName("Testing printScoreMessage prints correctly")
    public void testPrintScoreMessage() throws Exception {
        String output = SystemLambda.tapSystemOut(() -> QuizRunner.printScoreMessage(50, 45));
        Assertions.assertEquals("Score: 45.0 out of 50! (90%)\nGreat job!\n", output);
        output = SystemLambda.tapSystemOut(() -> QuizRunner.printScoreMessage(20, 11));
        Assertions.assertEquals("Score: 11.0 out of 20! (55%)\nLearn this tense, or try practicing a different tense!\n", output);
        output = SystemLambda.tapSystemOut(() -> QuizRunner.printScoreMessage(1000, 694.5));
        Assertions.assertEquals("Score: 694.5 out of 1000! (69%)\nKeep practicing!\n", output);
    }

    @Test
    @DisplayName("Testing getCounter works properly")
    public void testGetCounter() {
        String input = "37\n";
        ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes());
        System.setIn(bais);
        int counter = QuizRunner.getCounter();
        Assertions.assertEquals(37, counter);

        input = "1000000\n";
        bais = new ByteArrayInputStream(input.getBytes());
        System.setIn(bais);
        counter = QuizRunner.getCounter();
        Assertions.assertEquals(1000000, counter);

    }

    @Test
    @DisplayName("Testing getIndex works properly")
    public void testGetIndex() {
        QuizRunner.usedIndices = new ArrayList<>(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19));
        int idx = QuizRunner.getIndex(20);
        Assertions.assertEquals(9, idx);
        idx = QuizRunner.getIndex(20);
        Assertions.assertEquals(11, QuizRunner.usedIndices.size());
        Assertions.assertEquals(11, QuizRunner.usedIndices.getFirst());
    }
}
