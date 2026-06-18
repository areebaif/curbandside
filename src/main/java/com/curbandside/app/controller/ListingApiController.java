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
    public ResponseEntity<String> create(@Valid @RequestBody ListingRequestDto request) {

        ListingEntity entity =  listingService.saveListing(request);
        return ResponseEntity.ok("Successfully created listing with id " + entity.getId());
    }

    @GetMapping("/nearby")
    public ResponseEntity<ListingFeatureCollection> nearby(
            @RequestParam double lat,
            @RequestParam double lng) {
        Integer distanceInMiles = 50;
        ListingFeatureCollection listings = listingService.getGeoJsonFeatureCollectionOfListingsByClientLocation(lat, lng, distanceInMiles);
       return ResponseEntity.ok(listings);
    }

    /**
     * Searches listings by city/text query and returns matching listings
     * as a GeoJSON FeatureCollection.
     */
    @GetMapping("/search")
    public ResponseEntity<ListingFeatureCollection> search(@RequestParam(name = "q", required = true) String query) {
        Integer distanceInMiles = 50;
        ListingFeatureCollection listings = listingService.getGeoJsonFeatureCollectionOfListingsByCity(query, distanceInMiles);
        return ResponseEntity.ok(listings);

    }

    @PostMapping("/{id}/claim")
    public ResponseEntity<Void> claim(@PathVariable("id") Long id) {

        listingService.claimListing(id);

        // TODO: look up the listing by id and update its status to STATUS_CLAIMED here

        return ResponseEntity.ok().build();
    }


}
