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

/**
 * Represents a specific destination within a travel package.
 *
 * @author [Your Name]
 * @version 1.0
 * @since 2024-01-01
 */
public class Destination {

    /**
     * The name of the destination.
     */
    private String name;

    /**
     * A list of activities offered at this destination.
     */
    private List<Activity> activities;

    /**
     * The total cost of the destination, including activities.
     */
    private int cost;

    /**
     * Constructs a new Destination object with the specified name.
     *
     * @param name The name of the destination
     */
    public Destination(String name) {
        this.name = name;
        this.activities = new ArrayList<>();
    }

    /**
     * Constructs a new Destination object with the specified name and cost.
     *
     * @param name The name of the destination
     * @param cost The base cost of the destination (excluding activities)
     */
    public Destination(String name, int cost) {
        this.name = name;
        this.activities = new ArrayList<>();
        this.cost = cost;
    }

    /**
     * Returns the total cost of the destination, including activities.
     *
     * @return The total cost of the destination
     */
    public int getCost() {
        return cost;
    }

    /**
     * Adds an activity to the destination and updates the total cost.
     *
     * @param activity The activity to add
     */
    public void addActivity(Activity activity) {
        activities.add(activity);
        cost += activity.getCost();
    }

    /**
     * Decreases the capacity of a specific activity at this destination.
     *
     * @param activity The activity to decrease capacity for
     */
    public void decreaseActivityCapacity(Activity activity) {
        for (Activity a : activities) {
            if (a.equals(activity)) {
                a.decreaseCapacity();
            }
        }
    }

    /**
     * Returns the name of the destination.
     *
     * @return The destination name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a list of activities offered at this destination.
     *
     * @return The list of activities
     */
    public List<Activity> getActivities() {
        return activities;
    }

    /**
     * Provides a formatted string with details about the destination and its activities.
     *
     * @return A formatted string with destination and activity details
     */
    public String getActivityDetails() {
        StringBuilder details = new StringBuilder();
        for (Activity activity : activities) {
            details.append("- ")
                    .append(activity.getName())
                    .append(" at ")
                    .append(name)
                    .append(" (Capacity: ")
                    .append(activity.getCapacity())
                    .append(", Available Spaces: ")
                    .append(activity.getCapacity())
                    .append(", Cost: ")
                    .append(activity.getCost())
                    .append(")\n");
        }
        return details.toString();
    }
}
