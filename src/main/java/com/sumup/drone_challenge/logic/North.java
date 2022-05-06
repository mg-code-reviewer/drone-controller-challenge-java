package com.sumup.drone_challenge.logic;

public class North extends Direction {

    private String name = "NORTH";

    public North(Orientation orientation, int minX, int maxX, int minY, int maxY) {
        this.orientation = orientation;
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
    }

    public void init() {
        this.left = orientation.getWest();
        this.right = orientation.getEast();
    }

    public String getName() {
        return name;
    }

    @Override
    public void moveForward() {
        if (left != null && right != null) {
            if (orientation != null) {
                if (orientation.getPosition().y + 1 < minY && orientation.getPosition().y + 1 > maxY) {
                    throw new IllegalStateException("Cannot move");
                } else {
                    orientation.getPosition().y += 1;
                }
            } else {
                throw new IllegalStateException("Drone state is unknown");
            }
        } else {
            throw new IllegalStateException("Directions are not set");
        }
    }
}
