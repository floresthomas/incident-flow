package com.thomasbuilds.incidentflow.infrastructure;

import com.thomasbuilds.incidentflow.infrastructure.PostgresIncidentRepository;
import com.thomasbuilds.incidentflow.application.IncidentRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IncidentConfiguration {

    @Bean
    public IncidentRepository repositoryConfiguration(){
        String dbUrl = System.getenv("DB_URL");
        String dbUser = System.getenv("DB_USER");
        String dbPassword = System.getenv("DB_PASSWORD");

        return new PostgresIncidentRepository(dbUrl, dbUser, dbPassword);
    }
}
