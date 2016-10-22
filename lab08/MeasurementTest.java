import org.junit.*;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by rober on 7/2/2016.
 */
public class MeasurementTest {

    @org.junit.Test
    public void getFeet() throws Exception {
        Measurement a = new Measurement();
        assertTrue(a.getFeet() == 0);
        Measurement b = new Measurement(10);
        assertTrue(b.getFeet() == 10);
        Measurement c = new Measurement(10,20);
        assertTrue(c.getFeet() == 10);
    }

    @org.junit.Test
    public void getInches() throws Exception {
        Measurement a = new Measurement();
        assertTrue(a.getInches() == 0);
        Measurement b = new Measurement(10);
        assertTrue(b.getInches() == 0);
        Measurement c = new Measurement(10,20);
        assertTrue(c.getInches() == 20);
    }

    @org.junit.Test
    public void plus() throws Exception {
        Measurement b = new Measurement(5,6);
        Measurement c = new Measurement(10,20);
        Measurement a = b.plus(c);
        assertTrue(a.getFeet() == 15);
        assertTrue(a.getInches() == 26);
    }

    @org.junit.Test
    public void minus() throws Exception {
        Measurement b = new Measurement(15,26);
        Measurement c = new Measurement(10,20);
        Measurement a = b.minus(c);
        assertTrue(a.getFeet() == 5);
        assertTrue(a.getInches() == 6);
    }

    @org.junit.Test
    public void multiple() throws Exception {
        Measurement b = new Measurement(1,6);
        Measurement c=b.multiple(3);
        assertTrue(c.getFeet() == 4);
        assertTrue(c.getInches() == 6);
    }

    @org.junit.Test
    public void testtoString() throws Exception {
        Measurement b = new Measurement(1,6);
        assertTrue(b.toString().equals("1\'6\""));
    }

}