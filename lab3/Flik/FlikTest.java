import org.junit.Test;
import static org.junit.Assert.*;

public class FlikTest {

    @Test
    public void testisSameNumber() {
        int a = 128;
        int b = 128;
        int c = 500;
        boolean actual1 = Flik.isSameNumber(a, b);
        boolean actual2 = Flik.isSameNumber(a, c);

        assertTrue(actual1);
        assertFalse(actual2);

    }
}
