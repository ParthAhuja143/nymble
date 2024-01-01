package com.nymble;

import static org.junit.Assert.*;

import org.junit.Test;

public class DestinationTest {

    @Test
    public void testAddActivity() {
        Destination paris = new Destination("Paris");
        Activity eiffelTowerTour = new Activity("Eiffel Tower Tour", "Guided tour of the Eiffel Tower", 50.0, 2, paris);

        paris.addActivity(eiffelTowerTour);

        assertEquals(1, paris.getActivities().size());
        assertEquals(eiffelTowerTour, paris.getActivities().get(0));
    }

    @Test
    public void testAddMultipleActivities() {
        Destination newYork = new Destination("New York");
        Activity centralParkPicnic = new Activity("Central Park Picnic", "Picnic in Central Park", 30.0, 2, newYork);
        Activity broadwayShow = new Activity("Broadway Show", "Theater performance on Broadway", 80.0, 3, newYork);

        newYork.addActivity(centralParkPicnic);
        newYork.addActivity(broadwayShow);

        assertEquals(2, newYork.getActivities().size());
        assertTrue(newYork.getActivities().contains(centralParkPicnic));
        assertTrue(newYork.getActivities().contains(broadwayShow));
    }


    @Test
    public void testGetActivityDetails() {
        Destination newYork = new Destination("New York");
        Activity centralParkPicnic = new Activity("Central Park Picnic", "Picnic in Central Park", 30.0, 2, newYork);

        newYork.addActivity(centralParkPicnic);
    
        String expectedDetails = "- Central Park Picnic at New York (Capacity: 2, Available Spaces: 2, Cost: 30.0)\n";

        System.out.println(newYork.getActivityDetails() == expectedDetails);
        assertEquals(expectedDetails, newYork.getActivityDetails());
    }

    @Test
    public void testGetActivityDetailsNoActivities() {
        Destination newYork = new Destination("New York");

        String expectedDetails = "";
        assertEquals(expectedDetails, newYork.getActivityDetails());
    }

    @Test
    public void testGetActivityDetailsMultipleActivities() {
        Destination newYork = new Destination("New York");
        Activity centralParkPicnic = new Activity("Central Park Picnic", "Picnic in Central Park", 30.0, 2, newYork);
        Activity broadwayShow = new Activity("Broadway Show", "Theater performance on Broadway", 80.0, 3, newYork);

        newYork.addActivity(centralParkPicnic);
        newYork.addActivity(broadwayShow);

        String expectedDetails = "- Central Park Picnic at New York (Capacity: 2, Available Spaces: 2, Cost: 30.0)\n" +
                                 "- Broadway Show at New York (Capacity: 3, Available Spaces: 3, Cost: 80.0)\n";
        assertEquals(expectedDetails, newYork.getActivityDetails());
    }

    @Test
    public void testGetActivityDetailsEmptyActivity() {
        Destination newYork = new Destination("New York");
        Activity emptyActivity = new Activity("Empty Activity", "No description", 0.0, 0, newYork);

        newYork.addActivity(emptyActivity);

        String expectedDetails = "- Empty Activity at New York (Capacity: 0, Available Spaces: 0, Cost: 0.0)\n";
        assertEquals(expectedDetails, newYork.getActivityDetails());
    }

    @Test
    public void testGetActivityDetailsNegativeCapacity() {
        Destination newYork = new Destination("New York");
        Activity negativeCapacityActivity = new Activity("Negative Capacity", "Negative capacity description", 50.0, -2, newYork);

        newYork.addActivity(negativeCapacityActivity);

        String expectedDetails = "- Negative Capacity at New York (Capacity: 0, Available Spaces: 0, Cost: 50.0)\n";
        assertEquals(expectedDetails, newYork.getActivityDetails());
    }

    // Add more test cases as needed...
}

