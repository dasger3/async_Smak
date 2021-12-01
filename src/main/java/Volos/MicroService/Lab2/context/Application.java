package Volos.MicroService.Lab2.context;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = {"Volos.MicroService.Lab2.model",
        "Volos.MicroService.Lab2.controller",
        "Volos.MicroService.Lab2.service",
        "Volos.MicroService.Lab2.repository"})
@Import(HibernateConfCommon.class)
public class Application {
}
