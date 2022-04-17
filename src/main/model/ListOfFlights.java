package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Represents the Class for list of flights
public class ListOfFlights implements Writable {

    ArrayList<Flight> listOfFlights;

    /*
    EFFECTS: Creates a new list of flights
     */
    public ListOfFlights() {
        listOfFlights = new ArrayList<>();
    }

    /*
    EFFECTS: returns the size of the list of flights
     */
    public int size() {
        return listOfFlights.size();
    }

    /*
    REQUIRES:A flight with this name must not be already present in the list
    MODIFIES: this
    EFFECTS: adds given flight to the list of flights; logs this event
     */
    public void addFlight(Flight flight) {
        listOfFlights.add(flight);
        EventLog.getInstance().logEvent(new Event("The Flight: " + flight.getFlightNumber()
                 + " has been added to the list of flights"));
    }

    /*
    MODIFIES: this
    EFFECTS: removes given flight from the list of flights if already present, otherwise does nothing; logs this event
     */
    public void removeFlight(Flight flight) {
        if (containsFlight(flight)) {
            listOfFlights.remove(flight);
            EventLog.getInstance().logEvent(new Event("The Flight: " + flight.getFlightNumber()
                    + " has been removed from the list of flights"));
        }
    }

    /*
    REQUIRES: pos must be less than or equal to the size of the list,
                       Cannot be called if list is empty
    EFFECTS: returns the flight at given position (index+1)
     */
    public Flight getFlight(int pos) {
        return listOfFlights.get(pos - 1);
    }

    /*
    EFFECTS: Checks if the list contains that flight
     */
    public boolean containsFlight(Flight flight) {
        return listOfFlights.contains(flight);
    }

    // EFFECTS: returns an unmodifiable list of flights in this list of flights
    public List<Flight> getListOfFlights() {
        return Collections.unmodifiableList(listOfFlights);
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("List Of Flights", listOfFlightsToJson());
        return json;
    }

    // EFFECTS: returns Flights in this list of flights as a JSON array
    private JSONArray listOfFlightsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Flight f : getListOfFlights()) {
            jsonArray.put(f.toJson());
        }

        return jsonArray;
    }
}
