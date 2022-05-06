package com.sumup.drone_challenge.logic;

public class Direction implements IDirection {

    protected Orientation orientation;
    protected IDirection left;
    protected IDirection right;
    protected int minX;
    protected int maxX;
    protected int minY;
    protected int maxY;

    @Override
    public String getName() {
        return "UNDEFINED";
    }

    @Override
    public void turnLeft() {
        if (left != null && right != null) {
            orientation.setDirection(left);
        }  else {
            throw new IllegalStateException("Directions are not set");
        }
    }

    @Override
    public void turnRight() {
        if (left != null && right != null) {
            orientation.setDirection(right);
        }  else {
            throw new IllegalStateException("Directions are not set");
        }
    }

    @Override
    public void moveForward() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void init() {
        throw new UnsupportedOperationException("Not implemented");
    }
}
