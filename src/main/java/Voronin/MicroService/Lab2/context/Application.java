package Voronin.MicroService.Lab2.context;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = {"Voronin.MicroService.Lab2.model",
        "Voronin.MicroService.Lab2.controller",
        "Voronin.MicroService.Lab2.service",
        "Voronin.MicroService.Lab2.repository"})
@Import(HibernateConfCommon.class)
public class Application {
}
