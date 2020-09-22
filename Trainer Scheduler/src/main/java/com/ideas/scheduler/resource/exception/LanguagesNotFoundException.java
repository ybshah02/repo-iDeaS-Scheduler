package com.ideas.scheduler.resource.exception;

public class LanguagesNotFoundException extends RuntimeException{

    public LanguagesNotFoundException(int id){
        super(String.format("Language with id %d not found.", id));
    }
}
