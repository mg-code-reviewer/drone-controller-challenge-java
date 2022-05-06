package com.sumup.drone_challenge.logic;

import java.util.ArrayList;
import java.util.List;

public class Orientation {
    private IDirection direction;
    private Position position;

    private transient North north;
    private transient East east;
    private transient South south;
    private transient West west;


    public Orientation(int minX, int maxX, int minY, int maxY) {

        List<IDirection> directions = new ArrayList<>();

        directions.add(this.setNorth(new North(this, minX, maxX, minY, maxY)));
        directions.add(this.setEast(new East(this, minX, maxX, minY, maxY)));
        directions.add(this.setSouth(new South(this, minX, maxX, minY, maxY)));
        directions.add(this.setWest(new West(this, minX, maxX, minY, maxY)));

        directions.forEach(IDirection::init);

    }

    public static DroneStateBuilder builder() {
        return new DroneStateBuilder();
    }

    public IDirection getDirection() {
        return direction;
    }

    public void setDirection(IDirection direction) {
        this.direction = direction;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public North getNorth() {
        return north;
    }

    public North setNorth(North north) {
        this.north = north;
        return north;
    }

    public East getEast() {
        return east;
    }

    public East setEast(East east) {
        this.east = east;
        return east;
    }

    public South getSouth() {
        return south;
    }

    public South setSouth(South south) {
        this.south = south;
        return south;
    }

    public West getWest() {
        return west;
    }

    public West setWest(West west) {
        this.west = west;
        return west;
    }

    public static class DroneStateBuilder {
        private IDirection direction;
        private Position position;

        public DroneStateBuilder direction(IDirection direction) {
            this.direction = direction;
            return this;
        }

        public DroneStateBuilder position(Position position) {
            this.position = position;
            return this;
        }

        public Orientation build() {
            Orientation orientation = new Orientation(0, 9, 0, 9);
            orientation.setDirection(direction);
            orientation.setPosition(position);
            return orientation;
        }
    }
}
