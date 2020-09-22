package com.ideas.scheduler.learning_session.exception;

public class LSNotFoundException extends RuntimeException{

    public LSNotFoundException(int id){
        super(String.format("Learning Session with id %d not found.", id));
    }

}
