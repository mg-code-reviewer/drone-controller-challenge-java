package com.sumup.drone_challenge.api.controller;

import com.sumup.drone_challenge.api.common.DroneApi;
import com.sumup.drone_challenge.logic.Drone;
import com.sumup.drone_challenge.logic.Orientation;
import com.sumup.drone_challenge.logic.Position;
import com.sumup.drone_challenge.logic.Response;
import com.sumup.drone_challenge.logic.Rotation;
import com.sumup.drone_challenge.logic.Validator;
import com.sumup.drone_challenge.service.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DroneController implements DroneApi {

    @Autowired
    private DroneService droneService;
    @Autowired
    private Validator validator;

    @Override
    public Response<Orientation> rotate(@RequestBody Rotation rotation) {
        validator.validate(rotation);
        runCommand(droneService.getDrone(), rotation.getRotation());
        return new Response<>(droneService.getOrientation());
    }

    @Override
    public Response<Orientation> goForward() {
        runCommand(droneService.getDrone(), "F");
        return new Response<>(droneService.getOrientation());
    }

    @Override
    public Response<Position> where() {
        return new Response<>(droneService.where());
    }

    public void runCommand(Drone drone, String direction){
        switch (direction) {
            case "R" -> drone.getDroneState().getDirection().turnRight();
            case "L" -> drone.getDroneState().getDirection().turnLeft();
            case "F" -> drone.getDroneState().getDirection().moveForward();
            default -> System.out.println("Error happened");
        }
    }
}
