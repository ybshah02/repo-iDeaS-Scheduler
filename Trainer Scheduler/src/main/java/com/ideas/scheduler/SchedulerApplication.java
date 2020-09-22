package com.ideas.scheduler;

import com.ideas.scheduler.region.repository.RegionRepository;
import com.ideas.scheduler.scheduling.repository.ActivatedResourceRepository;
import com.ideas.scheduler.scheduling.repository.ResourceAvailabilityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SchedulerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchedulerApplication.class, args);
    }

    @Bean
    CommandLineRunner init(ActivatedResourceRepository activatedResourceRepository, ResourceAvailabilityRepository resourceAvailabilityRepository, RegionRepository regionRepository) {

        return args -> {

        };
    }
}
