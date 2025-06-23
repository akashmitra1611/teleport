package com.teleport.dto;

public class HandledExceptions extends Exception{

    private static final long serialVersionUID = -470180507998010368L;

    public HandledExceptions(final String message){
        super(message);
    }
}
