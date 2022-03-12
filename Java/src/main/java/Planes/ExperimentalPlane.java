package Planes;

import models.ClassificationSecrecyLevel;
import models.ExperimentalTypes;

import java.util.Objects;

public class ExperimentalPlane extends Plane {

    private final ExperimentalTypes experimentalType;
    private ClassificationSecrecyLevel classificationSecrecyLevel;

    public ExperimentalPlane(String model, int maxSpeed, int maxFlightDistance, int maxLoadCapacity,
                             ExperimentalTypes experimentalType, ClassificationSecrecyLevel classificationSecrecyLevel) {
        super(model, maxSpeed, maxFlightDistance, maxLoadCapacity);
        this.experimentalType = experimentalType;
        this.classificationSecrecyLevel = classificationSecrecyLevel;
    }

    public ClassificationSecrecyLevel getClassificationLevel() {
        return classificationSecrecyLevel;
    }

    public void setClassificationLevel(ClassificationSecrecyLevel classificationSecrecyLevel) {
        this.classificationSecrecyLevel = classificationSecrecyLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ExperimentalPlane that = (ExperimentalPlane) o;
        return experimentalType == that.experimentalType && getClassificationLevel() == that.getClassificationLevel();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), experimentalType, getClassificationLevel());
    }

    @Override
    public String toString() {
        return "experimentalPlane{" + "model='" + model + '\'' + '}';
    }
}
