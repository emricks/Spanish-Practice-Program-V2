package com.enkycode.config;

import com.enkycode.words.Verb;

import java.util.List;

public interface ConfigLoader {
    List<Verb> getVerbs(String... filepath);
}
