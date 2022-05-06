package com.sumup.drone_challenge.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.sumup.drone_challenge.api.common.ErrorCauses;
import com.sumup.drone_challenge.logic.Direction;
import com.sumup.drone_challenge.logic.Drone;
import com.sumup.drone_challenge.logic.Error;
import com.sumup.drone_challenge.logic.Orientation;
import com.sumup.drone_challenge.logic.Position;
import com.sumup.drone_challenge.logic.Response;
import com.sumup.drone_challenge.logic.ResponseError;
import com.sumup.drone_challenge.logic.Rotation;
import com.sumup.drone_challenge.logic.Validator;
import com.sumup.drone_challenge.service.DroneService;
import java.time.Clock;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = DroneController.class)
public class DroneControllerTest {

    private static final String CONTEXT_URL = "/api";
    private static final String DRONE_ROTATION = "/v1/drone/rotate";
    private static final String DRONE_FORWARD = "/v1/drone/forward";
    private static final String DRONE_WHERE = "/v1/drone/where";

    @Autowired
    MockMvc mockMvc;

    @MockBean
    DroneService droneService;

    @MockBean
    Drone drone;

    @MockBean
    Orientation orientation;

    @MockBean
    Direction direction;

    @MockBean
    Validator validator;

    @MockBean
    Clock clock;

    @Test
    public void testDroneRotate() throws Exception {
        Response<Orientation> response = buildOrientationResponse();
        doNothing().when(validator).validate(any());
        when(droneService.getOrientation()).thenReturn(buildOrientation());
        when(droneService.getDrone()).thenReturn(drone);
        when(drone.getDroneState()).thenReturn(orientation);
        when(orientation.getDirection()).thenReturn(direction);
        when(direction.getName()).thenReturn("TEST");
        when(clock.millis()).thenReturn(1L);
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

        //Executing the test
        String reponse = mockMvc.perform(put(CONTEXT_URL + DRONE_ROTATION).contextPath(CONTEXT_URL)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(objectWriter.writeValueAsString(response)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertThat(reponse.isEmpty()).isFalse();
    }

    @Test
    public void testDroneRotateHttpStatus400() throws Exception {
        Response<Orientation> response = buildOrientationResponse();
        Rotation rotation = new Rotation();
        rotation.setRotation("Z");
        ResponseError responseError = buildResponseErrorForRotateDrone(rotation);

        doThrow(ConstraintViolationException.class).when(validator).validate(any());
        when(droneService.getOrientation()).thenReturn(buildOrientation());
        when(droneService.getDrone()).thenReturn(drone);
        when(drone.getDroneState()).thenReturn(orientation);
        when(orientation.getDirection()).thenReturn(direction);
        when(clock.millis()).thenReturn(1L);
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

        //Executing the test
        mockMvc.perform(put(CONTEXT_URL + DRONE_ROTATION).contextPath(CONTEXT_URL)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(objectWriter.writeValueAsString(responseError)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testDroneMoveForward() throws Exception {
        Response<Orientation> response = buildOrientationResponse();
        doNothing().when(validator).validate(any());
        when(droneService.getOrientation()).thenReturn(buildOrientation());
        when(droneService.getDrone()).thenReturn(drone);
        when(drone.getDroneState()).thenReturn(orientation);
        when(orientation.getDirection()).thenReturn(direction);
        when(clock.millis()).thenReturn(1L);
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

        //Executing the test
        mockMvc.perform(put(CONTEXT_URL + DRONE_FORWARD).contextPath(CONTEXT_URL)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(objectWriter.writeValueAsString(response)))
                .andExpect(status().isOk());
    }

    @Test
    public void testDroneWhere() throws Exception {
        Response<Position> response = buildPositionResponse();
        when(droneService.where()).thenReturn(buildPosition());
        when(clock.millis()).thenReturn(1L);
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

        //Executing the test
        mockMvc.perform(get(CONTEXT_URL + DRONE_WHERE).contextPath(CONTEXT_URL)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(objectWriter.writeValueAsString(response)))
                .andExpect(status().isOk());
    }

    private Orientation buildOrientation() {
        Orientation orientation = new Orientation(0, 9, 0, 9);
        orientation.setDirection(orientation.getNorth());
        return orientation;
    }

    private Response<Orientation> buildOrientationResponse() {
        Orientation orientation = buildOrientation();
        Response<Orientation> response = new Response<>();
        response.setData(orientation);

        return response;
    }

    private Position buildPosition() {
        Position position = new Position();
        position.setX(2);
        position.setY(2);
        return position;
    }

    private Response<Position> buildPositionResponse() {
        Position position = buildPosition();
        Response<Position> response = new Response<>();
        response.setData(position);

        return response;
    }

    private ConstraintViolation buildViolation(Object object) {
        Set<ConstraintViolation<Object>> violationSet = Validation.buildDefaultValidatorFactory().getValidator().validate(object);
        Error error = new Error();
        ConstraintViolation<Object> violation = (ConstraintViolation<Object>) violationSet.toArray()[0];

        return violation;
    }

    private ResponseError buildResponseErrorForRotateDrone(Object object) {
        ConstraintViolation<Rotation> violation = buildViolation(object);
        Error error = new Error();
        error.setCode(ErrorCauses.ERROR_IN_REQUEST.getId());
        error.setDescription(ErrorCauses.ERROR_IN_REQUEST.getMessage());
        error.setDetail(violation.getMessage());

        return new ResponseError(error);
    }


}
