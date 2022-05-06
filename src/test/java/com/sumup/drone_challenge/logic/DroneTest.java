package com.sumup.drone_challenge.logic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DroneTest {

    @Test
    public void testDroneSectorConstructor() {
        int maximumX = 10;
        int maximumY = 10;
        Sector sector = new Sector(maximumX, maximumY);

        Drone drone = new Drone(sector);

        assertEquals(0, drone.getX());
        assertEquals(0, drone.getY());
        assertEquals("NORTH", drone.getDirection());
        assertEquals(new Sector(maximumX, maximumY), drone.getSector());
    }

    @Test
    public void testDroneSectorXYDirectionConstructor() {
        int maximumX = 10;
        int maximumY = 10;
        int x = 1;
        int y = 1;
        Sector sector = new Sector(maximumX, maximumY);

        Drone drone = new Drone(sector, x, y, "S");

        assertEquals(x, drone.getX());
        assertEquals(y, drone.getY());
        assertEquals("SOUTH", drone.getDirection());
        assertEquals(new Sector(maximumX, maximumY), drone.getSector());
    }

    @Test
    public void testDroneSectorXYOtherDirectionConstructor() {
        int maximumX = 10;
        int maximumY = 10;
        int x = 1;
        int y = 1;
        Sector sector = new Sector(maximumX, maximumY);

        Drone drone = new Drone(sector, x, y, "E");

        assertEquals(x, drone.getX());
        assertEquals(y, drone.getY());
    }

    @ParameterizedTest
    @CsvSource({
            "3, 3, 0, 0, N, L, WEST, 0, 0",
            "3, 3, 0, 0, S, R, WEST, 0, 0",
            "3, 3, 0, 0, E, L, NORTH, 0, 0",
            "3, 3, 0, 0, E, R, SOUTH, 0, 0",
            "3, 3, 0, 0, W, L, SOUTH, 0, 0",
            "3, 3, 0, 0, W, R, NORTH, 0, 0",
            "3, 3, 2, 2, N, F, NORTH, 2, 3",
            "3, 3, 2, 2, W, F, WEST, 1, 2",
            "3, 3, 2, 2, S, F, SOUTH, 2, 1",
    })
    public void testRunCommandParameterized(int maximumX, int maximumY, int x, int y, String directionStr,
                                       String command, String expectedDirection, int expectedX, int expectedY) {
        Sector sector = new Sector(maximumX, maximumY);
        Drone drone = new Drone(sector, x, y, directionStr);

        runCommand(drone, command);

        assertEquals(expectedDirection, drone.getDirection());
        assertEquals(expectedX, drone.getX());
        assertEquals(expectedY, drone.getY());
    }

    public void runCommand(Drone drone, String direction){
        switch(direction){
            case "R":
                drone.getDroneState().getDirection().turnRight();
                break;
            case "L":
                drone.getDroneState().getDirection().turnLeft();
                break;
            case "F":
                drone.getDroneState().getDirection().moveForward();
                break;
            default:
                break;
        }
    }

}
