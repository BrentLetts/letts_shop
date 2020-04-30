package com.example.letts_shop.models.geocode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GeocodeGeometry {

    @JsonProperty("location")
    private GeocodeGeometry geoLocation;

    public GeocodeGeometry() { }

    public GeocodeGeometry getGeoLocation() {
        return geoLocation;
    }

    public void setGeoLocation(GeocodeGeometry geoLocation) {
        this.geoLocation = geoLocation;
    }
}
