package com.ideas.scheduler.resource.exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(int id){
        super(String.format("Resource with id %d not found.", id));
    }
}
