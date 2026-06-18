package com.curbandside.app.Repositories;

import com.curbandside.app.Entities.CityEntity;

import java.util.List;
import java.util.Optional;

public interface CityRepository {

    List<String> getCities(String search);

    Optional<CityEntity> getEntityByNameStateIdAndCountryId(String name, Long stateId, Long countryId);
}
