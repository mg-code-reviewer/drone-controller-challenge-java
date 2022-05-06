package com.sumup.drone_challenge.logic;

public interface IDirection {
    String getName();
    void turnLeft();
    void turnRight();
    void moveForward();
    void init();
}
