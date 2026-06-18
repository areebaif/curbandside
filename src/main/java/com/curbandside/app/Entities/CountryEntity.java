package com.curbandside.app.Entities;

import com.curbandside.app.Entities.listing.ListingEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Entity
@Table(name = "country")
public class CountryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id", nullable = false, updatable = false, unique = true)
    private Long id;

    @Column(name = "country_name", nullable = false)
    private String name;

    @Column(name = "iso3", nullable = false, length = 3, columnDefinition = "CHAR(3)")
    private String iso;

    @Version
    @Column(name = "version")
    private Integer version;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "country", orphanRemoval = true)
    private List<ListingEntity> listings = new ArrayList<>();

    // constructors
    public CountryEntity() {
    }

    // Note that for example a client sends a state object and then we merge it in hibernate session or jpa entity manager than
    // the object equality is used to determine whether the object should be added to this list.
    // This is discussed in High persistence java book michael whatever pg 192 equality based entity removal.
    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "country", orphanRemoval = true)
    private List<StateEntity> states = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "country", orphanRemoval = true)
    private List<CityEntity> cities = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "country", orphanRemoval = true)
    private List<ZipcodeEntity> zipcodes = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public Integer getVersion() {
        return version;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public List<StateEntity> getStates() {
        return states;
    }

    public void setStates(List<StateEntity> states) {
        this.states = states;
    }

    public void addState(StateEntity state) {
        this.states.add(state);
        state.setCountry(this);
    }

    public void removeState(StateEntity state) {
        state.setCountry(null);
        this.states.remove(state);
    }

    public void removeStates() {
        Iterator<StateEntity> iterator = this.states.iterator();
        while (iterator.hasNext()) {
            StateEntity state = iterator.next();
            state.setCountry(null);
            iterator.remove();
        }
    }

    public List<CityEntity> getCities() {
        return cities;
    }

    public void setCities(List<CityEntity> cities) {
        this.cities = cities;
    }

    public void addCity(CityEntity city) {
        this.cities.add(city);
        city.setCountry(this);
    }

    public void removeCity(CityEntity city) {
        city.setCountry(null);
        this.cities.remove(city);
    }

    public void removeCities() {
        Iterator<CityEntity> iterator = this.cities.iterator();
        while (iterator.hasNext()) {
            CityEntity city = iterator.next();
            city.setCountry(null);
            iterator.remove();
        }
    }

    public List<ZipcodeEntity> getZipcodes() {
        return zipcodes;
    }

    public void setZipcodes(List<ZipcodeEntity> zipcodes) {
        this.zipcodes = zipcodes;
    }

    public void addZipcode(ZipcodeEntity zipcode) {
        this.zipcodes.add(zipcode);
        zipcode.setCountry(this);
    }

    public void removeZipcode(ZipcodeEntity zipcode) {
        zipcode.setCountry(null);
        this.zipcodes.remove(zipcode);
    }

    public void removeZipcodes() {
        Iterator<ZipcodeEntity> iterator = this.zipcodes.iterator();
        while (iterator.hasNext()) {
            ZipcodeEntity zipcode = iterator.next();

            zipcode.setCountry(null);
            iterator.remove();
        }
    }

    public List<ListingEntity> getListings() {
        return listings;
    }

    public void setListings(List<ListingEntity> listings) {
        this.listings = listings;
    }

    public void addListing(ListingEntity listing) {
        this.listings.add(listing);
        listing.setCountry(this);
    }

    public void removeListing(ListingEntity listing) {
        listing.setCountry(null);
        this.listings.remove(listing);
    }

    public void removeListings() {
        Iterator<ListingEntity> iterator = this.listings.iterator();
        while (iterator.hasNext()) {
            ListingEntity listing = iterator.next();
            listing.setCountry(null);
            iterator.remove();
        }
    }

    @Override
    public String toString() {
        return "Country{" +
                "countryId=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    // Two references referring to the same object on the heap are equal. Period
    // Hence, this means that two objects with null id are only considered equal if they are references of same object. This is achievable because hashCode() returns a constant;
    // therefore for null ids we rely on Object reference equality.
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CountryEntity country = (CountryEntity) obj;
        return this.id != null && this.id.equals(country.id);
    }

    @Override
    public int hashCode() {
        return 2021;
    }

}
