package persistence;

import model.Flight;
import model.ListOfFlights;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Class representing JsonTest
public class JsonTest {

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
        flight3.scheduleFlight("Vancouver", "NYC",
                "22:00 UTC", "06:00 UTC",
                "01/03/2022", "02/03/2022");
        flight3.setOccupancy(60);
        flight3.fly();

        flight4 = new Flight("IA683", 400);
        flight4.scheduleFlight("LA", "Seattle",
                "12:00 UTC", "16:00 UTC",
                "01/03/2022", "01/03/2022");
    }

    public void compareFlights(Flight flight01, Flight flight02) {
        assertEquals(flight01.getFlightNumber(), flight02.getFlightNumber());
        assertEquals(flight01.getCapacity(), flight02.getCapacity());
        assertEquals(flight01.isActive(), flight02.isActive());
        assertEquals(flight01.getDestinationFrom(), flight02.getDestinationFrom());
        assertEquals(flight01.getDestinationTo(), flight02.getDestinationTo());
        assertEquals(flight01.getDepartureDate(), flight02.getDepartureDate());
        assertEquals(flight01.getArrivalDate(), flight02.getArrivalDate());
        assertEquals(flight01.getDepartureTime(), flight02.getDepartureTime());
        assertEquals(flight01.getArrivalTime(), flight02.getArrivalTime());
        assertEquals(flight01.getOccupancy(), flight02.getOccupancy());
        assertEquals(flight01.isFlying(), flight02.isFlying());
    }
}
