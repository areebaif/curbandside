package com.curbandside.app.transformers;

import com.curbandside.app.DTO.GeoJson.ListingFeature;
import com.curbandside.app.DTO.GeoJson.ListingPropertiesForGeoJson;
import com.curbandside.app.DTO.GeoJson.PointGeometry;
import com.curbandside.app.utils.GeoJsonEnums;
import org.hibernate.query.ResultListTransformer;
import org.hibernate.query.TupleTransformer;

import java.math.BigDecimal;
import java.util.*;

public class GeoJsonFeatureForListingTransformer implements TupleTransformer<ListingFeature>, ResultListTransformer<ListingFeature> {

    final Map<String, ListingFeature> listingDtoMap = new HashMap<>();


    @Override
    public ListingFeature transformTuple(Object[] row, String[] strings) {
        String restaurantId = String.valueOf((Long) row[0]);

        ListingFeature listingFeatureGeoJsonDto = listingDtoMap.get(restaurantId);

        if (listingFeatureGeoJsonDto == null) {
            listingFeatureGeoJsonDto = new ListingFeature();
            PointGeometry listingGeoJsonGeometryDto = new PointGeometry();
            // Coordinates is a tuple with [longitude, latitude]
            List<Double> coordinates = List.of(((BigDecimal) row[6]).doubleValue(), ((BigDecimal) row[5]).doubleValue());
            listingGeoJsonGeometryDto.setCoordinates(coordinates);

            listingGeoJsonGeometryDto.setType(GeoJsonEnums.Point.toString());
            listingFeatureGeoJsonDto.setGeometry(listingGeoJsonGeometryDto);
            listingFeatureGeoJsonDto.setType(GeoJsonEnums.Feature.toString());
            listingFeatureGeoJsonDto.setId(restaurantId);

        }

        if (listingFeatureGeoJsonDto.getProperties() == null) {
            ListingPropertiesForGeoJson listingGeoJsonPropertiesDto = new ListingPropertiesForGeoJson();
            listingGeoJsonPropertiesDto.setListingId(restaurantId);
            listingGeoJsonPropertiesDto.setTitle((String) row[1]);
            listingGeoJsonPropertiesDto.setCategory((String) row[2]);
            listingGeoJsonPropertiesDto.setCondition((String) row[3]);
            listingGeoJsonPropertiesDto.setStatus((String) row[4]);
            if (row[7] instanceof String isoCode) {
                listingGeoJsonPropertiesDto.setIso3Code(isoCode);
            }

            if (row[8] instanceof String state) {
                listingGeoJsonPropertiesDto.setState(state);
            }

            if (row[9] instanceof String city) {
                listingGeoJsonPropertiesDto.setCity(city);
            }



            listingFeatureGeoJsonDto.setProperties(listingGeoJsonPropertiesDto);

        }

        listingDtoMap.putIfAbsent(restaurantId, listingFeatureGeoJsonDto);
        return listingFeatureGeoJsonDto;
    }

    @Override
    public List<ListingFeature> transformList(List list) {
        return new ArrayList<>(listingDtoMap.values());
    }
}
