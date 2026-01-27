package com.enkycode.config;

import com.enkycode.words.Verb;
import com.enkycode.words.VocabWord;

import java.util.List;

public interface ConfigLoader {
    List<Verb> getVerbs(String... filepath);
    List<VocabWord> getVocabWords(String... filepath);
}
