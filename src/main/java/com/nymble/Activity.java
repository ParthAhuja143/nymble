/**
 * Represents different passenger types and their behavior within the travel package system.
 *
 * @author Parth Ahuja
 * @version 1.0
 * @since 2024-01-01
 */

package com.nymble;
import java.util.List;
import java.util.ArrayList;

class Activity {

    /**
     * The name of the activity.
     */
    private String name;

    /**
     * A description of the activity.
     */
    private String description;

    /**
     * The cost of participating in the activity.
     */
    private double cost;

    /**
     * The maximum number of passengers that can participate in the activity.
     */
    private int capacity;

    /**
     * The destination where the activity takes place.
     */
    private Destination destination;

    /**
     * The list of passengers who have signed up for the activity.
     */
    private List<Passenger> signedUpPassengers;

    /**
     * Constructs a new Activity object with the specified details.
     *
     * @param name The name of the activity
     * @param description A description of the activity
     * @param cost The cost of the activity
     * @param capacity The maximum capacity of the activity
     * @param destination The destination where the activity takes place
     */
    public Activity(String name, String description, double cost, int capacity, Destination destination) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.destination = destination;
        this.signedUpPassengers = new ArrayList<>();
        this.capacity = Math.max(0, capacity);
    }

    /**
     * Returns the description of the activity.
     *
     * @return The activity description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the name of the activity.
     *
     * @return The activity name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the cost of participating in the activity.
     *
     * @return The activity cost
     */
    public double getCost() {
        return cost;
    }

    /**
     * Returns the maximum capacity of the activity.
     *
     * @return The activity capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Returns the destination where the activity takes place.
     *
     * @return The activity destination
     */
    public Destination getDestination() {
        return destination;
    }

    /**
     * Returns a list of passengers who have signed up for the activity.
     *
     * @return The list of signed-up passengers
     */
    public List<Passenger> getSignedUpPassengers() {
        return signedUpPassengers;
    }

    /**
     * Attempts to sign up a passenger for the activity if there's available capacity.
     *
     * @param passenger The passenger to sign up
     * @return true if the passenger was successfully signed up, false if the activity is full
     */
    public boolean signUpPassenger(Passenger passenger) {
        if (capacity > 0) {
            signedUpPassengers.add(passenger);
            capacity--;
            return true;
        }
        return false;
    }

    /**
     * Decreases the activity's capacity by 1, ensuring it doesn't go below 0.
     */
    public void decreaseCapacity() {
        if (capacity > 0) {
            capacity--;
        }
    }

    /**
     * Provides a formatted string with details about the activity, including its name, destination,
     * capacity, available spaces, and cost.
     *
     * @return A formatted string with activity details
     */
    public String getActivityDetails() {
        return "- " + name + " at " + destination.getName() +
                " (Capacity: " + (capacity + signedUpPassengers.size()) +
                ", Available Spaces: " + capacity +
                ", Cost: " + cost + ")";
    }
}
