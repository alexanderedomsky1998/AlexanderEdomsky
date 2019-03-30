package test.auto.hw1;

import com.epam.tat.module4.Calculator;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SumTest {

    private final Calculator calculator = new Calculator();

    @Test(groups = "SubSum")
    @Parameters({"value1", "value2"})
    public void TestLongSum(long a, long b)
    {
        Assert.assertEquals(a + b,calculator.sum(a,b));
    }

    @Test(groups = "SubSum")
    @Parameters({"value1", "value2"})
    public void TestDoubleSum(double a, double b)
    {
        Assert.assertEquals(a + b, calculator.sum(a,b),0.1);
    }
}
