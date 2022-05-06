package com.sumup.drone_challenge.api.common;

public enum ErrorCauses {
    ERROR_IN_REQUEST(1012, "There was an error in the request");

    private final int id;
    private final String message;

    ErrorCauses(int id, String message){
        this.id = id;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }
}
