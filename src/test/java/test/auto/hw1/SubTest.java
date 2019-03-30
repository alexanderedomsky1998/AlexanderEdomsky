package test.auto.hw1;

import com.epam.tat.module4.Calculator;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;

public class SubTest {
    private final Calculator calculator = new Calculator();

    @Test(groups = "SubSum")
    @Parameters({"value1", "value2"})
    public void TestLongSub(long a, long b)
    {
        assertEquals(a - b, calculator.sub(a ,b));
    }

    @Test(groups = "SubSum")
    @Parameters({"value1", "value2"})
    public void TestDoubleSub(double a, double b)
    {
        assertEquals(a - b, calculator.sub(a,b),0.001);
    }
}
