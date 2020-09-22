package com.ideas.scheduler.scheduling.exception;

public class WorkLeaveNotFoundException extends RuntimeException{

    public WorkLeaveNotFoundException(){
        super(String.format("WorkLeave not found."));
    }

}
