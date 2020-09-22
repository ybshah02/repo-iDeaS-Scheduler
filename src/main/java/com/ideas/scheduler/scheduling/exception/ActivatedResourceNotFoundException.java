package com.ideas.scheduler.scheduling.exception;

public class ActivatedResourceNotFoundException extends RuntimeException{

    public ActivatedResourceNotFoundException(int id){
        super(String.format("Activated Resource with resource id %d not found.", id));
    }

}
