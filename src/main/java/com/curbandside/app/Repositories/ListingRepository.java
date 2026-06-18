package com.curbandside.app.Repositories;

import com.curbandside.app.DTO.GeoJson.ListingFeature;
import com.curbandside.app.Entities.listing.ListingEntity;

import java.util.List;
import java.util.Optional;


public interface ListingRepository  {

    ListingEntity save(ListingEntity listingEntity);

    List<ListingFeature> getGeoJsonFeatureCollectionOfListingsByClientLocation(Double minLat,
                                                                                  Double maxLat,
                                                                                  Double minLng,
                                                                                  Double maxLng,
                                                                                  Double userLat,
                                                                                  Double userLng,
                                                                                  Integer distanceInMiles);

    List<ListingFeature> getGeoJsonFeatureCollectionOfListingsByCity(String cityName,
                                                                           String stateAbbreviation,
                                                                           String countryIso);

    List<ListingFeature> getGeoJsonFeatureCollectionOfRestaurantsWithinInCoordinateBoundingBox(Double minLng,
                                                                                                  Double maxLng,
                                                                                                  Double minLat,
                                                                                                  Double maxLat);

    Optional<ListingEntity> getListingEntityById(Long id);

    void claimListing(ListingEntity listingEntity);



}
