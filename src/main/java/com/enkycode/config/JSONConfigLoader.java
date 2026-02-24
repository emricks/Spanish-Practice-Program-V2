package com.enkycode.config;

import com.enkycode.constants.VerbsListNames;
import com.enkycode.constants.VocabListNames;
import com.enkycode.stats.*;
import com.enkycode.words.Verb;
import com.enkycode.words.VocabWord;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class JSONConfigLoader implements ConfigLoader {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public static VerbStats stats = new VerbStats();
    public static final DefaultStats defaultStats = new DefaultStats();

    @Override
    public List<Verb> getVerbs(VerbsListNames vln) {
        String[] files = switch (vln) {
            case COMMON20 -> new String[]{"configFiles/verbsC20.json"};
            case COMMON40 -> new String[]{"configFiles/verbsC20.json", "configFiles/verbsC40.json"};
            case COMMON60 -> new String[]{"configFiles/verbsC20.json", "configFiles/verbsC40.json", "configFiles/verbsC60.json"};
            case COMMON80 -> new String[]{"configFiles/verbsC20.json", "configFiles/verbsC40.json", "configFiles/verbsC60.json", "configFiles/verbsC80.json"};
            case COMMON100 -> new String[]{"configFiles/verbsC20.json", "configFiles/verbsC40.json", "configFiles/verbsC60.json", "configFiles/verbsC80.json", "configFiles/verbsC100.json"};
        };
        return loadVerbs(files);
    }
    private List<Verb> loadVerbs(String... filepath) {
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
    public List<VocabWord> getVocabWords(VocabListNames vln) {
        String file = switch (vln) {
            case COMMON20 -> "configFiles/vocabC20.json";
            case COMMON40 -> "configFiles/vocabC40.json";
            case COMMON60 -> "configFiles/vocabC60.json";
            case COMMON80 -> "configFiles/vocabC80.json";
            case COMMON100 -> "configFiles/vocabC100.json";
        };
        return loadVocabWords(file);
    }
    private List<VocabWord> loadVocabWords(String... filepath) {
        List<VocabWord> vocabWords = new ArrayList<>();
        for (String file : filepath) {
            try (FileReader reader = new FileReader(file)) {
                Type listType = new TypeToken<List<VocabWord>>(){}.getType();
                vocabWords.addAll(gson.fromJson(reader, listType));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return vocabWords;
    }

    public static void statsToJson() {
        try (Writer writer = new FileWriter("configFiles/verbStats.json", StandardCharsets.UTF_8)) {
            gson.toJson(stats, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void resetStats() {
        try (Writer writer = new FileWriter("configFiles/verbStats.json", StandardCharsets.UTF_8)) {
            gson.toJson(defaultStats, writer);
        }  catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void loadStats() {
        try (FileReader reader = new FileReader("configFiles/verbStats.json")) {
            stats = gson.fromJson(reader, VerbStats.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
