package com.andylibrian.pulau.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Kecamatan {

    private final String type = "kecamatan";

    private final String id;
    private final String name;
    private final String idKabupaten;

    public Kecamatan(String id, String name, String idKabupaten) {
        this.id = id;
        this.name = name;
        this.idKabupaten = idKabupaten;
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
    public String getIdKabupaten() {
        return idKabupaten;
    }
}
