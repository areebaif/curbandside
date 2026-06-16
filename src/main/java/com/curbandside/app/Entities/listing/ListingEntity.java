package com.curbandside.app.Entities.listing;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "listings")
public class ListingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "listing_id", nullable = false, updatable = false, unique = true)
    private Long id;

    @Column(name = "title")
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(length = 50, name = "category")
    private ListingCategory listingCategory;

    @Enumerated(EnumType.STRING)
    @Column(length = 50, name = "\"condition\"")
    private ListingCondition listingCondition;

    @Enumerated(EnumType.STRING)
    @Column(length = 30, name = "status")
    private ListingStatus listingStatus;

    @Column(name = "latitude", nullable = false)
    private BigDecimal latitude;

    @Column(name = "longitude", nullable = false)
    private BigDecimal longitude;

    @Version
    @Column(name = "version")
    private Integer version;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ListingCategory getListingCategory() {
        return listingCategory;
    }

    public void setListingCategory(ListingCategory listingCategory) {
        this.listingCategory = listingCategory;
    }

    public ListingCondition getListingCondition() {
        return listingCondition;
    }

    public void setListingCondition(ListingCondition listingCondition) {
        this.listingCondition = listingCondition;
    }

    public ListingStatus getListingStatus() {
        return listingStatus;
    }

    public void setListingStatus(ListingStatus listingStatus) {
        this.listingStatus = listingStatus;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ListingEntity listing = (ListingEntity) obj;
        return this.id != null && this.id.equals(listing.id);
    }

    @Override
    public int hashCode() {
        return 2021;
    }

    @Override
    public String toString() {
        return "ListingEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", listingCategory=" + listingCategory +
                ", listingCondition=" + listingCondition +
                ", listingStatus=" + listingStatus +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
