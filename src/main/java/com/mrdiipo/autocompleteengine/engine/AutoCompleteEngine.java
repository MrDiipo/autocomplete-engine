package com.mrdiipo.autocompleteengine.engine;

import com.mrdiipo.autocompleteengine.entity.Suggestion;

import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

// Returns set of suggestions
public class AutoCompleteEngine {

    TreeSet<AutoCompleteFragment> treeSet = new TreeSet<>();

    private Comparator<Suggestion> SCORE_COMPARATOR = new Comparator<Suggestion>() {
        @Override
        public int compare(Suggestion o1, Suggestion o2) {
            double d = o1.getScore() - o2.getScore();
            if (d > 0) {
                return -1;
            } else {
                return 1;
            }
        }
    };

    public Set<Suggestion> autocomplete(String query, int limit){

        String normalized = Fragmenter.normalize(query); // Example of user query = "matri"
        TreeSet<Suggestion> suggestions = new TreeSet<>(SCORE_COMPARATOR);

        AutoCompleteFragment queryWrapper = new AutoCompleteFragment(null, normalized);
        String lastCharDropped = normalized.substring(0, normalized.length()-1); // this method returns matr
        String withSubsequentChar = lastCharDropped + (char) (normalized.charAt(normalized.length()-1)); // this method returns matrJ

        AutoCompleteFragment endWrapper = new AutoCompleteFragment(null, withSubsequentChar);

        SortedSet<AutoCompleteFragment> subset =  treeSet.subSet(queryWrapper, endWrapper);
        for (AutoCompleteFragment fragment: subset){
            if (suggestions.size() < limit){
                suggestions.add(fragment.getSuggestion());
            }// compare score of current suggestion with the one that is in the variable "suggestion"
             else if (SCORE_COMPARATOR.compare(fragment.getSuggestion(), suggestions.last()) < 0){
               suggestions.remove(suggestions.last());
                suggestions.add(fragment.getSuggestion());
            }
        }

        return suggestions;
    }
}
