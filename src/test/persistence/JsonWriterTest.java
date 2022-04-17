package persistence;

/*
Reference:
CPSC 210 JsonSerializationDemo (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git)
*/

import model.Flight;
import model.ListOfFlights;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//Unit tests for JsonWriter

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFileLocation() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyListOfFlights() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyListOfFlights.json");
            writer.open();
            writer.write(listOfFlights);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyListOfFlights.json");
            listOfFlights = reader.read();
            assertEquals(0, listOfFlights.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralListOfFlights() {

        listOfFlights.addFlight(flight1);
        listOfFlights.addFlight(flight2);
        listOfFlights.addFlight(flight3);
        listOfFlights.addFlight(flight4);

        try {
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralListOfFlights.json");
            writer.open();
            writer.write(listOfFlights);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralListOfFlights.json");
            listOfFlights = reader.read();
            List<Flight> listOfFlightsNew = listOfFlights.getListOfFlights();
            assertEquals(4, listOfFlightsNew.size());
            compareFlights(flight1, listOfFlightsNew.get(0));
            compareFlights(flight2, listOfFlightsNew.get(1));
            compareFlights(flight3, listOfFlightsNew.get(2));
            compareFlights(flight4, listOfFlightsNew.get(3));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}