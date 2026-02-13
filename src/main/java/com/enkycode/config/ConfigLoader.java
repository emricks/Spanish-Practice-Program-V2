package com.enkycode.config;

import com.enkycode.constants.VerbsListNames;
import com.enkycode.constants.VocabListNames;
import com.enkycode.words.Verb;
import com.enkycode.words.VocabWord;

import java.util.List;

public interface ConfigLoader {
    List<Verb> getVerbs(VerbsListNames vln);
    List<VocabWord> getVocabWords(VocabListNames vln);
}
