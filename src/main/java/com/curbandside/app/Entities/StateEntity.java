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
@Table(name = "state")
public class StateEntity {
    // state
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "state_id", nullable = false, updatable = false, unique = true)
    private Long id;

    @Column(name = "state_name", nullable = false)
    private String name;

    @Column(name = "state_abbreviation")
    private String stateAbbreviation;

    @Version
    @Column(name = "version")
    private Integer version;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private CountryEntity country;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "state", orphanRemoval = true)
    private List<CityEntity> cities = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "state", orphanRemoval = true)
    private List<ListingEntity> listings = new ArrayList<>();

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

    public CountryEntity getCountry() {
        return country;
    }

    public void setCountry(CountryEntity country) {
        this.country = country;
    }

    public List<CityEntity> getCities() {
        return cities;
    }

    public void setCities(List<CityEntity> cities) {
        this.cities = cities;
    }

    public void addCity(CityEntity city) {
        this.cities.add(city);
        city.setState(this);
    }

    public void removeCity(CityEntity city) {
        city.setState(null);
        this.cities.remove(city);
    }

    public void removeCities() {
        Iterator<CityEntity> iterator = this.cities.iterator();
        while (iterator.hasNext()) {
            CityEntity city = iterator.next();
            city.setState(null);
            iterator.remove();
        }
    }

    public String getStateAbbreviation() {
        return stateAbbreviation;
    }

    public void setStateAbbreviation(String stateAbbreviation) {
        this.stateAbbreviation = stateAbbreviation;
    }

    public List<ListingEntity> getListings() {
        return listings;
    }

    public void setListings(List<ListingEntity> listings) {
        this.listings = listings;
    }

    public void addListing(ListingEntity listing) {
        this.listings.add(listing);
        listing.setState(this);
    }

    public void removeListing(ListingEntity listing) {
        listing.setState(null);
        this.listings.remove(listing);
    }

    public void removeListings() {
        Iterator<ListingEntity> iterator = this.listings.iterator();
        while (iterator.hasNext()) {
            ListingEntity listing = iterator.next();
            listing.setState(null);
            iterator.remove();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StateEntity state = (StateEntity) o;
        return this.id != null && this.id.equals(state.id);
    }

    @Override
    public int hashCode() {
        return 2021;
    }

    @Override
    public String toString() {
        return "State{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
