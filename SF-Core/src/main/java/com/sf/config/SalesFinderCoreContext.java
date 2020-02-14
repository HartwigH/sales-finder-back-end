package com.sf.config;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan({"com.sf.service"})
@EnableJpaRepositories(basePackages = "com.sf.dao")
@EntityScan( basePackages = {"com.sf.model"} )
@EnableAutoConfiguration
@PropertySource("classpath:application.properties")
public class SalesFinderCoreContext {

}
