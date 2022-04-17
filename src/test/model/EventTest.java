package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Event class
 * Reference: https://github.students.cs.ubc.ca/CPSC210/AlarmSystem.git
 */
public class EventTest {
	private Event e;
	private Date d;
    private Event e2;
	
	//NOTE: these tests might fail if time at which line (2) below is executed
	//is different from time that line (1) is executed.  Lines (1) and (2) must
	//run in same millisecond for this test to make sense and pass. The same is true for lines (3) and (4)
	
	@BeforeEach
	public void runBefore() {
		e = new Event("Added flight");   // (1)
		d = Calendar.getInstance().getTime();   // (2)
        e2 = new Event("Removed flight");
    }
	
	@Test
	public void testEvent() {
		assertEquals("Added flight", e.getDescription());
		assertEquals(d, e.getDate());
	}

	@Test
	public void testToString() {
		assertEquals(d.toString() + "\n" + "Added flight", e.toString());
	}

    @Test
    public void equalsTest() {

        assertEquals(e, e);
        assertNotEquals(e, e2);
        assertFalse(e.equals(null));
        assertFalse(e.equals(new Flight("AI777", 200)));
        //assertFalse(e.equals(new Event("Removed flight")));
        //assertFalse(e.equals(new Event("Added flight")));
        //assertTrue(e.equals(e));
        //assertFalse(e.equals(e2));
        //assertFalse(e.equals(null));
        //assertFalse(e.equals(new Event("Removed flight")));
        //assertFalse(e.equals(new Event("Added flight")));
    }

    @Test
    public void hashcodeTest() {
        assertEquals(e.hashCode(), 13 * e.getDate().hashCode() + e.getDescription().hashCode());
        assertEquals(d, e.getDate());
    }
}
