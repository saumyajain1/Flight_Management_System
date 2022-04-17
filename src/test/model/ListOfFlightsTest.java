package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//Unit Tests for class ListOfFlights

public class ListOfFlightsTest {
    ListOfFlights listOfFlights;
    Flight flight1;
    Flight flight2;
    Flight flight3;
    Flight flight4;

    @BeforeEach
    public void setUp() {
        listOfFlights = new ListOfFlights();
        flight1 = new Flight("EK3121", 300);
        flight2 = new Flight("QA1797", 200);
        flight3 = new Flight("AI1525", 250);
        flight4 = new Flight("EK7777", 450);
    }

    @Test
    public void sizeTest() {
        assertEquals(listOfFlights.size(), 0);
        listOfFlights.addFlight(flight1);
        listOfFlights.addFlight(flight2);
        listOfFlights.addFlight(flight3);
        assertEquals(listOfFlights.size(), 3);
    }

    @Test
    public void addFlightTest() {
        assertEquals(listOfFlights.size(), 0);
        assertFalse(listOfFlights.containsFlight(flight1));
        listOfFlights.addFlight(flight1);
        assertEquals(listOfFlights.size(), 1);
        assertTrue(listOfFlights.containsFlight(flight1));

        assertEquals(listOfFlights.size(), 1);
        assertFalse(listOfFlights.containsFlight(flight2));
        listOfFlights.addFlight(flight2);
        assertEquals(listOfFlights.size(), 2);
        assertTrue(listOfFlights.containsFlight(flight2));
    }

    @Test
    public void removeFlightTest() {
        assertEquals(listOfFlights.size(), 0);
        listOfFlights.removeFlight(flight2);
        assertEquals(listOfFlights.size(), 0);

        listOfFlights.addFlight(flight2);
        assertTrue(listOfFlights.containsFlight(flight2));
        assertEquals(listOfFlights.size(), 1);
        listOfFlights.removeFlight(flight2);
        assertFalse(listOfFlights.containsFlight(flight2));
        assertEquals(listOfFlights.size(), 0);

        listOfFlights.addFlight(flight1);
        listOfFlights.addFlight(flight2);
        listOfFlights.addFlight(flight3);
        assertTrue(listOfFlights.containsFlight(flight2));
        assertEquals(listOfFlights.size(), 3);
        listOfFlights.removeFlight(flight2);
        assertFalse(listOfFlights.containsFlight(flight2));
        assertEquals(listOfFlights.size(), 2);
    }

    @Test
    public void getFlightTest() {
        listOfFlights.addFlight(flight1);
        listOfFlights.addFlight(flight2);
        listOfFlights.addFlight(flight3);
        assertEquals(listOfFlights.getFlight(1), flight1);
        assertEquals(listOfFlights.getFlight(2), flight2);
        assertEquals(listOfFlights.getFlight(3), flight3);
    }

    @Test
    public void containsFlightTest() {
        assertFalse(listOfFlights.containsFlight(flight2));
        listOfFlights.addFlight(flight1);
        listOfFlights.addFlight(flight2);
        listOfFlights.addFlight(flight3);
        assertTrue(listOfFlights.containsFlight(flight2));
    }
}
