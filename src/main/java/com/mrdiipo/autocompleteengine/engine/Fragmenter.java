package com.mrdiipo.autocompleteengine.engine;

import com.mrdiipo.autocompleteengine.entity.Suggestion;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public class Fragmenter {

    public static List<String> getFragments(Suggestion s){
        List<String> words = new ArrayList<>();
        String normalized = normalize((s.getSuggestion()));
        for (String w: s.getSuggestion().split("\\s+")) {
            words.add(w);
        }

        // enable querying from any position
        List<String> fragments = new ArrayList<>();
        String suffix = "";

        for (int i=words.size() -1; i >=0; i--){

            if (!suffix.isEmpty()){
                suffix = " " + suffix;
            }
            suffix = words.get(i) + suffix;
            fragments.add(suffix);
        }
        return fragments; // from a String like "Once beaten twice shy" it returns [shy, twice shy, beaten twice shy, Once beaten twice shy}
    }

    // Normalize non ASCII characters
    public static String normalize(String dirty){
        return Normalizer.normalize(dirty, Normalizer.Form.NFD) // normalize to lower case
                .replaceAll("[*\\p{ASCII}]", "" )
                .toLowerCase()
                .replace("-", "");
    }

}
