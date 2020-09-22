package com.ideas.scheduler.learning_group.exception;

public class LGNotFoundException extends RuntimeException{

    public LGNotFoundException(int id){
        super(String.format("Learning Group with id %d not found.", id));
    }

}
