package com.curbandside.app.controller;

import com.curbandside.app.services.LocationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/places")
public class PlacesAutoCompleteController {
    private final LocationService locationService;

    public PlacesAutoCompleteController(LocationService locationService) {
        this.locationService = locationService;
    }

    /**
     * Returns location autocomplete suggestions for the given query
     * (e.g. "Chicago" -> ["Chicago, IL", "Chicago Heights, IL", ...]).
     */
    @GetMapping("/autocomplete")
    public List<String> autocomplete(@RequestParam("q") String query) {
        return locationService.getCities(query);
    }
}