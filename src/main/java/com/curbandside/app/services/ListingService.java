package com.curbandside.app.services;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import com.curbandside.app.DTO.GeoJson.ListingFeature;
import com.curbandside.app.DTO.GeoJson.ListingFeatureCollection;
import com.curbandside.app.DTO.GeoJson.PointGeometry;
import com.curbandside.app.DTO.ListingRequestDto;
import com.curbandside.app.Entities.listing.ListingCategory;
import com.curbandside.app.Entities.listing.ListingCondition;
import com.curbandside.app.Entities.listing.ListingEntity;
import com.curbandside.app.Entities.listing.ListingStatus;
import com.curbandside.app.Repositories.ListingRepository;
import com.curbandside.app.utils.BoundingBox;
import com.curbandside.app.utils.DistanceBwTwoCoordinates;
import com.curbandside.app.utils.GeoJsonEnums;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ListingService {

    private final ListingRepository listingRepository;

    public ListingService(ListingRepository listingRepositoryImpl) {
        this.listingRepository = listingRepositoryImpl;
    }

    @Transactional
    @Modifying
    public ListingEntity saveListing(ListingRequestDto listingRequestDto) {
        ListingEntity listing = new ListingEntity();
        listing.setTitle(listingRequestDto.getTitle());
        listing.setListingCondition(this.convertListingCondition(listingRequestDto.getCondition()));
        listing.setListingCategory(this.convertListingCategory(listingRequestDto.getCategory()));
        listing.setListingStatus(this.convertListingStatus(listingRequestDto.getStatus()));
        listing.setLatitude(listingRequestDto.getLatitude());
        listing.setLongitude(listingRequestDto.getLongitude());

        return  listingRepository.save(listing);

    }

    public ListingFeatureCollection getGeoJsonFeatureCollectionOfListingsByClientLocation(Double latitude, Double longitude, Integer distanceInMiles) {

        List<Double> coordinates = BoundingBox.boundingBoxCalculation(latitude, longitude, distanceInMiles);
        Double minLat = coordinates.getFirst();
        Double maxLat = coordinates.get(2);
        Double minLng = coordinates.get(1);
        Double maxLng = coordinates.get(3);


        List<ListingFeature> features = listingRepository.getGeoJsonFeatureCollectionOfListingsByClientLocation(
                minLat,
                maxLat,
                minLng,
                maxLng,
                latitude,
                longitude,
                distanceInMiles);
        // custom sort by distance from user
        features.sort((a, b) -> {
            PointGeometry restaurantGeoJsonGeometryDtoA = a.getGeometry();
            PointGeometry restaurantGeoJsonGeometryDtoB = b.getGeometry();
            // calculate distance from user
            double distanceA = DistanceBwTwoCoordinates.distanceBetweenTwoCoords(latitude,
                    longitude, restaurantGeoJsonGeometryDtoA.getCoordinates().getLast(),
                    restaurantGeoJsonGeometryDtoA.getCoordinates().getFirst());
            double distanceB = DistanceBwTwoCoordinates.distanceBetweenTwoCoords(latitude,
                    longitude, restaurantGeoJsonGeometryDtoB.getCoordinates().getLast(),
                    restaurantGeoJsonGeometryDtoB.getCoordinates().getFirst());
            return Double.compare(distanceA, distanceB);
        });


        return ListingFeatureCollection
                .newBuilder()
                .type(GeoJsonEnums.FeatureCollection.toString())
                .features(features).build();
    }

    private ListingStatus convertListingStatus(String listingStatus) {
        System.out.println(listingStatus);
        ListingStatus listingStatusEnum = null;
        return switch (listingStatus) {
            case "active" -> listingStatusEnum = ListingStatus.STATUS_ACTIVE;
            case "claimed" -> listingStatusEnum = ListingStatus.STATUS_CLAIMED;
            default -> throw new IllegalStateException("Unexpected value: " + listingStatus);
        };
    }

    private ListingCategory convertListingCategory(String listingCategory) {
        ListingCategory listingCategoryEnum = null;

        return switch (listingCategory) {
            case "sofa" -> listingCategoryEnum = ListingCategory.TYPE_SOFA;
            case "table" -> listingCategoryEnum = ListingCategory.TYPE_TABLE;
            case "chair" -> listingCategoryEnum = ListingCategory.TYPE_CHAIR;
            case "bed" -> listingCategoryEnum = ListingCategory.TYPE_BED;
            case "dresser" -> listingCategoryEnum = ListingCategory.TYPE_DRESSER;
            case "desk" -> listingCategoryEnum = ListingCategory.TYPE_DESK;
            case "shelving" -> listingCategoryEnum = ListingCategory.TYPE_SHELVING;
            case "appliance" -> listingCategoryEnum = ListingCategory.TYPE_APPLIANCE;
            default -> throw new IllegalStateException("Unexpected value: " + listingCategory);
        };
    }

    private ListingCondition convertListingCondition(String listingCondition) {
        ListingCondition listingCategoryEnum = null;
        return switch (listingCondition) {
            case "Great — barely used" -> listingCategoryEnum = ListingCondition.CONDITION_GREAT_BARELY_USED;
            case "Good — normal wear" -> listingCategoryEnum = ListingCondition.CONDITION_GOOD_NORMAL_WEAR;
            case "Fair — visible wear" -> listingCategoryEnum = ListingCondition.CONDITION_FAIR_VISIBLE_WEAR;
            case "Rough — take it or leave it" -> listingCategoryEnum = ListingCondition.CONDITION_ROUGH_TAKE_IT_OR_LEAVE_IT;
            default -> throw new IllegalStateException("Unexpected value: " + listingCondition);
        };
    }

    private String convertSvgToDataUrl(String rawSvgContent) {
        // Encode the raw XML string to Base64
        String base64Encoded = Base64.getEncoder()
                .encodeToString(rawSvgContent.getBytes(StandardCharsets.UTF_8));

        // Return the formatted Data URL header paired with the encoded body
        return "data:image/svg+xml;base64," + base64Encoded;
    }

//    private String addSvgImageToFeature(ListingFeature feature) {
//
//    }
}
