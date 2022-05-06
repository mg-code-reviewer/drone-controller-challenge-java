package com.sumup.drone_challenge.logic;

import javax.validation.constraints.Pattern;
import java.util.Objects;

public class Rotation {

    @Pattern(regexp = "^[LR]{1}$", message = "The rotation string used is not valid. The rotation value must be L or R")
    private String rotation;

    public Rotation() {

    }

    public String getRotation() {
        return rotation;
    }

    public void setRotation(String rotation) {
        this.rotation = rotation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rotation rotation1 = (Rotation) o;
        return Objects.equals(rotation, rotation1.rotation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rotation);
    }

    @Override
    public String toString() {
        return "Rotation{" +
                "rotation='" + rotation + '\'' +
                '}';
    }
}
