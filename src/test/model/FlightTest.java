package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//Unit Tests for class Flight

class FlightTest {
    Flight flight;

    @BeforeEach
    public void setUp() {
        flight = new Flight("EK3211", 300);
    }

    @Test
    public void getFlightNumberTest() {
        assertEquals(flight.getFlightNumber(), "EK3211");
    }

    @Test
    public void getFlightCapacityTest() {
        assertEquals(flight.getCapacity(), 300);
    }

    @Test
    public void isActiveTest() {
        assertFalse(flight.isActive());
        flight.scheduleFlight("Dubai", "Vancouver",
                "20:00", "08:00",
                "14/02/2022", "15/02/2022");
        assertTrue(flight.isActive());
    }

    @Test
    public void isFlyingTest() {
        assertFalse(flight.isFlying());
        flight.scheduleFlight("Dubai", "Vancouver",
                "20:00", "08:00",
                "14/02/2022", "15/02/2022");
        flight.fly();
        assertTrue(flight.isFlying());
        flight.land();
        assertFalse(flight.isFlying());
    }

    @Test
    public void scheduleFlightTest() {
        assertFalse(flight.isActive());
        flight.scheduleFlight("Dubai", "Vancouver",
                "20:00", "08:00",
                "14/02/2022", "15/02/2022");
        assertTrue(flight.isActive());
        assertEquals(flight.getDestinationFrom(), "Dubai");
        assertEquals(flight.getDestinationTo(), "Vancouver");
        assertEquals(flight.getDepartureTime(), "20:00");
        assertEquals(flight.getArrivalTime(), "08:00");
        assertEquals(flight.getDepartureDate(), "14/02/2022");
        assertEquals(flight.getArrivalDate(), "15/02/2022");
    }

    @Test
    public void getDestinationFromTest() {
        flight.scheduleFlight("Dubai", "Vancouver",
                "20:00", "08:00",
                "14/02/2022", "15/02/2022");
        assertEquals(flight.getDestinationFrom(), "Dubai");
    }

    @Test
    public void getDestinationToTest() {
        flight.scheduleFlight("Dubai", "Vancouver",
                "20:00", "08:00",
                "14/02/2022", "15/02/2022");
        assertEquals(flight.getDestinationTo(), "Vancouver");
    }

    @Test
    public void getDepartureTimeTest() {
        flight.scheduleFlight("Dubai", "Vancouver",
                "20:00", "08:00",
                "14/02/2022", "15/02/2022");
        assertEquals(flight.getDepartureTime(), "20:00");
    }

    @Test
    public void getArrivalTimeTest() {
        flight.scheduleFlight("Dubai", "Vancouver",
                "20:00", "08:00",
                "14/02/2022", "15/02/2022");
        assertEquals(flight.getArrivalTime(), "08:00");
    }

    @Test
    public void getDepartureDateTest() {
        flight.scheduleFlight("Dubai", "Vancouver",
                "20:00", "08:00",
                "14/02/2022", "15/02/2022");
        assertEquals(flight.getDepartureDate(), "14/02/2022");
    }

    @Test
    public void getArrivalDateTest() {
        flight.scheduleFlight("Dubai", "Vancouver",
                "20:00", "08:00",
                "14/02/2022", "15/02/2022");
        assertEquals(flight.getArrivalDate(), "15/02/2022");
    }

    @Test
    public void setOccupancyTest() {
        flight.scheduleFlight("Dubai", "Vancouver",
                "20:00", "08:00",
                "14/02/2022", "15/02/2022");
        assertEquals(flight.getOccupancy(), 0);
        flight.setOccupancy(150);
        assertEquals(flight.getOccupancy(), 150);
    }

    @Test
    public void getOccupancyTest() {
        flight.scheduleFlight("Dubai", "Vancouver",
                "20:00", "08:00",
                "14/02/2022", "15/02/2022");
        assertEquals(flight.getOccupancy(), 0);
        flight.setOccupancy(200);
        assertEquals(flight.getOccupancy(), 200);
    }

    @Test
    public void flyTest() {
        flight.scheduleFlight("Dubai", "Vancouver",
                "20:00", "08:00",
                "14/02/2022", "15/02/2022");
        assertFalse(flight.isFlying());
        flight.fly();
        assertTrue(flight.isFlying());
    }

    @Test
    public void landTest() {
        flight.scheduleFlight("Dubai", "Vancouver",
                "20:00", "08:00",
                "14/02/2022", "15/02/2022");
        assertFalse(flight.isFlying());
        flight.fly();
        assertTrue(flight.isFlying());
        flight.land();
        assertFalse(flight.isFlying());
    }

    @Test
    public void deactivateFlightTest() {
        flight.scheduleFlight("Dubai", "Vancouver",
                "20:00", "08:00",
                "14/02/2022", "15/02/2022");
        assertTrue(flight.isActive());
        flight.fly();
        flight.setOccupancy(125);
        assertEquals(flight.getDestinationFrom(), "Dubai");

        flight.deactivateFlight();
        assertFalse(flight.isActive());
        assertFalse(flight.isFlying());
        assertEquals(flight.getOccupancy(), 0);
        assertEquals(flight.getDestinationFrom(), "");
    }

    @Test
    public void toStringTest() {
        assertFalse(flight.isActive());
        assertEquals(flight.getFlightNumber()+"\t \tINACTIVE!", flight.toString());
        flight.scheduleFlight("Dubai", "Vancouver",
                "20:00", "08:00",
                "14/02/2022", "15/02/2022");
        assertTrue(flight.isActive());
        assertEquals(flight.getFlightNumber()+"\t \tACTIVE!", flight.toString());
    }
}
