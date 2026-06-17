package com.curbandside.app.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class ListingRequestDto {

        @NotBlank(message = "Title is required")
        private String title;

        @NotBlank(message = "Category is required")
        private String category;

        @NotBlank(message = "Condition is required")
        private String condition;

        private String status;

        @NotNull(message = "Latitude is required")
        private BigDecimal latitude;

        @NotNull(message = "Longitude is required")
        private BigDecimal longitude;

        @NotBlank(message = "City and State is required")
        private String cityState;

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

        public @NotNull(message = "Latitude is required") BigDecimal getLatitude() {
                return latitude;
        }

        public void setLatitude(@NotNull(message = "Latitude is required") BigDecimal latitude) {
                this.latitude = latitude;
        }

        public @NotNull(message = "Longitude is required") BigDecimal getLongitude() {
                return longitude;
        }

        public void setLongitude(@NotNull(message = "Longitude is required") BigDecimal longitude) {
                this.longitude = longitude;
        }

        public @NotBlank(message = "City and State is required") String getCityState() {
                return cityState;
        }

        public void setCityState(@NotBlank(message = "City and State is required") String cityState) {
                this.cityState = cityState;
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
                        ", cityStateAbbreviation='" + cityState + '\'' +
                        '}';
        }
}