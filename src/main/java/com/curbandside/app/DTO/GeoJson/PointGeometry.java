package com.curbandside.app.DTO.GeoJson;

import java.util.List;
import java.util.Objects;

public class PointGeometry {
    private String type;

    private List<Double> coordinates;

    public PointGeometry() {
    }

    public PointGeometry(String type, List<Double> coordinates) {
        this.type = type;
        this.coordinates = coordinates;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Double> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Double> coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return "PointGeometry{type='" + type + "', coordinates='" + coordinates + "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointGeometry that = (PointGeometry) o;
        return Objects.equals(type, that.type) &&
                Objects.equals(coordinates, that.coordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, coordinates);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private String type;

        private List<Double> coordinates;

        public PointGeometry build() {
            PointGeometry result = new PointGeometry();
            result.type = this.type;
            result.coordinates = this.coordinates;
            return result;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder coordinates(List<Double> coordinates) {
            this.coordinates = coordinates;
            return this;
        }
    }
}
