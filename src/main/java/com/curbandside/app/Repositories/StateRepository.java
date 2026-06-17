package com.curbandside.app.Repositories;

import com.curbandside.app.Entities.StateEntity;

import java.util.Optional;

public interface StateRepository {

    Optional<StateEntity> getEntityByStateAbbreviationAndCountryId(String abbreviation, Long countryId);
}
