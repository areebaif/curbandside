package com.curbandside.app.DTO.GeoJson;


import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListingPropertiesForGeoJson {
        private String listingId;

        private String title;

        private String condition;

        private String category;

        private String status;

        private String coverUrl;

    private String iso3Code;

    private String state;

    private String city;

        public ListingPropertiesForGeoJson() {
        }

        public ListingPropertiesForGeoJson(String listingId, String title,
                                              String condition, String category, String status,
                                              String coverUrl) {
            this.listingId = listingId;
            this.title = title;
            this.condition = condition;
            this.category = category;
            this.status = status;
            this.coverUrl = coverUrl;
        }

    public String getListingId() {
        return listingId;
    }

    public void setListingId(String listingId) {
        this.listingId = listingId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getIso3Code() {
        return iso3Code;
    }

    public void setIso3Code(String iso3Code) {
        this.iso3Code = iso3Code;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "ListingPropertiesForGeoJson{" +
                "listingId='" + listingId + '\'' +
                ", title='" + title + '\'' +
                ", condition='" + condition + '\'' +
                ", category='" + category + '\'' +
                ", status='" + status + '\'' +
                ", coverUrl='" + coverUrl + '\'' +
                '}';
    }

    @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ListingPropertiesForGeoJson that = (ListingPropertiesForGeoJson) o;
            return Objects.equals(listingId, that.listingId) &&
                    Objects.equals(title, that.title) &&
                    Objects.equals(category, that.category) &&
                    Objects.equals(condition, that.condition) &&
                    Objects.equals(status, that.status) &&
                    Objects.equals(coverUrl, that.coverUrl);
        }

        @Override
        public int hashCode() {
            return Objects.hash(listingId, title, condition, category, status,  coverUrl);
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public static class Builder {
            private String listingId;

            private String title;

            private String condition;

            private String status;

            private String category;


            private String coverUrl;

            public ListingPropertiesForGeoJson build() {
                ListingPropertiesForGeoJson result = new ListingPropertiesForGeoJson();
                result.listingId = this.listingId;
                result.title = this.title;
                result.condition = this.condition;
                result.status = this.status;
                result.category = this.category;
                result.coverUrl = this.coverUrl;
                return result;
            }

            public Builder listingId(String listingId) {
                this.listingId = listingId;
                return this;
            }

            public Builder title(String title) {
                this.title = title;
                return this;
            }



            public Builder category(String category) {
                this.category = category;
                return this;
            }

            public Builder status(String status) {
                this.status = status;
                return this;
            }


            public Builder coverUrl(String coverUrl) {
                this.coverUrl = coverUrl;
                return this;
            }
        }
    }


