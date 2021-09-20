package com.mrdiipo.autocompleteengine.controller;


import com.mrdiipo.autocompleteengine.entity.Suggestion;
import com.mrdiipo.autocompleteengine.service.DataIngestionService;
import com.mrdiipo.autocompleteengine.service.RapidSuggestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@org.springframework.web.bind.annotation.RestController
public class RestApiController {

    @Autowired
    private DataIngestionService dataIngestionService;

    @Autowired
    private RapidSuggestService rapidSuggestService;

    @PostMapping(value = "/post", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getSuggestion(@RequestBody List<Suggestion> suggestions){

        dataIngestionService.submitSuggestion(suggestions);
        return new ResponseEntity(HttpStatus.OK);

    }

    @GetMapping(value = "/rapidsuggest", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity autocomplete(@RequestParam("q") String query,
        @RequestParam(name = "n", defaultValue = "10") int n,
    @RequestParam(name = "g", defaultValue = "movies, people") String groups){


    Set<Suggestion> results = rapidSuggestService.getSuggestions(n, groups, query);
        return new ResponseEntity(results, HttpStatus.OK);


    }

}
