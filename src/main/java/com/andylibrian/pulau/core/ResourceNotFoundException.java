package com.andylibrian.pulau.core;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;


public class ResourceNotFoundException extends WebApplicationException {

    public ResourceNotFoundException() {
        super(Response.status(Response.Status.NOT_FOUND).type("application/json").build());
    }

    public ResourceNotFoundException(String message) {
        super(Response.status(Response.Status.NOT_FOUND).
                entity(message).type("application/json").build());
    }
}
