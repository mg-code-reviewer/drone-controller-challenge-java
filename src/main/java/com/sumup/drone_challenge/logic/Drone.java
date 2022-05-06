package com.sumup.drone_challenge.logic;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Drone {

    private static final String[] DIRECTIONS = new String[] {"N", "E", "S", "W"};

    private Orientation orientation;
    private Sector sector;
    private int x = 0;
    private int y = 0;
    private int direction = 0;

    public Drone(Sector sector){
        this.orientation = new Orientation(0, 9, 0, 9);
        Position position = new Position();
        position.x = x;
        position.y = y;

        orientation.setDirection(orientation.getNorth());
        orientation.setPosition(position);
        this.sector = sector;
    }

    public Drone(Sector sector, int x, int y, String direction){
        this.orientation = new Orientation(0, 9, 0, 9);
        Position position = new Position();
        position.x = x;
        position.y = y;

        orientation.setDirection(orientation.getNorth());
        orientation.setPosition(position);

        this.sector = sector;
        this.x = x;
        this.y = y;
        this.direction = Arrays.binarySearch(DIRECTIONS, direction) > 0 ? Arrays.binarySearch(DIRECTIONS, direction) : 0;

        switch(direction){
            case "N":
                orientation.setDirection(orientation.getNorth());
                break;
            case "E":
                orientation.setDirection(orientation.getEast());
                break;
            case "S":
                orientation.setDirection(orientation.getSouth());
                break;
            case "W":
                orientation.setDirection(orientation.getWest());
                break;
            default:
                break;
        }
    }

    public Orientation getDroneState() {
        return orientation;
    }

    public Sector getSector(){
        return sector;
    }

    public int getX(){
        x = orientation.getPosition().x;
        return x;
    }

    public int getY(){
        y = orientation.getPosition().y;
        return y;
    }

    public String getDirection(){
        return getDroneState().getDirection().getName();
    }

    public static final List<String> getDirections(){
        return Collections.unmodifiableList(Arrays.asList(DIRECTIONS));
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Drone drone = (Drone) o;
        return x == drone.x && y == drone.y && direction == drone.direction && Objects.equals(sector, drone.sector);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sector, x, y, direction);
    }

    @Override
    public String toString() {
        return "Drone{" +
                "sector=" + sector +
                ", x=" + x +
                ", y=" + y +
                ", direction=" + direction +
                '}';
    }
}
