package com.curbandside.app.Repositories;


import com.curbandside.app.Entities.CountryEntity;
import jakarta.persistence.EntityManager;

import jakarta.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public class CountryRepositoryImpl implements CountryRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Optional<CountryEntity> findEntityByIso(String iso) {
        try {
            CountryEntity result = entityManager.createQuery("select c from CountryEntity c where c.iso= :iso", CountryEntity.class)
                    .setParameter("iso", iso)
                    .getSingleResult();
            
            return Optional.ofNullable(result);

        } catch (Exception e) {
            return Optional.empty();
        }

    }


}
