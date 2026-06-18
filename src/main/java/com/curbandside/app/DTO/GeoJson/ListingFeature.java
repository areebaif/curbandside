package com.curbandside.app.DTO.GeoJson;

import java.util.Objects;

public class ListingFeature {
    private String type;

    private String id;

    private PointGeometry geometry;

    private ListingPropertiesForGeoJson properties;

    public ListingFeature() {
    }

    public ListingFeature(String type, String id, PointGeometry geometry,
                          ListingPropertiesForGeoJson properties) {
        this.type = type;
        this.id = id;
        this.geometry = geometry;
        this.properties = properties;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PointGeometry getGeometry() {
        return geometry;
    }

    public void setGeometry(PointGeometry geometry) {
        this.geometry = geometry;
    }

    public ListingPropertiesForGeoJson getProperties() {
        return properties;
    }

    public void setProperties(ListingPropertiesForGeoJson properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "ListingFeature{" +
                "type='" + type + '\'' +
                ", id='" + id + '\'' +
                ", geometry=" + geometry +
                ", properties=" + properties +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListingFeature that = (ListingFeature) o;
        return Objects.equals(type, that.type) &&
                Objects.equals(id, that.id) &&
                Objects.equals(geometry, that.geometry) &&
                Objects.equals(properties, that.properties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, id, geometry, properties);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private String type;

        private String id;

        private PointGeometry geometry;

        private ListingPropertiesForGeoJson properties;

        public ListingFeature build() {
            ListingFeature result = new ListingFeature();
            result.type = this.type;
            result.id = this.id;
            result.geometry = this.geometry;
            result.properties = this.properties;
            return result;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder geometry(PointGeometry geometry) {
            this.geometry = geometry;
            return this;
        }

        public Builder properties(ListingPropertiesForGeoJson properties) {
            this.properties = properties;
            return this;
        }
    }
}
