package com.andylibrian.pulau;

import com.andylibrian.pulau.health.TemplateHealthCheck;
import com.andylibrian.pulau.resources.PulauResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class PulauApplication extends Application<PulauConfiguration> {

    public static void main(String[] args) throws Exception {
        new PulauApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<PulauConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(PulauConfiguration configuration,
            Environment environment) {
        final PulauResource resource = new PulauResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );
        final TemplateHealthCheck healthCheck
                = new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
    }

}
