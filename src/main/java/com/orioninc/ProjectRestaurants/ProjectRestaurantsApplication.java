package com.orioninc.ProjectRestaurants;

import com.orioninc.ProjectRestaurants.auth.AuthenticationFacadeImpl;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProjectRestaurantsApplication {

    public static void main(String[] args) {
        SpringApplication. run(ProjectRestaurantsApplication.class, args);
    }
}
