package com.enkycode.config;

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
        List<Verb> verbs = configLoader.getVerbs("configFiles/verbsC20.json");
        Assertions.assertEquals(20, verbs.size());
        Assertions.assertEquals("hablar", verbs.getLast().getWord());
        Assertions.assertEquals("será", verbs.getFirst().getFutureConjugation().getThirdPersonSingularConjugation());
    }
    @Test
    public void testGetVerbsFail() {
        assertThrows(RuntimeException.class, () -> {
            ConfigLoader configLoader = new JSONConfigLoader();
            List<Verb> verbs = configLoader.getVerbs("configFiles/howeebews.json");
        });
    }
}
