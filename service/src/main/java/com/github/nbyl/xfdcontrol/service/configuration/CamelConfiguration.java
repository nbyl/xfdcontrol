package com.github.nbyl.xfdcontrol.service.configuration;

import org.apache.camel.CamelContext;
import org.apache.camel.osgi.SpringCamelContextFactory;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CamelConfiguration implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Bean
    public CamelContext camelContext() {
        return new SpringCamelContext(this.applicationContext);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
