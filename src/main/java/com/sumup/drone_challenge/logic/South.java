package com.sumup.drone_challenge.logic;

public class South extends Direction {

    private String name = "SOUTH";

    public South(Orientation orientation, int minX, int maxX, int minY, int maxY) {
        this.orientation = orientation;
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
    }

    public void init() {
        this.left = orientation.getEast();
        this.right = orientation.getWest();
    }

    public String getName() {
        return name;
    }

    @Override
    public void moveForward() {
        if (left != null && right != null) {
            if (orientation != null) {
                if (orientation.getPosition().y - 1 < minY) {
                    throw new IllegalStateException("Cannot move");
                } else {
                    orientation.getPosition().y -= 1;
                }
            } else {
                throw new IllegalStateException("Drone state is unknown");
            }
        } else {
            throw new IllegalStateException("Directions are not set");
        }
    }
}
