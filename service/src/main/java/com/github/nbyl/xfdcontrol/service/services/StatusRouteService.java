package com.github.nbyl.xfdcontrol.service.services;


import com.github.nbyl.xfdcontrol.core.status.JobStatus;
import com.google.common.base.Optional;
import org.apache.camel.CamelContext;
import org.apache.camel.Route;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class StatusRouteService {

    private static final String ROUTE_NAME = "StatusUpdateRoute";

    @Autowired
    private SettingsService settingsService;

    @Autowired
    private CamelContext camelContext;

    @PostConstruct
    public void startStatusRoute() throws Exception {
        Optional<Route> oldRoute = Optional.fromNullable(camelContext.getRoute(ROUTE_NAME));
        if (oldRoute.isPresent()) {
            camelContext.stopRoute(ROUTE_NAME);
            camelContext.removeRoute(ROUTE_NAME);
        }

        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                // TODO: make this configurable
                from("timer://statusUpdate?fixedRate=true&delay=0&period=10000")
                        .to(settingsService.getSettings().getJenkinsUrl() + "/api/json")
                        .unmarshal().json(JsonLibrary.Gson, JobStatus.class)
                        .to("bean:statusDispatcher")
                        .routeId(ROUTE_NAME);
            }
        });
    }
}
