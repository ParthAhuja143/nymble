package com.nymble;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class TravelPackageTest {

    @Test
    public void testAddDestination() {
        TravelPackage travelPackage = new TravelPackage("Europe Tour", 2);
        Destination paris = new Destination("Paris");

        travelPackage.addDestination(paris);

        assertEquals(1, travelPackage.getItinerary().size());
    }

    @Test
    public void testAddPassenger() {
        TravelPackage travelPackage = new TravelPackage("Europe Tour", 2);
        Passenger johnDoe = new NormalPassenger("John Doe", 100.0);

        travelPackage.addPassenger(johnDoe);

        assertEquals(1, travelPackage.getPassengers().size());
    }

    @Test
    public void testSignUpForActivity() {
        TravelPackage travelPackage = new TravelPackage("Europe Tour", 2);
        Destination paris = new Destination("Paris");
        Activity eiffelTowerTour = new Activity("Eiffel Tower Tour", "Guided tour of the Eiffel Tower", 50.0, 1, paris);
        paris.addActivity(eiffelTowerTour);
        travelPackage.addDestination(paris);
        Passenger johnDoe = new NormalPassenger("John Doe", 100.0);
        travelPackage.addPassenger(johnDoe);

        assertEquals(1, johnDoe.getSignedUpActivities().size());
    }

    @Test
    public void testSignUpForActivityFullCapacity() {
        TravelPackage travelPackage = new TravelPackage("Europe Tour", 1);
        Destination paris = new Destination("Paris");
        Activity eiffelTowerTour = new Activity("Eiffel Tower Tour", "Guided tour of the Eiffel Tower", 50.0, 2, paris);
        paris.addActivity(eiffelTowerTour);
        travelPackage.addDestination(paris);
        Passenger johnDoe = new NormalPassenger("John Doe", 100.0);
        Passenger janeDoe = new NormalPassenger("Jane Doe", 150.0);
        travelPackage.addPassenger(johnDoe);
        travelPackage.addPassenger(janeDoe);

        assertEquals(0, janeDoe.getSignedUpActivities().size());
    }
}
