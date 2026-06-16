package com.curbandside.app.controller;

import com.curbandside.app.DTO.ListingRequestDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/listings")
public class ListingApiController {

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ListingRequestDto request) {

        System.out.println(request);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

//        Listing listing = new Listing();
//        listing.setTitle(request.getTitle());
//        listing.setCondition(request.getCondition());
//        listing.setCategory(request.getCategory());
//        listing.setStatus(request.getStatus() != null ? request.getStatus() : "active");
//        listing.setLatitude(request.getLatitude());
//        listing.setLongitude(request.getLongitude());
//        listing.setClaimed(false);
        //TODO: fix this
        //Listing saved = listingRepository.save(listing);
        return ResponseEntity.ok("success");
    }


}
