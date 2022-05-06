package com.sumup.drone_challenge.logic;

public class East extends Direction {

    private String name = "EATS";


    public East(Orientation orientation, int minX, int maxX, int minY, int maxY) {
        this.orientation = orientation;
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
    }

    public void init() {
        this.left = orientation.getNorth();
        this.right = orientation.getSouth();
    }

    public String getName() {
        return name;
    }

    @Override
    public void moveForward() {
        if (left != null && right != null) {
            if (orientation != null) {
                if (orientation.getPosition().x + 1 < minX && orientation.getPosition().x + 1 > maxX) {
                    throw new IllegalStateException("Cannot move");
                } else {
                    orientation.getPosition().x += 1;
                }
            } else {
                throw new IllegalStateException("Drone state is unknown");
            }
        } else {
            throw new IllegalStateException("Directions are not set");
        }
    }
}
