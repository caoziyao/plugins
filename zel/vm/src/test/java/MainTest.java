import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class MainTest extends TestCase {


    @Before
    public void setup() {
        System.out.println("setup");
    }

    @Test
    public void testA() {
        System.out.println("testa");
    }
}