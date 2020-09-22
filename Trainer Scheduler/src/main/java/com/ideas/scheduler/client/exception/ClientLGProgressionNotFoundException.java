package com.ideas.scheduler.client.exception;

public class ClientLGProgressionNotFoundException extends RuntimeException{

    public ClientLGProgressionNotFoundException(int id, int learningSessionId){
        super(String.format("ClientLGProgression with client id %d and learning session id %d not found.", id, learningSessionId));
    }

}
