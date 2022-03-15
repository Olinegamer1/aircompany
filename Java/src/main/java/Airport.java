import Planes.ExperimentalPlane;
import models.MilitaryType;
import Planes.MilitaryPlane;
import Planes.PassengerPlane;
import Planes.Plane;

import java.util.*;
import java.util.stream.Collectors;

public class Airport<T extends Plane> {

    private final List<T> planes;

    public Airport(List<T> planes) {
        this.planes = planes;
    }

    public List<T> getPlanes() {
        return planes;
    }

    public List<PassengerPlane> getPassengerPlanes() {
        return getPlanesByClass(PassengerPlane.class);
    }

    public List<MilitaryPlane> getMilitaryPlanes() {
        return getPlanesByClass(MilitaryPlane.class);
    }

    public List<ExperimentalPlane> getExperimentalPlanes() {
        return getPlanesByClass(ExperimentalPlane.class);
    }

    public List<MilitaryPlane> getTransportMilitaryPlanes() {
        return getMilitaryPlanesByType(MilitaryType.TRANSPORT);
    }

    public List<MilitaryPlane> getBomberMilitaryPlanes() {
        return getMilitaryPlanesByType(MilitaryType.BOMBER);
    }

    public PassengerPlane getPassengerPlaneWithMaxPassengersCapacity() {
        return getPassengerPlanes().stream()
                .max(Comparator.comparingInt(PassengerPlane::getPassengersCapacity))
                .orElse(null);
    }

    public Airport<T> sortByMaxDistance() {
        return new Airport<>(sortByComparator(Comparator.comparingInt(Plane::getMaxFlightDistance)));
    }

    public Airport<T> sortByMaxSpeed() {
       return new Airport<>(sortByComparator(Comparator.comparingInt(Plane::getMaxSpeed)));
    }

    public Airport<T> sortByMaxLoadCapacity() {
        return new Airport<>(sortByComparator(Comparator.comparingInt(Plane::getMaxLoadCapacity)));
    }

    @Override
    public String toString() {
        return "Airport{" +
                "Planes=" + planes.toString() +
                '}';
    }

    private <E extends Plane> List<E> getPlanesByClass(Class<E> planeClass) {
        return planes.stream()
                .filter(planeClass::isInstance)
                .map(planeClass::cast)
                .collect(Collectors.toList());
    }

    private List<MilitaryPlane> getMilitaryPlanesByType(MilitaryType militaryType) {
        return getMilitaryPlanes().stream()
                .filter(militaryPlane -> militaryPlane.getMilitaryType() == militaryType)
                .collect(Collectors.toList());
    }

    private List<T> sortByComparator(Comparator<T> comparator) {
        return planes.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }

}
