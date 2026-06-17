package com.curbandside.app.controller;

import com.curbandside.app.DTO.GeoJson.ListingFeatureCollection;
import com.curbandside.app.DTO.ListingRequestDto;
import com.curbandside.app.Entities.listing.ListingEntity;
import com.curbandside.app.services.ListingService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/listings")
public class ListingApiController {

    private final ListingService listingService;

    public ListingApiController(ListingService listingService) {
        this.listingService = listingService;
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ListingRequestDto request) {

        ListingEntity entity =  listingService.saveListing(request);
        return ResponseEntity.ok("Successfully created listing with id " + entity.getId());
    }

    @GetMapping("/nearby")
    public ListingFeatureCollection nearby(
            @RequestParam double lat,
            @RequestParam double lng) {
        Integer distanceInMiles = 30;
        ListingFeatureCollection listings = listingService.getGeoJsonFeatureCollectionOfListingsByClientLocation(lat, lng, distanceInMiles);
       return listings;
    }

    /**
     * Searches listings by city/text query and returns matching listings
     * as a GeoJSON FeatureCollection.
     */
    @GetMapping("/search")
    public ListingFeatureCollection search(@RequestParam(name = "q", required = true) String query) {


        Integer distanceInMiles = 30;
        ListingFeatureCollection listings = listingService.getGeoJsonFeatureCollectionOfListingsByCity(query, distanceInMiles);
        return listings;

    }


}
