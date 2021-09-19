package com.mrdiipo.autocompleteengine.entity;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode
// Model class for the suggestion returned by the engine
public class Suggestion {

    @ToString.Include
    //  returned suggestion
    private String suggestion;
    // the url returned
    private String target;
    // defines the type of suggestion
    private String group;
    // score of returned suggestion
    double score;
}
