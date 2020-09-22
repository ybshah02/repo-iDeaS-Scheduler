package com.ideas.scheduler.resource.exception;

public class CapabilitiesNotFoundException extends RuntimeException{

    public CapabilitiesNotFoundException(int id){
        super(String.format("Capabilities with resource id %d not found.", id));
    }
}
