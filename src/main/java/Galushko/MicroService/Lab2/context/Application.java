package Galushko.MicroService.Lab2.context;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = {"Galushko.MicroService.Lab2.model",
        "Galushko.MicroService.Lab2.controller",
        "Galushko.MicroService.Lab2.service",
        "Galushko.MicroService.Lab2.repository"})
@Import(HibernateConfCommon.class)
public class Application {
}
