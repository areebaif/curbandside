package com.curbandside.app.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Request body for creating a new listing via POST /api/listings.
 */
public class ListingRequestDto {

        @NotBlank(message = "Title is required")
        private String title;

        @NotBlank(message = "Category is required")
        private String category;

        @NotBlank(message = "Condition is required")
        private String condition;

        private String status;

        @NotNull(message = "Latitude is required")
        private Double latitude;

        @NotNull(message = "Longitude is required")
        private Double longitude;

        public ListingRequestDto() {
        }

        public String getTitle() {
                return title;
        }

        public void setTitle(String title) {
                this.title = title;
        }

        public String getCategory() {
                return category;
        }

        public void setCategory(String category) {
                this.category = category;
        }

        public String getCondition() {
                return condition;
        }

        public void setCondition(String condition) {
                this.condition = condition;
        }

        public String getStatus() {
                return status;
        }

        public void setStatus(String status) {
                this.status = status;
        }

        public Double getLatitude() {
                return latitude;
        }

        public void setLatitude(Double latitude) {
                this.latitude = latitude;
        }

        public Double getLongitude() {
                return longitude;
        }

        public void setLongitude(Double longitude) {
                this.longitude = longitude;
        }

        @Override
        public String toString() {
                return "ListingRequestDto{" +
                        "title='" + title + '\'' +
                        ", category='" + category + '\'' +
                        ", condition='" + condition + '\'' +
                        ", status='" + status + '\'' +
                        ", latitude=" + latitude +
                        ", longitude=" + longitude +
                        '}';
        }
}