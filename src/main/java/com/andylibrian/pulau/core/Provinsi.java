package com.andylibrian.pulau.core;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Provinsi {

    private final String type = "provinsi";
    private String id;
    private String name;

    public Provinsi(String id, String name) {
        this.id = id;
        this.name = name;
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
}
