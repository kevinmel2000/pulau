package com.andylibrian.pulau.core;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Kabupaten {

    private final String type = "kabupaten";

    private final String id;
    private final String name;

    public Kabupaten(String id, String name) {
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
