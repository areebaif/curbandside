package com.curbandside.app.Repositories;

import com.curbandside.app.DTO.GeoJson.LatitudeLongitudeListDto;
import com.curbandside.app.transformers.LatitudeLongitudeListTransformer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class ZipcodeRepositoryImpl implements ZipcodeRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings({"unchecked"})
    @Override
    public LatitudeLongitudeListDto getLatitudeLongitudeListForRestaurantsByCityName(String cityName, String stateAbbreviation, String countryIso) {
        LatitudeLongitudeListTransformer transformer = new LatitudeLongitudeListTransformer();
        Query query = entityManager
                .createNativeQuery(
                        "select latitude, longitude from zipcode " +
                                "join city on city.city_id = zipcode.city_id " +
                                "join state on state.state_id = zipcode.state_id " +
                                "join country on country.country_id=zipcode.country_id " +
                                "where city_name=:cityName and state_abbreviation=:stateAbbreviation and country.iso3=:countryIso")
                .setParameter("cityName", cityName)
                .setParameter("stateAbbreviation", stateAbbreviation)
                .setParameter("countryIso", countryIso)
                .unwrap(org.hibernate.query.NativeQuery.class)
                .setTupleTransformer(transformer)
                .setResultListTransformer(transformer);
        return (LatitudeLongitudeListDto) query.getSingleResult();

    }
}
