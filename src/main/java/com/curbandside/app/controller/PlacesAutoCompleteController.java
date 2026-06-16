package com.curbandside.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/places")
public class PlacesAutoCompleteController {

    /**
     * Returns location autocomplete suggestions for the given query
     * (e.g. "Chicago" -> ["Chicago, IL", "Chicago Heights, IL", ...]).
     */
    @GetMapping("/autocomplete")
    public Map<String, Object> autocomplete(@RequestParam("q") String query) {

        // TODO: fetch real suggestions here (e.g. from a places/geocoding service or your DB)
        List<Map<String, String>> suggestions = List.of();

        return Map.of("suggestions", suggestions);
    }
}