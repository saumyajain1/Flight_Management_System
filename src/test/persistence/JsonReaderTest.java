
package persistence;

/*
Reference:
CPSC 210 JsonSerializationDemo (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git)
*/

import model.Flight;
import model.ListOfFlights;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//Unit tests for JsonReader

class JsonReaderTest extends JsonTest{

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ListOfFlights listOfFlights = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        /*
        try {
            JsonWriter writer = new JsonWriter("./data/testReaderEmptyListOfFlights.json");
            writer.open();
            writer.write(new ListOfFlights());
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        */
        JsonReader reader = new JsonReader("./data/testReaderEmptyListOfFlights.json");
        try {
            ListOfFlights listOfFlights = reader.read();
            assertEquals(0, listOfFlights.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralListOfFlights.json");
        try {
            ListOfFlights listOfFlights = reader.read();

            List<Flight> listOfFlightsNew = listOfFlights.getListOfFlights();
            assertEquals(4, listOfFlightsNew.size());
            compareFlights(flight1, listOfFlightsNew.get(0));
            compareFlights(flight2, listOfFlightsNew.get(1));
            compareFlights(flight3, listOfFlightsNew.get(2));
            compareFlights(flight4, listOfFlightsNew.get(3));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
