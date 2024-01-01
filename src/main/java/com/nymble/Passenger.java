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
 * Enumerates the available passenger types.
 */
enum PassengerType {
    /**
     * Represents a normal passenger with no special discounts or privileges.
     */
    NORMAL,

    /**
     * Represents a silver passenger with a 10% discount on activities.
     */
    SILVER,

    /**
     * Represents a gold passenger with unlimited access to activities at no cost.
     */
    GOLD
}

/**
 * Abstract base class for passengers, defining common properties and behaviors.
 */
abstract class Passenger {
    /**
     * A counter to generate unique passenger numbers.
     */
    static int idCount = 0;

    /**
     * The passenger's name.
     */
    private String name;

    /**
     * A unique identifier for the passenger.
     */
    private int passengerNumber;

    /**
     * The passenger's current balance.
     */
    private double balance;

    /**
     * A list of activities the passenger has signed up for.
     */
    private List<Activity> signedUpActivities;

    /**
     * The passenger's type (normal, silver, or gold).
     */
    private PassengerType type;

    /**
     * Constructs a new Passenger object with the specified information.
     *
     * @param name The passenger's name
     * @param balance The passenger's initial balance
     * @param type The passenger's type
     */
    public Passenger(String name, double balance, PassengerType type) {
        this.name = name;
        this.passengerNumber = idCount++;
        this.balance = balance;
        this.signedUpActivities = new ArrayList<>();
        this.type = type;
    }

    /**
     * Signs the passenger up for an activity, handling different behaviors based on passenger type.
     *
     * @param activity The activity to sign up for
     */
    public abstract void signUpForActivity(Activity activity);

    /**
     * Checks if the passenger can sign up for an activity based on their balance and the activity's cost.
     *
     * @param cost The cost of the activity
     * @return true if the passenger can sign up, false otherwise
     */
    public abstract boolean canSignUp(int cost);

    /**
     * Returns the passenger's name.
     *
     * @return The passenger's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the passenger's unique identifier.
     *
     * @return The passenger's number
     */
    public int getPassengerNumber() {
        return passengerNumber;
    }

    /**
     * Returns the passenger's current balance.
     *
     * @return The passenger's balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Returns a list of activities the passenger has signed up for.
     *
     * @return The list of signed-up activities
     */
    public List<Activity> getSignedUpActivities() {
        return signedUpActivities;
    }

    /**
     * Returns the passenger's type (normal, silver, or gold).
     *
     * @return The passenger's type
     */
    public PassengerType getType() {
        return type;
    }

    /**
     * Adds an activity to the passenger's list of signed-up activities.
     *
     * @param a The activity to add
     */
    protected void addToSignUpActivities(Activity a) {
        this.signedUpActivities.add(a);
    }

    /**
     * Sets the passenger's current balance.
     *
     * @param balance The new balance
     */
    protected void setBalance(double balance) {
    this.balance = balance;
    }
}

// GoldPassenger class
class PremiumPassenger extends Passenger {
    /**
     * Constructs a new GoldPassenger object with the specified name and balance.
     *
     * @param name The passenger's name
     * @param balance The passenger's initial balance
     */
    public PremiumPassenger(String name, double balance) {
        super(name, balance, PassengerType.GOLD);
    }

    /**
     * {@inheritDoc}
     *
     * Always returns true for gold passengers, as they have unlimited access to activities.
     */
    @Override
    public boolean canSignUp(int cost) {
        return true;
    }

    /**
     * {@inheritDoc}
     *
     * Signs up the gold passenger for the activity without deducting any cost,
     * and decreases the activity's capacity if available.
     */
    @Override
    public void signUpForActivity(Activity activity) {
        if (activity.getCapacity() > 0) {
            activity.getDestination().decreaseActivityCapacity(activity);
            addToSignUpActivities(activity);
            System.out.println(getName() + " (" + getType() + ") signed up for " + activity.getName() +
                    " at " + activity.getDestination().getName() +
                    " for a discounted cost of " + 0);
        } else {
            System.out.println("Unable to sign up for activity. Activity is full.");
        }
    }
}

// SilverPassenger class
class GoldPassenger extends Passenger {
    /**
     * Constructs a new SilverPassenger object with the specified name and balance.
     *
     * @param name The passenger's name
     * @param balance The passenger's initial balance
     */
    public GoldPassenger(String name, double balance) {
        super(name, balance, PassengerType.SILVER);
    }

    /**
     * {@inheritDoc}
     *
     * Checks if the silver passenger can sign up for an activity based on their balance and
     * the discounted cost (10% off).
     */
    @Override
    public boolean canSignUp(int cost) {
        return cost * 0.9 >= this.getBalance();
    }

    /**
     * {@inheritDoc}
     *
     * Signs up the silver passenger for the activity, deducting the discounted cost from their balance,
     * and decreases the activity's capacity if available.
     */
    @Override
    public void signUpForActivity(Activity activity) {
        double cost = 0.9 * activity.getCost();

        if (getBalance() >= cost && activity.getCapacity() > 0) {
            setBalance(getBalance() - cost);
            activity.getDestination().decreaseActivityCapacity(activity);
            addToSignUpActivities(activity);
            System.out.println(getName() + " (" + getType() + ") signed up for " + activity.getName() +
                    " at " + activity.getDestination().getName() +
                    " for a cost of " + cost);
        } else {
            System.out.println("Unable to sign up for activity. Insufficient balance or activity is full.");
        }
    }
}

// NormalPassenger class
class NormalPassenger extends Passenger {
    /**
     * Constructs a new NormalPassenger object with the specified name and balance.
     *
     * @param name The passenger's name
     * @param balance The passenger's initial balance
     */
    public NormalPassenger(String name, double balance) {
        super(name, balance, PassengerType.NORMAL);
    }

    /**
     * {@inheritDoc}
     *
     * Checks if the normal passenger can sign up for an activity based on their balance and the full cost.
     */
    @Override
    public boolean canSignUp(int cost) {
        return cost >= this.getBalance();
    }

    /**
     * {@inheritDoc}
     *
     * Signs up the normal passenger for the activity, deducting the full cost from their balance,
    * and decreases the activity's capacity if available.
    */
    @Override
    public void signUpForActivity(Activity activity) {
        if (getBalance() >= activity.getCost() && activity.getCapacity() > 0) {
            setBalance(getBalance() - activity.getCost());
            activity.getDestination().decreaseActivityCapacity(activity);
            addToSignUpActivities(activity);
            System.out.println(getName() + " (" + getType() + ") signed up for " + activity.getName() +
                    " at " + activity.getDestination().getName() +
                    " for a cost of " + activity.getCost());
        } else {
            System.out.println("Unable to sign up for activity. Insufficient balance or activity is full.");
        }
    }
}
