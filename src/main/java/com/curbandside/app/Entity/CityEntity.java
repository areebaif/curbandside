package com.curbandside.app.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "city")
public class CityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id", nullable = false, updatable = false, unique = true)
    private Long id;

    @Column(name = "city_name", nullable = false)
    private String name;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id")
    private StateEntity state;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,
            mappedBy = "city", orphanRemoval = true)
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

    public CountryEntity getCountry() {
        return country;
    }

    public void setCountry(CountryEntity country) {
        this.country = country;
    }

    public Optional<StateEntity> getState() {
        return Optional.ofNullable(state);
    }

    public void setState(StateEntity state) {
        this.state = state;
    }


    public List<ZipcodeEntity> getZipcodes() {
        return zipcodes;
    }

    public void setZipcodes(List<ZipcodeEntity> zipcodes) {
        this.zipcodes = zipcodes;
    }

    public void addZipcode(ZipcodeEntity zipcode) {
        this.zipcodes.add(zipcode);
        zipcode.setCity(this);
    }

    public void removeZipcode(ZipcodeEntity zipcode) {
        zipcode.setCity(null);
        this.zipcodes.remove(zipcode);
    }

    public void removeZipcodes() {
        Iterator<ZipcodeEntity> iterator = this.zipcodes.iterator();
        while (iterator.hasNext()) {
            ZipcodeEntity zipcode = iterator.next();
            zipcode.setCity(null);
            iterator.remove();
        }
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {

        private String name;
        private CountryEntity country;
        private StateEntity state;
        private List<ZipcodeEntity> zipcodes;

        public Builder() {
        }

        public CityEntity build() {
            CityEntity result = new CityEntity();
            result.name = this.name;
            result.country = this.country;
            result.state = this.state;
            result.zipcodes = this.zipcodes;
            return result;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder country(CountryEntity country) {
            this.country = country;
            return this;
        }

        public Builder state(StateEntity state) {
            this.state = state;
            return this;
        }

        public Builder zipcodes(List<ZipcodeEntity> zipcodes) {
            this.zipcodes = zipcodes;
            return this;
        }


    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CityEntity city = (CityEntity) obj;
        return this.id != null && this.id.equals(city.id);
    }

    @Override
    public int hashCode() {
        return 2021;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
