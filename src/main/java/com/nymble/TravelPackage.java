/**
 * Represents different passenger types and their behavior within the travel package system.
 *
 * @author Parth Ahuja
 * @version 1.0
 * @since 2024-01-01
 */
package com.nymble;

import java.util.ArrayList;
import java.util.List;

public class TravelPackage {

    /**
     * The name of the travel package.
     */
    private String name;

    /**
     * The maximum number of passengers allowed in the package.
     */
    private int passengerCapacity;

    /**
     * A list of destinations included in the itinerary.
     */
    private List<Destination> itinerary;

    /**
     * A list of passengers enrolled in the package.
     */
    private List<Passenger> passengers;

    /**
     * The base cost of the travel package without activities.
     */
    private int baseCost;

    /**
     * Constructs a new travel package with the specified name and capacity.
     *
     * @param name The name of the package.
     * @param passengerCapacity The maximum number of passengers allowed.
     */
    public TravelPackage(String name, int passengerCapacity) {
        this.name = name;
        this.passengerCapacity = passengerCapacity;
        this.itinerary = new ArrayList<>();
        this.passengers = new ArrayList<>();
        this.baseCost = 0;
    }

    /**
     * Adds a destination to the travel package itinerary and updates the base cost.
     *
     * @param destination The destination to add.
     */
    public void addDestination(Destination destination) {
        itinerary.add(destination);
        baseCost += destination.getCost();
        for (Activity a : destination.getActivities()) {
            baseCost += a.getCost();
        }
    }

    /**
     * Calculates the total cost of the travel package for a specific passenger based on their type and any applicable discounts.
     *
     * @param passenger The passenger to calculate the cost for.
     * @return The total cost for the passenger.
     */
    public int calculateTotalCost(Passenger passenger) {
        int totalCost = baseCost;
        if (passenger.getType() == PassengerType.GOLD) {
            return totalCost; // No discount for Gold passengers
        } else if (passenger.getType() == PassengerType.SILVER) {
            totalCost = (int) (baseCost * 0.9); // 10% discount for Silver passengers
        }

        else{
            return 0;
        }
        return totalCost;
    }

    /**
     * Adds a passenger to the travel package, checking for available capacity and applying discounts if applicable.
     *
     * @param passenger The passenger to add.
     */
    public void addPassenger(Passenger passenger) {
        if (passengers.size() < passengerCapacity) {
            if (passenger.getBalance() < calculateTotalCost(passenger)) {
                System.out.println("Insufficient balance for " + passenger.getName() + ". Cannot add to package.");
                return;
            }

            passengers.add(passenger);
            for (Destination d : itinerary) {
                for (Activity a : d.getActivities()) {
                    a.signUpPassenger(passenger);
                    passenger.addToSignUpActivities(a);
                }
            }
            System.out.println(passenger.getName() + " added to " + name + ".");
        } else {
            System.out.println("Travel package is at full capacity. Cannot add more passengers.");
        }
    }

    /**
     * Generates a detailed report of the travel package with itinerary, costs, passenger information, and activity details.
     *
     * @return A formatted string containing the report information.
     */
    public String generateReport() {
        StringBuilder report = new StringBuilder();
        report.append("Travel Package Report for " + name + "\n");
        report.append("Itinerary:\n");
        for (Destination destination : itinerary) {
            report.append("  - " + destination.getName() + "\n");
            for (Activity activity : destination.getActivities()) {
                report.append("    - " + activity.getName() + " (Cost: " + activity.getCost() + ", Capacity: " + activity.getCapacity() + ")\n");
            }
        }
        report.append("Passengers:\n");
        report.append("  - Total Passengers: " + passengers.size() + "\n");
        for (Passenger passenger : passengers) {
            report.append("    - " + passenger.getName() + " (" + passenger.getType() + ", Balance: " + passenger.getBalance() + 
            ", Total Cost: " + calculateTotalCost(passenger) + ")\n");
       }
       report.append("Cost Breakdown:\n");
       report.append("  - Base Cost: " + baseCost + "\n");
       report.append("  - Total Cost for All Passengers: " + calculateTotalCostForAllPassengers() + "\n");
       report.append("Activities:\n");
       for (Destination destination : itinerary) {
           report.append("  - " + destination.getName() + "\n");
           for (Activity activity : destination.getActivities()) {
               report.append("    - " + activity.getName() + " (Capacity: " + activity.getCapacity() + ", Available Spaces: " + activity.getCapacity() + ")\n");
           }
       }
       return report.toString();
   }

   /**
    * Calculates the total cost of the travel package for all enrolled passengers.
    *
    * @return The total cost for all passengers.
    */
   public int calculateTotalCostForAllPassengers() {
       int totalCost = 0;
       for (Passenger passenger : passengers) {
           totalCost += calculateTotalCost(passenger);
       }
       return totalCost;
   }

   /**
    * Print's activities
    */
   public void printItinerary() {
        System.out.println("Travel Package Itinerary for " + name + ":");
        for (Destination destination : itinerary) {
            System.out.println(destination.getName() + ":");
            for (Activity activity : destination.getActivities()) {
                System.out.println("  - " + activity.getName() +
                        " (Cost: " + activity.getCost() +
                        ", Capacity: " + activity.getCapacity() +
                        ", Description: " + activity.getDestination().getName() + ")");
            }
        }
    }

    /**
     * Prints Passenger List
     */
    public void printPassengerList() {
        System.out.println("Passenger List for Travel Package " + name + ":");
        System.out.println("Passenger Capacity: " + passengerCapacity);
        System.out.println("Number of Passengers Enrolled: " + passengers.size());
        for (Passenger passenger : passengers) {
            System.out.println("- " + passenger.getName() +
                    " (Passenger Number: " + passenger.getPassengerNumber() +
                    ", Balance: " + passenger.getBalance() + ")");
        }
    }

    /**
     * Prints Acitvity details
     */
    public void printActivityDetails() {
        System.out.println("Activity Details for Travel Package " + name + ":");
        for (Destination destination : itinerary) {
            for (Activity activity : destination.getActivities()) {
                System.out.println("- " + activity.getName() +
                        " at " + destination.getName() +
                        " (Capacity: " + activity.getCapacity() +
                        ", Available Spaces: " + activity.getCapacity() +
                        ", Cost: " + activity.getCost() + ")");
            }
        }
    }

    /**
     * Returns Acitvity details
     * @return List<Destination> 
     */
    public List<Destination> getItinerary() {
        return itinerary;
    }

    /**
     * Returns Passenger details
     * @return List<Passenger> 
     */
    public List<Passenger> getPassengers() {
        return passengers;
    }
}

