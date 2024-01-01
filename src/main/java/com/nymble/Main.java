package com.nymble;

public class Main {
    public static void main(String[] args) {
        // Creating destinations
        Destination paris = new Destination("Paris");
        Destination newYork = new Destination("New York");

        // Creating activities
        Activity eiffelTowerTour = new Activity("Eiffel Tower Tour", "Guided tour of the Eiffel Tower", 50.0, 20, paris);
        Activity centralParkPicnic = new Activity("Central Park Picnic", "Picnic in Central Park", 30.0, 15, newYork);

        // Adding activities to destinations
        paris.addActivity(eiffelTowerTour);
        newYork.addActivity(centralParkPicnic);

        // Creating passengers
        Passenger johnDoe = new NormalPassenger("John Doe", 100.0);
        Passenger janeDoe = new NormalPassenger("Jane Doe", 150.0);

        // Creating travel package
        TravelPackage europeTour = new TravelPackage("Europe Tour", 2);

        // Adding destinations to the travel package
        europeTour.addDestination(paris);
        europeTour.addDestination(newYork);

        // Adding passengers to the travel package
        europeTour.addPassenger(johnDoe);
        europeTour.addPassenger(janeDoe);

        // Printing itinerary
        europeTour.printItinerary();

        // Printing passenger list
        europeTour.printPassengerList();

        // Printing activity details
        europeTour.printActivityDetails();

        // John Doe signing up for activities
        johnDoe.signUpForActivity(eiffelTowerTour);

        // Jane Doe signing up for activities
        janeDoe.signUpForActivity(centralParkPicnic);
        janeDoe.signUpForActivity(eiffelTowerTour);
    }
}
