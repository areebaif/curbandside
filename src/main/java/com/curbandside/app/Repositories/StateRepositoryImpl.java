package com.curbandside.app.Repositories;

import com.curbandside.app.Entities.StateEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public class StateRepositoryImpl implements StateRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<StateEntity> getEntityByStateAbbreviationAndCountryId(String abbreviation, Long countryId) {
        try {
            Query query = entityManager.createQuery("select s from StateEntity s where s.stateAbbreviation = :abbreviation and s.country.id = :countryId")
                    .setParameter("abbreviation", abbreviation)
                    .setParameter("countryId", countryId);
            StateEntity result = (StateEntity) query.getSingleResult();
            return Optional.ofNullable(result);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
