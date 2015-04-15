package com.andylibrian.pulau.resources;

import com.andylibrian.pulau.core.Saying;
import com.andylibrian.pulau.core.Message;
import com.google.common.base.Optional;
import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class PulauResource {

    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    public PulauResource(String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }

    @GET
    @Timed
    public Message sayHi(@QueryParam("name") Optional<String> name) {
        final String value = String.format(template, name.or(defaultName));
        return new Message(counter.incrementAndGet(), value);
    }
}
