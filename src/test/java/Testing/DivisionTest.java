package Testing;

import com.epam.tat.module4.Calculator;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class DivisionTest {
    private final Calculator calculator = new Calculator();

    @Test(groups = "MultDiv")
    @Parameters({"value1", "value2"})
    public void TestLongSum(long a, long b)
    {
        Assert.assertEquals(a/b,calculator.div(a,b));
    }

    @Test(groups = "MultDiv")
    @Parameters({"value1", "value2"})
    public void TestDoubleSum(double a, double b)
    {
        Assert.assertEquals(a/b, calculator.div(a,b),0.1);
    }
}
