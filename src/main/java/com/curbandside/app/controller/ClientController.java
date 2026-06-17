package com.curbandside.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ClientController {

    @Value("${mapbox.access-token}")
    private String mapboxAccessToken;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/browse")
    public String browsePage(
            @RequestParam(value = "lat", required = true) Double lat,
            @RequestParam(value = "lng", required = true) Double lng,
            Model model) {
        // Pass values to Thymeleaf variables: [[${lat}]], [[${lng}]], etc.
        model.addAttribute("lat", lat);
        model.addAttribute("lng", lng);
        model.addAttribute("mapboxAccessToken", mapboxAccessToken);

        return "browse"; // Returns browse.html template
    }
}
