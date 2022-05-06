package com.sumup.drone_challenge.logic;

public class West extends Direction {

    private String name = "WEST";

    public West(Orientation orientation, int minX, int maxX, int minY, int maxY) {
        this.orientation = orientation;
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
    }


    public void init() {
        this.left = orientation.getSouth();
        this.right = orientation.getNorth();
    }

    public String getName() {
        return name;
    }

    @Override
    public void moveForward() {
        if (left != null && right != null) {
            if (orientation != null) {
                if (orientation.getPosition().x - 1 < minX) {
                    throw new IllegalStateException("Cannot move");
                } else {
                    orientation.getPosition().x -= 1;
                }
            } else {
                throw new IllegalStateException("Drone state is unknown");
            }
        } else {
            throw new IllegalStateException("Directions are not set");
        }
    }
}
