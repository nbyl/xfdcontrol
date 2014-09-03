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

package com.github.nbyl.xfdcontrol.service;

import com.google.common.base.StandardSystemProperty;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.File;


@ComponentScan("com.github.nbyl.xfdcontrol")
@EnableAutoConfiguration
@EnableWebMvc
public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);
    public static final String SPRING_CONFIG_LOCATION = "spring.config.location";

    public static void main(String[] args) {
        installConfigLocation();

        SpringApplication.run(Application.class, args);
    }

    private static void installConfigLocation() {
        if (Strings.isNullOrEmpty(System.getProperty(SPRING_CONFIG_LOCATION))) {
            LOGGER.debug("Loading configuration from user home directory {}.", StandardSystemProperty.USER_HOME.value());
            File configFile = new File(StandardSystemProperty.USER_HOME.value(), ".xfdcontrol.properties");
            System.setProperty(SPRING_CONFIG_LOCATION, configFile.getAbsolutePath());
        } else {
            LOGGER.debug("Configuration location already set.");
        }
    }
}
