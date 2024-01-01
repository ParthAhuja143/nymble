package com.nymble;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PassengerTest {

    @Test
    public void testSignUpForActivitySufficientBalance() {
        Destination paris = new Destination("Paris");
        Activity eiffelTowerTour = new Activity("Eiffel Tower Tour", "Guided tour of the Eiffel Tower", 50.0, 20, paris);
        paris.addActivity(eiffelTowerTour);
        Passenger johnDoe = new NormalPassenger("John Doe", 100.0);

        johnDoe.signUpForActivity(eiffelTowerTour);

        assertEquals(50.0, johnDoe.getBalance(), 0.001);
        assertTrue(johnDoe.getSignedUpActivities().contains(eiffelTowerTour));
    }

    @Test
    public void testSignUpForActivityInsufficientBalance() {
        Destination paris = new Destination("Paris");
        Activity eiffelTowerTour = new Activity("Eiffel Tower Tour", "Guided tour of the Eiffel Tower", 150.0, 20, paris);
        paris.addActivity(eiffelTowerTour);
        Passenger johnDoe = new NormalPassenger("John Doe", 100.0);

        johnDoe.signUpForActivity(eiffelTowerTour);

        assertEquals(100.0, johnDoe.getBalance(), 0.001);
        assertTrue(johnDoe.getSignedUpActivities().isEmpty());
    }

    @Test
    public void testSignUpForActivityDiscountForGoldPassenger() {
        Destination paris = new Destination("Paris");
        Activity eiffelTowerTour = new Activity("Eiffel Tower Tour", "Guided tour of the Eiffel Tower", 50.0, 20, paris);
        
        TravelPackage parisTour = new TravelPackage("Paris Tour", 20);
        parisTour.addDestination(paris);
        paris.addActivity(eiffelTowerTour);
        Passenger goldPassenger = new GoldPassenger("Gold Member", 100.0);

        goldPassenger.signUpForActivity(eiffelTowerTour);

        assertEquals(55.0, goldPassenger.getBalance(), 0.001);
        assertTrue(goldPassenger.getSignedUpActivities().contains(eiffelTowerTour));
    }

    @Test
    public void testSignUpForActivityInsufficientBalanceForGoldPassenger() {
        Destination paris = new Destination("Paris");
        Activity eiffelTowerTour = new Activity("Eiffel Tower Tour", "Guided tour of the Eiffel Tower", 150.0, 20, paris);
        paris.addActivity(eiffelTowerTour);
        Passenger goldPassenger = new GoldPassenger("Gold Member", 100.0);

        goldPassenger.signUpForActivity(eiffelTowerTour);

        assertEquals(100.0, goldPassenger.getBalance(), 0.001);
        assertTrue(goldPassenger.getSignedUpActivities().isEmpty());
    }

    @Test
    public void testSignUpForActivityFreeForPremiumPassenger() {
        Destination paris = new Destination("Paris");
        Activity eiffelTowerTour = new Activity("Eiffel Tower Tour", "Guided tour of the Eiffel Tower", 50.0, 20, paris);
        paris.addActivity(eiffelTowerTour);
        Passenger premiumPassenger = new PremiumPassenger("Premium Member", 100.00);

        premiumPassenger.signUpForActivity(eiffelTowerTour);

        assertEquals(100.0, premiumPassenger.getBalance(), 0.001);
        assertTrue(premiumPassenger.getSignedUpActivities().contains(eiffelTowerTour));
    }

    @Test
    public void testGetSignedUpActivities() {
        Destination paris = new Destination("Paris");
        Activity eiffelTowerTour = new Activity("Eiffel Tower Tour", "Guided tour of the Eiffel Tower", 50.0, 20, paris);
        paris.addActivity(eiffelTowerTour);
        Passenger johnDoe = new NormalPassenger("John Doe", 100.0);

        johnDoe.signUpForActivity(eiffelTowerTour);

        assertTrue(johnDoe.getSignedUpActivities().contains(eiffelTowerTour));
    }

    @Test
    public void testGetBalance() {
        Passenger janeDoe = new NormalPassenger("Jane Doe", 150.0);

        assertEquals(150.0, janeDoe.getBalance(), 0.001);
    }

    @Test
    public void testGetBalanceForGoldPassenger() {
        Passenger goldPassenger = new GoldPassenger("Gold Member", 200.0);

        assertEquals(200.0, goldPassenger.getBalance(), 0.001);
    }
}
