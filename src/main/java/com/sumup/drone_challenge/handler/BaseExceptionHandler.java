package com.sumup.drone_challenge.handler;

import com.sumup.drone_challenge.api.common.ErrorCauses;
import com.sumup.drone_challenge.logic.Error;
import com.sumup.drone_challenge.logic.ResponseError;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseExceptionHandler extends ResponseEntityExceptionHandler {

    @Value("${openapi.show-stacktrace:true}")
    private Boolean showStackTrace;

    private List<String> getStackTrace(Exception ex) {
        return showStackTrace ? Arrays.stream(ex.getStackTrace()).map(StackTraceElement::toString)
                .collect(Collectors.toList()) : null;
    }

    public ResponseError generateResponseError(Exception ex, ErrorCauses message){
        return generateResponseError(ex, message, null);
    }

    public ResponseError generateResponseError(Exception ex, ErrorCauses message, String additionalInfo){
        Error error = new Error();

        String description = message.getMessage();

        if(additionalInfo != null){
            description += additionalInfo;
        }

        error.setCode(message.getId());
        error.setDescription(description);
        error.setDetail(ex.getMessage());
        error.setStack(getStackTrace(ex));

        return new ResponseError(error);
    }




}
