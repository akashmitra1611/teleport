package com.teleport.dto;

public enum CustomMessages {

    INVALID_REQUEST("E:001 : Invalid Request : Please Check Payload");


    String key;
    CustomMessages(String code){
        key=code;
    }

    public String getValue(){
        return this.key;
    }

}
