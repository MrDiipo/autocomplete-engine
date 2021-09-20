package com.mrdiipo.autocompleteengine.controller;


import com.mrdiipo.autocompleteengine.entity.Suggestion;
import com.mrdiipo.autocompleteengine.service.DataIngestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestApiController {

    @Autowired
    private DataIngestionService dataIngestionService;

    @PostMapping(value = "/post", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getSuggestion(@RequestBody List<Suggestion> suggestions){

        dataIngestionService.submitSuggestion(suggestions);
        return new ResponseEntity(HttpStatus.OK);

    }

}
