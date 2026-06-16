package com.curbandside.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Value("${mapbox.access-token}")
    private String mapboxAccessToken;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/browse")
    public String browsePage(
            @RequestParam(value = "lat", required = false) Double lat,
            @RequestParam(value = "lng", required = false) Double lng,
            @RequestParam(value = "city", required = false) String city,
            @RequestParam(value = "countryIso", required = false) String countryIso,
            Model model) {

        if (lat != null && lng != null) {
            // Validate physical globe boundaries
            if (lat < -90 || lat > 90 || lng < -180 || lng > 180) {
                // Reset to default if numbers are outside real world boundaries
                model.addAttribute("lat", 41.8781);
                model.addAttribute("lng", -87.6298);
            } else {
                model.addAttribute("lat", lat);
                model.addAttribute("lng", lng);
            }
        } else if (city != null && countryIso != null) {
            // Process city search
            // TODO: fix this
        }
        else {
            // If they sent half-baked data, reset them to fallback defaults
            model.addAttribute("lat", 41.8781);
            model.addAttribute("lng", -87.6298);
        }

        // Pass values to Thymeleaf variables: [[${lat}]], [[${lng}]], etc.
        model.addAttribute("mapboxAccessToken", mapboxAccessToken);


        return "browse"; // Returns browse.html template
    }
}
