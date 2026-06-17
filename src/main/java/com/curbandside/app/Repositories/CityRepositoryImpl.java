package com.curbandside.app.Repositories;

import com.curbandside.app.Entities.CityEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public class CityRepositoryImpl implements CityRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings({"unchecked"})
    @Override
    @Transactional(readOnly = true)
    public List<String> getCities(String cityNamePrefix) {
        Query query = entityManager
                .createNativeQuery(
                        "select c.city_name, s.state_abbreviation from city c join state s on s.state_id = c.state_id " +
                                "  where c.city_name like :cityNamePrefix")
                .setParameter("cityNamePrefix", cityNamePrefix + "%")
                .unwrap(org.hibernate.query.NativeQuery.class)
                .setTupleTransformer((tuple, aliases) -> {
                    String cityName = (String) tuple[0];
                    String stateAbbreviation = (String) tuple[1];
                    return cityName + ", " + stateAbbreviation;

                });
        return query.getResultList();

    }

    @Override
    public Optional<CityEntity> getEntityByNameStateIdAndCountryId(String name, Long stateId, Long countryId) {
        try {
            Query query = entityManager.createQuery("Select c from CityEntity  c where c.name = :name and c.state.id = :stateId and c.country.id = :countryId")
                    .setParameter("name", name)
                    .setParameter("stateId", stateId)
                    .setParameter("countryId", countryId);
            CityEntity cityEntity = (CityEntity) query.getSingleResult();
            return Optional.of(cityEntity);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

}
