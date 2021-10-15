import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.miet.testing.Model.CalculatorModel;
import ru.miet.testing.Model.CalculatorModelImpl;

public class CalculatorModelTest
{
    CalculatorModel model;

    CalculatorModelTest()
    {
        model = CalculatorModelImpl.getInstance();
    }

    @Test
    void sumTest1Basic()
    {
        Assertions.assertEquals(3309.7, model.sum(194.2, 3115.5));
    }

    @Test
    void sumTest2NegativeParam1()
    {
        Assertions.assertEquals(-4.3, model.sum(-5.3, 1));
    }

    @Test
    void sumTest3NegativeParam2()
    {
        Assertions.assertEquals(4, model.sum(5.2, -1.2));
    }

    @Test
    void sumTest4NegativeParams()
    {
        Assertions.assertEquals(-93341.1, model.sum(-321, -93020.1));
    }

    @Test
    void sumTest5Zeros()
    {
        Assertions.assertEquals(0, model.sum(0, 0));
    }

    @Test
    void subtractTest1Basic()
    {
        Assertions.assertEquals(99.225252, model.subtract(103.58203, 4.356778));
    }

    @Test
    void subtractTest2NegativeParam1()
    {
        Assertions.assertEquals(-189.89, model.subtract(-156, 33.890));
    }

    @Test
    void subtractTest3NegativeParam2()
    {
        Assertions.assertEquals(421.24, model.subtract(332.34, -88.9));
    }

    @Test
    void subtractTest4NegativeParams()
    {
        Assertions.assertEquals(54226.4214, model.subtract(-653.1275, -54879.5489));
    }

    @Test
    void subtractTest5Zeros()
    {
        Assertions.assertEquals(0, model.subtract(0, 0));
    }

    @Test
    void multiplyTest1Basic()
    {
        Assertions.assertEquals(698.2956, model.multiply(15.77,44.28));
    }

    @Test
    void multiplyTest2NegativeParam1()
    {
        Assertions.assertEquals(-906419.0140508, model.multiply(-5902.349,153.5692));
    }

    @Test
    void multiplyTest3NegativeParam2()
    {
        Assertions.assertEquals(-7466419998.164961, model.multiply(3489.219,-2139854.219));
    }

    @Test
    void multiplyTest4NegativeParams()
    {
        Assertions.assertEquals(230653085.0866, model.multiply(-47821.4,-4823.219));
    }

    @Test
    void multiplyTest5ZeroParam1()
    {
        Assertions.assertEquals(0, model.multiply(0, 3495.492));
    }

    @Test
    void multiplyTest6ZeroParam2()
    {
        Assertions.assertEquals(0, model.multiply(3789.5490, 0));
    }

    @Test
    void divideTest1Basic()
    {
        Assertions.assertEquals(0.008805108, model.divide(7864.89, 893219), 0.000000001);
    }

    @Test
    void divideTest2NegativeParam1()
    {
        Assertions.assertEquals(-108.198473244, model.divide(-897234, 8292.483), 0.000000001);
    }

    @Test
    void divideTest3NegativeParam2()
    {
        Assertions.assertEquals(-108.198473244, model.divide(897234, -8292.483), 0.000000001);
    }

    @Test
    void divideTest4NegativeParams()
    {
        Assertions.assertEquals(10.853684235, model.divide(-930193.3,-85703), 0.000000001);
    }

    @Test
    void divideTest5ZeroParam1()
    {
        Assertions.assertEquals(0, model.divide(0, 38734.238));
    }

    @Test
    void divideTest6ZeroParam2()
    {
        Assertions.assertThrows(IllegalArgumentException.class, () -> { model.divide(1, 0); });
    }

    @Test
    void divideTest7ZeroParams()
    {
        Assertions.assertThrows(IllegalArgumentException.class, () -> { model.divide(0, 0); });
    }
}
