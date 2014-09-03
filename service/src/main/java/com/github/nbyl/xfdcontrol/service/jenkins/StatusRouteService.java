/*
 * Copyright 2014 Nicolas Byl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.nbyl.xfdcontrol.service.jenkins;


import com.github.nbyl.xfdcontrol.core.settings.GlobalSettings;
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
    private CamelContext camelContext;

    @Autowired
    private GlobalSettings settings;

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
                from("timer://statusUpdate?fixedRate=true&delay=0&period=10000")
                        .to(settings.getJobUrl() + "/api/json")
                        .unmarshal().json(JsonLibrary.Gson, JenkinsJobStatus.class)
                        .convertBodyTo(JenkinsJobStatusEvent.class)
                        .to("spring-event://default")
                        .routeId(ROUTE_NAME);
            }
        });
    }
}
