
package persistence;

/*
Reference:
CPSC 210 JsonSerializationDemo (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git)
*/

import model.Event;
import model.EventLog;
import model.ListOfFlights;
import model.Flight;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }


    // EFFECTS: reads list of flights from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ListOfFlights read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseListOfFlights(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }


    // EFFECTS: parses workroom from JSON object and returns it
    private ListOfFlights parseListOfFlights(JSONObject jsonObject) {
        ListOfFlights listOfFlights = new ListOfFlights();
        addFlights(listOfFlights, jsonObject);
        return listOfFlights;
    }

    // MODIFIES: listOfFlights
    // EFFECTS: parses flights from JSON object and adds them to list of flights
    private void addFlights(ListOfFlights listOfFlights, JSONObject jsonObject) {
        EventLog.getInstance().logEvent(new Event("Loading from file..."));
        JSONArray jsonArray = jsonObject.getJSONArray("List Of Flights");
        for (Object json : jsonArray) {
            JSONObject nextFlight = (JSONObject) json;
            addFlight(listOfFlights, nextFlight);
        }
        EventLog.getInstance().logEvent(new Event("Loading finished."));
    }

    // MODIFIES: listOfFlights
    // EFFECTS: parses one flight from JSON object and adds it to list of flights
    private void addFlight(ListOfFlights listOfFlights, JSONObject jsonObject) {

        String flightNumber = jsonObject.getString("Flight number");
        int capacity = jsonObject.getInt("Capacity");
        boolean active = jsonObject.getBoolean("Active status");
        String destinationFrom = jsonObject.getString("Departure destination");
        String destinationTo = jsonObject.getString("Arrival destination");
        String departureTime = jsonObject.getString("Departure time");
        String arrivalTime = jsonObject.getString("Arrival time");
        String departureDate = jsonObject.getString("Departure date");
        String arrivalDate = jsonObject.getString("Arrival date");
        int occupancy = jsonObject.getInt("Occupancy");
        boolean flying = jsonObject.getBoolean("Flying");

        Flight flight = new Flight(flightNumber, capacity);
        if (active) {
            flight.scheduleFlight(destinationFrom, destinationTo, departureTime, arrivalTime,
                    departureDate, arrivalDate);
            flight.setOccupancy(occupancy);
            if (flying) {
                flight.fly();
            }
        }

        listOfFlights.addFlight(flight);

    }
}


