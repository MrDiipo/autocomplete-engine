package com.mrdiipo.autocompleteengine.service;


import com.mrdiipo.autocompleteengine.engine.AutoCompleteEngine;
import com.mrdiipo.autocompleteengine.entity.Suggestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class RapidSuggestService {

    @Autowired
    private DataIngestionService dataIngestionService;

    public Set<Suggestion> getSuggestions(int n, String groups, String query){

        Set<Suggestion> suggestions = new HashSet<>();

        Map<String, AutoCompleteEngine> engines = dataIngestionService.getEngines();

        String[] groupsArray = groups.split(",")

        for(String g : groupsArray){
            AutoCompleteEngine autoCompleteEngine = engines.get(g);
            if (autoCompleteEngine != null){
                Set<Suggestion> results = autoCompleteEngine.autocomplete(query, n/groups.length());
            }
            return suggestions;
        }
    }
}
