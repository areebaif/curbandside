package com.curbandside.app.Repositories;

import com.curbandside.app.DTO.GeoJson.ListingFeature;
import com.curbandside.app.Entities.listing.ListingEntity;

import java.util.List;


public interface ListingRepository  {

    ListingEntity save(ListingEntity listingEntity);

    List<ListingFeature> getGeoJsonFeatureCollectionOfListingsByClientLocation(Double minLat,
                                                                                  Double maxLat,
                                                                                  Double minLng,
                                                                                  Double maxLng,
                                                                                  Double userLat,
                                                                                  Double userLng,
                                                                                  Integer distanceInMiles);



}
