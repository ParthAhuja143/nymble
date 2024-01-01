package com.nymble;

import org.junit.Test;
import static org.junit.Assert.*;

public class ActivityTest {

    @Test
    public void testSignUpPassenger() {
        Destination paris = new Destination("Paris");
        Activity eiffelTowerTour = new Activity("Eiffel Tower Tour", "Guided tour of the Eiffel Tower", 50.0, 2, paris);
        Passenger johnDoe = new NormalPassenger("John Doe", 100.0);

        assertTrue(eiffelTowerTour.signUpPassenger(johnDoe));
        assertEquals(1, eiffelTowerTour.getSignedUpPassengers().size());
    }

    @Test
    public void testSignUpPassengerFullCapacity() {
        Destination newYork = new Destination("New York");
        Activity centralParkPicnic = new Activity("Central Park Picnic", "Picnic in Central Park", 30.0, 1, newYork);
        Passenger janeDoe = new NormalPassenger("Jane Doe", 150.0);

        assertTrue(centralParkPicnic.signUpPassenger(janeDoe));
        assertFalse(centralParkPicnic.signUpPassenger(new NormalPassenger("Another Passenger", 200.0)));
    }

    @Test
    public void testGetSignedUpPassengers() {
        Destination paris = new Destination("Paris");
        Activity eiffelTowerTour = new Activity("Eiffel Tower Tour", "Guided tour of the Eiffel Tower", 50.0, 2, paris);
        Passenger johnDoe = new NormalPassenger("John Doe", 100.0);
        eiffelTowerTour.signUpPassenger(johnDoe);

        assertEquals(1, eiffelTowerTour.getSignedUpPassengers().size());
        assertEquals(johnDoe, eiffelTowerTour.getSignedUpPassengers().get(0));
    }

    @Test
    public void testDecreaseCapacity() {
        Destination newYork = new Destination("New York");
        Activity centralParkPicnic = new Activity("Central Park Picnic", "Picnic in Central Park", 30.0, 2, newYork);
        centralParkPicnic.decreaseCapacity();
        assertEquals(1, centralParkPicnic.getCapacity());
    }

    @Test
    public void testDecreaseCapacityBelowZero() {
        Destination newYork = new Destination("New York");
        Activity centralParkPicnic = new Activity("Central Park Picnic", "Picnic in Central Park", 30.0, 1, newYork);
        centralParkPicnic.decreaseCapacity();
        centralParkPicnic.decreaseCapacity();
        assertEquals(0, centralParkPicnic.getCapacity());
    }

    @Test
    public void testActivityDetails() {
        Destination paris = new Destination("Paris");
        Activity eiffelTowerTour = new Activity("Eiffel Tower Tour", "Guided tour of the Eiffel Tower", 50.0, 2, paris);
        String expectedDetails = "- Eiffel Tower Tour at Paris (Capacity: 2, Available Spaces: 2, Cost: 50.0)";
        assertEquals(expectedDetails, eiffelTowerTour.getActivityDetails());
    }

    @Test
    public void testActivityDetailsFullCapacity() {
        Destination newYork = new Destination("New York");
        Activity centralParkPicnic = new Activity("Central Park Picnic", "Picnic in Central Park", 30.0, 1, newYork);
        centralParkPicnic.signUpPassenger(new NormalPassenger("Jane Doe", 150.0));
        String expectedDetails = "- Central Park Picnic at New York (Capacity: 1, Available Spaces: 0, Cost: 30.0)";
        assertEquals(expectedDetails, centralParkPicnic.getActivityDetails());
    }

    @Test
    public void testActivityDetailsEmptyActivity() {
        Destination newYork = new Destination("New York");
        Activity centralParkPicnic = new Activity("Central Park Picnic", "Picnic in Central Park", 30.0, 2, newYork);
        String expectedDetails = "- Central Park Picnic at New York (Capacity: 2, Available Spaces: 2, Cost: 30.0)";
        assertEquals(expectedDetails, centralParkPicnic.getActivityDetails());
    }

    // Add more test cases as needed...
}
