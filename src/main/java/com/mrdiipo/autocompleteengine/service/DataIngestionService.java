package com.mrdiipo.autocompleteengine.service;

import com.mrdiipo.autocompleteengine.engine.AutoCompleteEngine;
import com.mrdiipo.autocompleteengine.entity.Suggestion;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DataIngestionService {

    Map<String, AutoCompleteEngine> engines = new HashMap<>();

    public void submitSuggestion(List<Suggestion> suggestions) {

        for (Suggestion s : suggestions){
            AutoCompleteEngine engine;
            String group = s.getGroup();
            if (engines.containsKey(group)){
                engine = engines.get(group);
            } else{
                engine = new AutoCompleteEngine();
                engines.put(group, engine);
            }
        }
    }
}
