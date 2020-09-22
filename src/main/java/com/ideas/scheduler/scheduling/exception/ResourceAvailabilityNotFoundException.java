package com.ideas.scheduler.scheduling.exception;

public class ResourceAvailabilityNotFoundException extends RuntimeException{

    public ResourceAvailabilityNotFoundException(){
        super(String.format("ResourceAvailability not found."));
    }

}
