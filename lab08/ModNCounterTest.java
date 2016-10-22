import static org.junit.Assert.*;

/**
 * Created by rober on 7/2/2016.
 */
public class ModNCounterTest {

    @org.junit.Test
    public void testConstructor() {
        int n = 9;
        ModNCounter c = new ModNCounter(n);
        assertTrue(c.value() == n);
    }

    @org.junit.Test
    public void testIncrement() throws Exception {
        int n = 9;
        ModNCounter c = new ModNCounter(n);
        for(int i = 0; i < n + 1; i++) {
            c.increment();
            assertTrue(c.value_()  == ((i+1)%n));
        }
    }

    @org.junit.Test
    public void testReset() throws Exception {
        int n = 9;
        ModNCounter c = new ModNCounter(n);
        c.increment();
        c.reset();
        assertTrue(c.value_() == 0);
    }
}