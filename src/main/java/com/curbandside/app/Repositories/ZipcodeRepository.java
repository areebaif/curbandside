package com.curbandside.app.Repositories;

import com.curbandside.app.DTO.GeoJson.LatitudeLongitudeListDto;

public interface ZipcodeRepository {

    LatitudeLongitudeListDto getLatitudeLongitudeListForRestaurantsByCityName(String cityName, String stateAbbreviation, String countryIso);
}
