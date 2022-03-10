import Planes.ExperimentalPlane;
import models.MilitaryType;
import Planes.MilitaryPlane;
import Planes.PassengerPlane;
import Planes.Plane;

import java.util.*;
import java.util.stream.Collectors;

public class Airport {

    private final List<? extends Plane> planes;

    public Airport(List<? extends Plane> planes) {
        this.planes = planes;
    }

    public List<? extends Plane> getPlanes() {
        return planes;
    }

    public List<PassengerPlane> getPassengerPlanes() {
        return getTypePlanes(PassengerPlane.class);
    }

    public List<MilitaryPlane> getMilitaryPlanes() {
        return getTypePlanes(MilitaryPlane.class);
    }

    public List<ExperimentalPlane> getExperimentalPlanes() {
        return getTypePlanes(ExperimentalPlane.class);
    }

    public List<MilitaryPlane> getTransportMilitaryPlanes() {
        return getTypeMilitaryPlanes(MilitaryType.TRANSPORT);
    }

    public List<MilitaryPlane> getBomberMilitaryPlanes() {
        return getTypeMilitaryPlanes(MilitaryType.BOMBER);
    }

    public PassengerPlane getPassengerPlaneWithMaxPassengersCapacity() {
        return getPassengerPlanes().stream()
                .max(Comparator.comparingInt(PassengerPlane::getPassengersCapacity))
                .orElse(null);
    }

    public void sortByMaxDistance() {
        planes.sort(Comparator.comparingInt(Plane::getMaxFlightDistance));
    }

    public void sortByMaxSpeed() {
        planes.sort(Comparator.comparingInt(Plane::getMaxSpeed));
    }

    public void sortByMaxLoadCapacity() {
        planes.sort(Comparator.comparingInt(Plane::getMaxLoadCapacity));
    }

    @Override
    public String toString() {
        return "Airport{" +
                "Planes=" + planes.toString() +
                '}';
    }

    private <T extends Plane> List<T> getTypePlanes(Class<T> planeClass) {
        return planes.stream()
                .filter(planeClass::isInstance)
                .map(planeClass::cast)
                .collect(Collectors.toList());
    }

    private List<MilitaryPlane> getTypeMilitaryPlanes(MilitaryType militaryType) {
        return getMilitaryPlanes().stream()
                .filter(militaryPlane -> militaryPlane.getMilitaryType() == militaryType)
                .collect(Collectors.toList());
    }

}
