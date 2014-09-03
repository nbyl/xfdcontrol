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
import java.io.FileNotFoundException;


@ComponentScan("com.github.nbyl.xfdcontrol")
@EnableAutoConfiguration
@EnableWebMvc
public class Application {

    public static final String SPRING_CONFIG_LOCATION = "spring.config.location";

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        try {
            installConfigLocation();

            SpringApplication.run(Application.class, args);
        } catch (Exception e) {
            LOGGER.error("Error running the application", e);
        }
    }

    private static void installConfigLocation() throws FileNotFoundException {
        if (Strings.isNullOrEmpty(System.getProperty(SPRING_CONFIG_LOCATION))) {
            File configFile = new File(StandardSystemProperty.USER_HOME.value(), ".xfdcontrol.properties");
            LOGGER.debug("Loading configuration from file {}.", configFile.getAbsolutePath());

            if (!configFile.exists()) {
                throw new FileNotFoundException("Config file " + configFile.getPath() + " does not exist.");
            }

            System.setProperty(SPRING_CONFIG_LOCATION, configFile.getAbsolutePath());
        } else {
            LOGGER.debug("Configuration location already set.");
        }
    }
}
