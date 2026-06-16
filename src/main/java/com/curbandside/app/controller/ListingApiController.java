package com.curbandside.app.controller;

import com.curbandside.app.DTO.GeoJson.ListingFeatureCollection;
import com.curbandside.app.DTO.ListingRequestDto;
import com.curbandside.app.Entities.listing.ListingEntity;
import com.curbandside.app.services.ListingService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/listings")
public class ListingApiController {

    private final ListingService listingService;

    public ListingApiController(ListingService listingService) {
        this.listingService = listingService;
    }

    @PostMapping
    public ResponseEntity<ListingEntity> create(@Valid @RequestBody ListingRequestDto request) {

        System.out.println(request);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        ListingEntity entity =  listingService.saveListing(request);
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/nearby")
    public ListingFeatureCollection nearby(
            @RequestParam double lat,
            @RequestParam double lng) {
        System.out.println(lat );
        System.out.println( lng);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        Integer distanceInMiles = 30;

        ListingFeatureCollection listings = listingService.getGeoJsonFeatureCollectionOfListingsByClientLocation(lat, lng, distanceInMiles);
        System.out.println(lat );
        System.out.println( lng);
        System.out.println(listings);

       return listings;
    }


}
