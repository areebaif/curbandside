package com.curbandside.app.services;

import com.curbandside.app.Repositories.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    private final CityRepository cityRepository;

    public LocationService(CityRepository cityRepositoryImpl) {
        this.cityRepository = cityRepositoryImpl;
    }

    public List<String> getCities(String query) {
        return cityRepository.getCities(query);
    }
}
