package Planes;

import models.MilitaryType;

import java.util.Objects;

public class MilitaryPlane extends Plane {

    private final MilitaryType militaryType;

    public MilitaryPlane(String model, int maxSpeed, int maxFlightDistance, int maxLoadCapacity,
                         MilitaryType type) {
        super(model, maxSpeed, maxFlightDistance, maxLoadCapacity);
        this.militaryType = type;
    }

    public MilitaryType getMilitaryType() {
        return militaryType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MilitaryPlane that = (MilitaryPlane) o;
        return getMilitaryType() == that.getMilitaryType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getMilitaryType());
    }

    @Override
    public String toString() {
        return super.toString().replace("}",
                ", type=" + militaryType +
                        '}');
    }
}
