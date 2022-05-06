package com.sumup.drone_challenge.service;

import com.sumup.drone_challenge.logic.Drone;
import com.sumup.drone_challenge.logic.Orientation;
import com.sumup.drone_challenge.logic.Position;
import com.sumup.drone_challenge.logic.Sector;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class DroneService {

    private final Sector sector = new Sector(10, 10);
    private Drone drone;

    public DroneService() {
        //Randomly choose the position and direction of the drone on the sector
        Random rand = new Random();
        int randomX = rand.nextInt(1 + 10);
        int randomY = rand.nextInt(1 + 10);
        int randomDirection = rand.nextInt(1 + 2);
        this.drone = new Drone(sector, randomX, randomY, Drone.getDirections().get(randomDirection));
    }

    public Drone getDrone() {
        return drone;
    }

    public Orientation getOrientation() {
        return Orientation.builder()
                .direction(drone.getDroneState().getDirection())
                .position(drone.getDroneState().getPosition())
                .build();
    }

    public Position where() {
        Position position = new Position();
        position.setX(drone.getX());
        position.setY(drone.getY());
        return position;
    }

}
