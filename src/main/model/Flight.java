package model;

import org.json.JSONObject;
import persistence.Writable;

//Represents the Flight class
public class Flight implements Writable {

    private final String flightNumber;
    private final int capacity;
    private boolean active;
    private String departureTime;
    private String arrivalTime;
    private String departureDate;
    private String arrivalDate;
    private String destinationFrom;
    private String destinationTo;
    private int occupancy;
    private boolean flying;

    /*
    EFFECTS: Creates a new flight with a flight number and a capacity, which is
                inactive (not scheduled), unoccupied and not flying
     */
    public Flight(String flightNumber, int capacity) {
        this.flightNumber = flightNumber;
        this.capacity = capacity;
        active = false;
        destinationFrom = "";
        destinationTo = "";
        departureTime = "";
        arrivalTime = "";
        departureDate = "";
        arrivalDate = "";
        occupancy = 0;
        flying = false;

    }

    /*
    EFFECTS: returns the flight number
     */
    public String getFlightNumber() {
        return flightNumber;
    }

    /*
    EFFECTS: returns the flight's capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /*
    EFFECTS: returns if the flight is active (scheduled) or inactive
     */
    public boolean isActive() {
        return active;
    }

    /*
    EFFECTS: returns if the flight is flying
     */
    public boolean isFlying() {
        return flying;
    }

    /*
    MODIFIES: this
    EFFECTS: Activates and schedules a flight
     */
    public void scheduleFlight(String destinationFrom, String destinationTo,
                               String departureTime, String arrivalTime,
                               String departureDate, String arrivalDate) {
        active = true;
        setDestinationFrom(destinationFrom);
        setDestinationTo(destinationTo);
        setDepartureTime(departureTime);
        setArrivalTime(arrivalTime);
        setDepartureDate(departureDate);
        setArrivalDate(arrivalDate);

    }

    /*
    MODIFIES: this
    EFFECTS: sets the destination from where the flight will depart
     */
    private void setDestinationFrom(String destinationFrom) {
        this.destinationFrom = destinationFrom;
    }

    /*
    MODIFIES: this
    EFFECTS: sets the destination where the flight will arrive
     */
    private void setDestinationTo(String destinationTo) {
        this.destinationTo = destinationTo;
    }

    /*
    MODIFIES: this
    EFFECTS: sets the time when the flight will depart
     */
    private void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    /*
    MODIFIES: this
    EFFECTS: sets the time when the flight will arrive
     */
    private void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    /*
    MODIFIES: this
    EFFECTS: sets the date when the flight will depart
     */
    private void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    /*
    MODIFIES: this
    EFFECTS: sets the date when the flight will arrive
     */
    private void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }


    /*
    REQUIRES: The flight must be active (scheduled)
    EFFECTS: returns the place where the flight will depart from
     */
    public String getDestinationFrom() {
        return destinationFrom;
    }

    /*
    REQUIRES: The flight must be active (scheduled)
    EFFECTS: returns the place where the flight will arrive
     */
    public String getDestinationTo() {
        return destinationTo;
    }

    /*
    REQUIRES: The flight must be active (scheduled)
    EFFECTS: returns the time when the flight will depart
     */
    public String getDepartureTime() {
        return departureTime;
    }

    /*
    REQUIRES: The flight must be active (scheduled)
    EFFECTS: returns the time when the flight will arrive
     */
    public String getArrivalTime() {
        return arrivalTime;
    }

    /*
    REQUIRES: The flight must be active (scheduled)
    EFFECTS: returns the date when the flight will depart
     */
    public String getDepartureDate() {
        return departureDate;
    }

    /*
    REQUIRES: The flight must be active (scheduled)
    EFFECTS: returns the date when the flight will arrive
     */
    public String getArrivalDate() {
        return arrivalDate;
    }

    /*
    REQUIRES: the flight must be active (scheduled), occupancy must be less than capacity
    MODIFIES: this
    EFFECTS: Sets the current occupancy of the flight
     */
    public void setOccupancy(int occupancy) {
        this.occupancy = occupancy;
    }

    /*
    EFFECTS: returns the current occupancy of the flight
     */
    public int getOccupancy() {
        return occupancy;
    }

    /*
    REQUIRES: the flight must be active (scheduled)
    MODIFIES: this
    EFFECTS: makes the flight fly
     */
    public void fly() {
        flying = true;
    }

    /*
    MODIFIES: this
    EFFECTS: makes the flight land if it was flying, otherwise does nothing
     */
    public void land() {
        flying = false;
    }

    /*
    MODIFIES: this
    EFFECTS: if the flight is inactive already, does nothing, else deactivates it:
                lands the flight if it's flying, deactivates and unschedules the flight,
                and makes it unoccupied
     */
    public void deactivateFlight() {
        land();
        active = false;
        destinationFrom = "";
        destinationTo = "";
        departureTime = "";
        arrivalTime = "";
        departureDate = "";
        arrivalDate = "";
        occupancy = 0;
    }

    /*
    MODIFIES: this
    EFFECTS: converts the Flight to a JSONObject
     */
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Flight number", flightNumber);
        json.put("Capacity", capacity);
        json.put("Active status", active);
        json.put("Departure destination", destinationFrom);
        json.put("Arrival destination", destinationTo);
        json.put("Departure time", departureTime);
        json.put("Arrival time", arrivalTime);
        json.put("Departure date", departureDate);
        json.put("Arrival date", arrivalDate);
        json.put("Occupancy", occupancy);
        json.put("Flying", flying);
        return json;
    }

    /*
    EFFECTS: converts the Flight to a String for its representation in the Menu GUI
     */
    @Override
    public String toString() {
        String flightString;
        if (active) {
            flightString = flightNumber + "\t \t" + "ACTIVE!";
        } else {
            flightString = flightNumber + "\t \t" + "INACTIVE!";
        }
        return flightString;
    }
}

