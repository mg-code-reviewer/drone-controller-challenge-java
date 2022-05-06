package com.sumup.drone_challenge.logic;

import java.util.Objects;

public class Sector {
    private int maximumX = 0;
    private int maximumY = 0;

    public Sector(int maximumX, int maximumY) {
        this.maximumX = maximumX;
        this.maximumY = maximumY;
    }

    public int getMaximumX() {
        return maximumX;
    }

    public int getMaximumY() {
        return maximumY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sector sector = (Sector) o;
        return maximumX == sector.maximumX && maximumY == sector.maximumY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(maximumX, maximumY);
    }

    @Override
    public String toString() {
        return "Sector{" +
                "maximumX=" + maximumX +
                ", maximumY=" + maximumY +
                '}';
    }
}
