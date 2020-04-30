package com.example.letts_shop.models.geocode;

import com.example.letts_shop.models.geocode.GeocodeObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GeocodeResults {

    List<GeocodeObject> results;

    String status;

    public GeocodeResults() { }

    public List<GeocodeObject> getResults() {
        return results;
    }

    public void setResults(List<GeocodeObject> results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
