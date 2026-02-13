package com.enkycode.config;

import com.enkycode.constants.VerbsListNames;
import com.enkycode.words.Verb;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestJSONConfigLoader {

    @Test
    public void testGetVerbs() {
        ConfigLoader configLoader = new JSONConfigLoader();
        List<Verb> verbs = configLoader.getVerbs(VerbsListNames.COMMON20);
        Assertions.assertEquals(20, verbs.size());
        Assertions.assertEquals("hablar", verbs.getLast().getWord());
        Assertions.assertEquals("será", verbs.getFirst().getFutureConjugations()[2]);
    }
}
