package com.ideas.scheduler.client.exception;

public class ClientNotFoundException extends RuntimeException{

    public ClientNotFoundException(int id){
        super(String.format("Client with id %d not found.", id));
    }

}
