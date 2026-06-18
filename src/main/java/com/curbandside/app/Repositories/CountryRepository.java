package com.curbandside.app.Repositories;


import com.curbandside.app.Entities.CountryEntity;

import java.util.Optional;


public interface CountryRepository {


    Optional<CountryEntity> findEntityByIso(String iso);


}
