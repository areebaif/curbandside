package com.curbandside.app.Repositories;

import com.curbandside.app.DTO.GeoJson.ListingFeature;
import com.curbandside.app.Entities.listing.ListingEntity;
import com.curbandside.app.transformers.GeoJsonFeatureForListingTransformer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public class ListingRepositoryImpl implements ListingRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Modifying
    @Override
    public ListingEntity save(ListingEntity listingEntity) {
        entityManager.persist(listingEntity);
        return listingEntity;
    }

    @SuppressWarnings({"unchecked"})
    @Override
    public List<ListingFeature> getGeoJsonFeatureCollectionOfListingsByClientLocation(Double minLat,
                                                                                      Double maxLat,
                                                                                      Double minLng,
                                                                                      Double maxLng,
                                                                                      Double userLat,
                                                                                      Double userLng,
                                                                                      Integer distanceInMiles) {
        GeoJsonFeatureForListingTransformer geoJsonFeatureForRestaurantTransformer = new GeoJsonFeatureForListingTransformer();
        Query query = entityManager
                .createNativeQuery(
                        "select l.listing_id, l.title, l.category, l.`condition`, l.status, l.latitude, l.longitude from listings l " +
                                "where l.longitude between :minLng and :maxLng and l.latitude between :minLat and :maxLat")
                .setParameter("minLng", minLng)
                .setParameter("maxLng", maxLng)
                .setParameter("minLat", minLat)
                .setParameter("maxLat", maxLat)
                .unwrap(org.hibernate.query.NativeQuery.class)
                .setTupleTransformer(geoJsonFeatureForRestaurantTransformer)
                .setResultListTransformer(geoJsonFeatureForRestaurantTransformer);
        return query.getResultList();

    }

}
