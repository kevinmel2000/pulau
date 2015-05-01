package com.andylibrian.pulau.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Kabupaten {

    private final String type = "kabupaten";

    private final String id;
    private final String name;
    private final String idProvinsi;

    public Kabupaten(String id, String name, String idProvinsi) {
        this.id = id;
        this.name = name;
        this.idProvinsi = idProvinsi;
    }

    @JsonProperty
    public String getType() {
        return type;
    }

    @JsonProperty
    public String getId() {
        return id;
    }

    @JsonProperty
    public String getName() {
        return name;
    }
    
    @JsonIgnore
    public String getIdProvinsi() {
        return idProvinsi;
    }
}
