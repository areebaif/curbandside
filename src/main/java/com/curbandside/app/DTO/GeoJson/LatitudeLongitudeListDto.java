package com.curbandside.app.DTO.GeoJson;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class LatitudeLongitudeListDto {

    private List<BigDecimal> latitude = new ArrayList<>();

    private List<BigDecimal> longitude = new ArrayList<>();

    public List<BigDecimal> getLatitude() {
        return latitude;
    }

    public void setLatitude(List<BigDecimal> latitude) {
        this.latitude = latitude;
    }

    public List<BigDecimal> getLongitude() {
        return longitude;
    }

    public void setLongitude(List<BigDecimal> longitude) {
        this.longitude = longitude;
    }

    public void addLatitude(BigDecimal latitude) {
        this.latitude.add(latitude);
    }

    public void addlongitude(BigDecimal longitude) {
        this.longitude.add(longitude);
    }

}
