package com.enkycode.config;

import com.enkycode.words.Verb;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JSONConfigLoader implements ConfigLoader {

    public List<Verb> getVerbs(String... filepath) {
        Gson gson = new Gson();
        List<Verb> verbs = new ArrayList<>();
        for (String file : filepath) {
            try (FileReader reader = new FileReader(file)) {
                Type listType = new TypeToken<List<Verb>>(){}.getType();
                verbs.addAll(gson.fromJson(reader, listType));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return verbs;
    }
}
