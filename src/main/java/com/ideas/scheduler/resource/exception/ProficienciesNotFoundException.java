package com.ideas.scheduler.resource.exception;

public class ProficienciesNotFoundException extends RuntimeException{

    public ProficienciesNotFoundException(int id){
        super(String.format("Proficiency with id %d not found.", id));
    }

}
