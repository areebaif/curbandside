package com.curbandside.app.DTO.GeoJson;

import java.util.List;
import java.util.Objects;

public class ListingFeatureCollection {

        /**
         * The type will always be FeatureCollection
         */
        private String type;

        private List<ListingFeature> features;

        public ListingFeatureCollection() {
        }

        public ListingFeatureCollection(String type, List<ListingFeature> features) {
            this.type = type;
            this.features = features;
        }

        /**
         * The type will always be FeatureCollection
         */
        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<ListingFeature> getFeatures() {
            return features;
        }

        public void setFeatures(List<ListingFeature> features) {
            this.features = features;
        }

        @Override
        public String toString() {
            return "RestaurantFeatureCollection{type='" + type + "', features='" + features + "'}";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ListingFeatureCollection that = (ListingFeatureCollection) o;
            return Objects.equals(type, that.type) &&
                    Objects.equals(features, that.features);
        }

        @Override
        public int hashCode() {
            return Objects.hash(type, features);
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public static class Builder {
            /**
             * The type will always be FeatureCollection
             */
            private String type;

            private List<ListingFeature> features;

            public ListingFeatureCollection build() {
                ListingFeatureCollection result = new ListingFeatureCollection();
                result.type = this.type;
                result.features = this.features;
                return result;
            }

            /**
             * The type will always be FeatureCollection
             */
            public Builder type(String type) {
                this.type = type;
                return this;
            }

            public Builder features(List<ListingFeature> features) {
                this.features = features;
                return this;
            }
        }


}
