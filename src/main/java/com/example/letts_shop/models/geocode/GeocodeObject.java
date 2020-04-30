package com.example.letts_shop.models.geocode;

import com.example.letts_shop.models.geocode.AddressComponent;
import com.example.letts_shop.models.geocode.GeocodeGeometry;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GeocodeObject {

    @JsonProperty("place_id")
    private String placeId;

    @JsonProperty("address_components")
    private List<AddressComponent> addressComponent;

    @JsonProperty("formatted_address")
    private String formattedAddress;

    private GeocodeGeometry geometry;

    public GeocodeObject() { }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public List<AddressComponent> getAddressComponent() {
        return addressComponent;
    }

    public void setAddressComponent(List<AddressComponent> addressComponent) {
        this.addressComponent = addressComponent;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public GeocodeGeometry getGeometry() {
        return geometry;
    }

    public void setGeometry(GeocodeGeometry geometry) {
        this.geometry = geometry;
    }
}
