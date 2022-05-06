package com.sumup.drone_challenge.logic;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.sumup.drone_challenge.logic.Error;
import java.util.ArrayList;
import java.util.List;

public class ResponseError {

    @JsonProperty("errors")
    private List<Error> errors;

    public ResponseError(){}

    public ResponseError(Error error){
        this.errors = new ArrayList<>();
        this.errors.add(error);
    }

    public ResponseError(List<Error> errors){
        this.errors = errors;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }
}
