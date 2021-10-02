package ru.miet.testing.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import ru.miet.testing.Model.CalculatorModel;
import ru.miet.testing.Model.CalculatorModelImpl;
import ru.miet.testing.OperationType;
import ru.miet.testing.View.CalculatorView;
import ru.miet.testing.View.FXView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ResourceBundle;

public class FXController implements CalculatorPresenter, Initializable
{
    private CalculatorModel model;
    private CalculatorView view;
    private OperationType currentSelectedOperation;
    private double leftOperand;
    private double rightOperand;
    private int activeInputNum;

    public FXController()
    {
        model = CalculatorModelImpl.getInstance();
        activeInputNum = -1;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        view = FXView.initializeInstance(leftInput, rightInput);
        leftOperand = 0;
        rightOperand = 0;
        view.printResult(0, resultArea);
        currentSelectedOperation = OperationType.None;
    }

    @FXML
    private TextField leftInput;

    @FXML
    private TextField rightInput;

    @FXML
    private TextArea operationArea;

    @FXML
    private TextArea eqSignArea;

    @FXML
    private TextArea resultArea;

    @FXML
    private Button clear;

    @FXML
    private Button div;

    @FXML
    private Button eight;

    @FXML
    private Button equals;

    @FXML
    private Button five;

    @FXML
    private Button four;

    @FXML
    private Button minus;

    @FXML
    private Button mult;

    @FXML
    private Button nine;

    @FXML
    private Button one;

    @FXML
    private Button plus;

    @FXML
    private Button seven;

    @FXML
    private Button six;

    @FXML
    private Button three;

    @FXML
    private Button two;

    @FXML
    private Button zero;

    private void setCurrentSelectedOperation(OperationType operationType)
    {
        currentSelectedOperation = operationType;
        switch (operationType)
        {
            case None -> view.setOperation("", operationArea);
            case Sum -> view.setOperation("+", operationArea);
            case Subtract -> view.setOperation("-", operationArea);
            case Multiply -> view.setOperation("*", operationArea);
            case Divide -> view.setOperation("/", operationArea);
        }
    }

    private Method getFunctionByOperation(OperationType operationType) throws NoSuchMethodException
    {
        Method method = null;
        switch (operationType)
        {
            case None -> throw new IllegalArgumentException("Operation none cannot be applied");
            case Sum -> method = model.getClass().getDeclaredMethod("sum", double.class, double.class);

            case Subtract -> method = model.getClass().getDeclaredMethod("subtract", double.class, double.class);

            case Multiply -> method = model.getClass().getDeclaredMethod("multiply", double.class, double.class);

            case Divide -> method = model.getClass().getDeclaredMethod("divide", double.class, double.class);
        }

        method.setAccessible(true);

        return method;
    }

    public void setLeftOperand(double value)
    {
        leftOperand = value;
        view.setLeftOperand(String.valueOf(leftOperand), leftInput);
    }

    public void setRightOperand(double value)
    {
        rightOperand = value;
        view.setRightOperand(String.valueOf(rightOperand), leftInput);
    }

    @Override
    public void onPlusClicked()
    {
        setCurrentSelectedOperation(OperationType.Sum);
    }

    @Override
    public void onMinusClicked()
    {
        setCurrentSelectedOperation(OperationType.Subtract);
    }

    @Override
    public void onDivideClicked()
    {
        setCurrentSelectedOperation(OperationType.Divide);
    }

    @Override
    public void onMultiplyClicked()
    {
        setCurrentSelectedOperation(OperationType.Multiply);
    }

    @FXML
    void onClClicked(MouseEvent event)
    {

        setCurrentSelectedOperation(OperationType.None);
    }

    @FXML
    void onEqClicked(MouseEvent event)
    {
        Method method = null;
        try
        {
            method = getFunctionByOperation(currentSelectedOperation);
        }
        catch (Exception e)
        {
            view.displayError("Operation is not supported!");
            return;
        }

        double result = 0;

        try
        {
            result = (double) method.invoke(model, leftOperand, rightOperand);
        }
        catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e)
        {
            view.displayError("Internal error. How did you produce it?");
            return;
        }

        view.setResult(String.valueOf(result), resultArea);
    }

    void concatOperand(short num, short digit)
    {
        if (num == 0)
        {
            setLeftOperand(leftOperand * 10 + digit);
        }
        else if (num == 1)
        {
            setRightOperand(rightOperand * 10 + digit);
        }
    }

    @FXML
    void onZeroClicked(MouseEvent event)
    {
        concatOperand(view.addStrToActiveField("0"), (short) 0);
    }

    @FXML
    void onOneClicked(MouseEvent event)
    {
        concatOperand(view.addStrToActiveField("1"), (short) 1);
    }

    @FXML
    void onTwoClicked(MouseEvent event)
    {
        concatOperand(view.addStrToActiveField("2"), (short) 2);
    }

    @FXML
    void onThreeClicked(MouseEvent event)
    {
        concatOperand(view.addStrToActiveField("3"), (short) 3);
    }

    @FXML
    void onFourClicked(MouseEvent event)
    {
        concatOperand(view.addStrToActiveField("4"), (short) 4);
    }

    @FXML
    void onFiveClicked(MouseEvent event)
    {
        concatOperand(view.addStrToActiveField("5"), (short) 5);
    }

    @FXML
    void onSixClicked(MouseEvent event)
    {
        concatOperand(view.addStrToActiveField("6"), (short) 6);
    }

    @FXML
    void onSevenClicked(MouseEvent event)
    {
        concatOperand(view.addStrToActiveField("7"), (short) 7);
    }

    @FXML
    void onEightClicked(MouseEvent event)
    {
        concatOperand(view.addStrToActiveField("8"), (short) 8);
    }

    @FXML
    void onNineClicked(MouseEvent event)
    {
        concatOperand(view.addStrToActiveField("9"), (short) 9);
    }

    @FXML
    void onLeftInputChanged(InputMethodEvent event)
    {
        String leftStr = view.getLeftOperand(leftInput);
        try
        {
            leftOperand = Double.parseDouble(leftStr);
        }
        catch (NullPointerException | NumberFormatException e)
        {
            view.displayError("Number '" + leftStr + "' is not parsable!");
        }

        setLeftOperand(leftOperand);
    }

    @FXML
    void onRightInputChanged(InputMethodEvent event)
    {
        String rightStr = view.getRightOperand(rightInput);
        try
        {
            leftOperand = Double.parseDouble(rightStr);
        }
        catch (NullPointerException | NumberFormatException e)
        {
            view.displayError("Number '" + rightStr + "' is not parsable!");
        }

        setLeftOperand(leftOperand);
    }

    @FXML
    void onLeftInputClicked(MouseEvent event)
    {
        /*leftInput.getStyleClass().add("textInput.css");
        rightInput.getStyleClass().remove("textInput.css");*/
    }

    @FXML
    void onRightInputClicked(MouseEvent event)
    {
        /*rightInput.getStyleClass().add("textInput.css");
        leftInput.getStyleClass().remove("textInput.css");*/
    }
}
