package com.ideas.scheduler.resource.exception;

public class ResourceLanguagesNotFoundException extends RuntimeException{

    public ResourceLanguagesNotFoundException(int id){
        super(String.format("ResourceLanguage(s) with id %d not found.", id));
    }
}
