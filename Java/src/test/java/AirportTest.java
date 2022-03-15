import Planes.ExperimentalPlane;
import Planes.MilitaryPlane;
import Planes.PassengerPlane;
import Planes.Plane;
import models.ClassificationSecrecyLevel;
import models.ExperimentalType;
import models.MilitaryType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

public class AirportTest {
    private static final Airport<Plane> AIRPORT = new Airport<>(Arrays.asList(
            new PassengerPlane("Boeing-737", 900, 12000, 60500, 164),
            new PassengerPlane("Boeing-737-800", 940, 12300, 63870, 192),
            new PassengerPlane("Boeing-747", 980, 16100, 70500, 242),
            new PassengerPlane("Airbus A320", 930, 11800, 65500, 188),
            new PassengerPlane("Airbus A330", 990, 14800, 80500, 222),
            new PassengerPlane("Embraer 190", 870, 8100, 30800, 64),
            new PassengerPlane("Sukhoi Superjet 100", 870, 11500, 50500, 140),
            new PassengerPlane("Bombardier CS300", 920, 11000, 60700, 196),
            new MilitaryPlane("B-1B Lancer", 1050, 21000, 80000, MilitaryType.BOMBER),
            new MilitaryPlane("B-2 Spirit", 1030, 22000, 70000, MilitaryType.BOMBER),
            new MilitaryPlane("B-52 Stratofortress", 1000, 20000, 80000, MilitaryType.BOMBER),
            new MilitaryPlane("F-15", 1500, 12000, 10000, MilitaryType.FIGHTER),
            new MilitaryPlane("F-22", 1550, 13000, 11000, MilitaryType.FIGHTER),
            new MilitaryPlane("C-130 Hercules", 650, 5000, 110000, MilitaryType.TRANSPORT),
            new ExperimentalPlane("Bell X-14", 277, 482, 500, ExperimentalType.HIGH_ALTITUDE, ClassificationSecrecyLevel.SECRET),
            new ExperimentalPlane("Ryan X-13 Vertijet", 560, 307, 500, ExperimentalType.VTOL, ClassificationSecrecyLevel.TOP_SECRET)
    ));

    @Test
    public void getTransportMilitaryPlanes() {
        MilitaryPlane transportMilitaryPlane = new MilitaryPlane("C-130 Hercules", 650, 5000, 110000, MilitaryType.TRANSPORT);
        List<MilitaryPlane> otherMilitaryPlanes = Arrays.asList(
                new MilitaryPlane("B-1B Lancer", 1050, 21000, 80000, MilitaryType.BOMBER),
                new MilitaryPlane("B-2 Spirit", 1030, 22000, 70000, MilitaryType.BOMBER),
                new MilitaryPlane("B-52 Stratofortress", 1000, 20000, 80000, MilitaryType.BOMBER),
                new MilitaryPlane("F-15", 1500, 12000, 10000, MilitaryType.FIGHTER),
                new MilitaryPlane("F-22", 1550, 13000, 11000, MilitaryType.FIGHTER)
        );

        List<MilitaryPlane> militaryPlanes = AIRPORT.getTransportMilitaryPlanes();

        Assert.assertTrue(militaryPlanes.contains(transportMilitaryPlane));
        Assert.assertFalse(militaryPlanes.containsAll(otherMilitaryPlanes));
    }

    @Test
    public void getPassengerPlaneWithMaxCapacity() {
        PassengerPlane planeWithMaxPassengerCapacity = new PassengerPlane("Boeing-747", 980, 16100, 70500, 242);
        List<PassengerPlane> otherPassengerPlane = Arrays.asList(
                new PassengerPlane("Boeing-737", 900, 12000, 60500, 164),
                new PassengerPlane("Boeing-737-800", 940, 12300, 63870, 192),
                new PassengerPlane("Airbus A320", 930, 11800, 65500, 188),
                new PassengerPlane("Airbus A330", 990, 14800, 80500, 222),
                new PassengerPlane("Embraer 190", 870, 8100, 30800, 64),
                new PassengerPlane("Sukhoi Superjet 100", 870, 11500, 50500, 140),
                new PassengerPlane("Bombardier CS300", 920, 11000, 60700, 196)
        );


        Assert.assertEquals(planeWithMaxPassengerCapacity, AIRPORT.getPassengerPlaneWithMaxPassengersCapacity());
        Assert.assertFalse(otherPassengerPlane.contains(AIRPORT.getPassengerPlaneWithMaxPassengersCapacity()));
    }

    @Test
    public void sortedByMaxLoadCapacity() {
        Assert.assertTrue(arePlanesSortedByMaxLoadCapacity(AIRPORT.sortByMaxLoadCapacity().getPlanes()));
    }

    @Test
    public void getBomberMilitaryPlanes() {
        List<MilitaryPlane> bomberMilitaryPlanes = Arrays.asList(
                new MilitaryPlane("B-1B Lancer", 1050, 21000, 80000, MilitaryType.BOMBER),
                new MilitaryPlane("B-2 Spirit", 1030, 22000, 70000, MilitaryType.BOMBER),
                new MilitaryPlane("B-52 Stratofortress", 1000, 20000, 80000, MilitaryType.BOMBER)
        );

        Assert.assertTrue(AIRPORT.getBomberMilitaryPlanes().containsAll(bomberMilitaryPlanes));
    }

    @Test
    public void experimentalPlanesHasClassificationLevelHigherThanUnclassified() {
        Assert.assertTrue(isClassificationLevelHigherThanUnclassified(AIRPORT.getExperimentalPlanes()));
    }

    private boolean isClassificationLevelHigherThanUnclassified(List<ExperimentalPlane> experimentalPlanes) {
        return experimentalPlanes.stream()
                .map(ExperimentalPlane::getClassificationLevel)
                .allMatch(classificationSecrecyLevel -> classificationSecrecyLevel.ordinal() > ClassificationSecrecyLevel.UNCLASSIFIED.ordinal());
    }

    private boolean arePlanesSortedByMaxLoadCapacity(List<? extends Plane> planesSortedByMaxLoadCapacity) {
        int[] loadCapacityByPlanes = planesSortedByMaxLoadCapacity.stream()
                .mapToInt(Plane::getMaxLoadCapacity)
                .toArray();

        for (int i = 0; i < loadCapacityByPlanes.length - 1; i++) {
            if (loadCapacityByPlanes[i] > loadCapacityByPlanes[i + 1]) {
                return false;
            }
        }
        return true;
    }
}
