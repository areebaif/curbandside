package com.curbandside.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
@Controller
public class HomeController {

    @Value("${mapbox.access-token}")
    private String mapboxAccessToken;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/browse")
    public String browse(Model model) {
        model.addAttribute("mapboxAccessToken", mapboxAccessToken);
        return "browse";
    }
}
