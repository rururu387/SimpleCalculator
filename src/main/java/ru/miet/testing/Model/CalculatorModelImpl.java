package ru.miet.testing.Model;

public class CalculatorModelImpl implements CalculatorModel
{
    static CalculatorModelImpl instance = null;

    CalculatorModelImpl()
    {}

    public static CalculatorModel getInstance()
    {
        if (instance == null) {
            instance = new CalculatorModelImpl();
        }
        return instance;
    }

    @Override
    public double sum(double a, double b)
    {
        return a + b;
    }

    @Override
    public double subtract(double a, double b)
    {
        return a - b;
    }

    @Override
    public double multiply(double a, double b)
    {
        return a * b;
    }

    @Override
    public double divide(double a, double b) throws IllegalArgumentException
    {
        if (b < 0.000001 && b > -0.000001)
        {
            throw new IllegalArgumentException("Division by zero!");
        }

        return a / b;
    }
}
