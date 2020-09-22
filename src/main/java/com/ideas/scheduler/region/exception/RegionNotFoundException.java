package com.ideas.scheduler.region.exception;

public class RegionNotFoundException extends RuntimeException{

    public RegionNotFoundException(int id){
        super(String.format("Region with id %d not found.", id));
    }

}
