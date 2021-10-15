package ru.miet.testing.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import ru.miet.testing.Model.CalculatorModel;
import ru.miet.testing.Model.CalculatorModelImpl;
import ru.miet.testing.OperationType;
import ru.miet.testing.View.CalculatorView;
import ru.miet.testing.View.FXView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class FXController implements CalculatorPresenter, Initializable
{
    private CalculatorModel model;
    private CalculatorView view;
    private OperationType currentSelectedOperation;
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
        view.printResult(0, resultLabel);
        currentSelectedOperation = OperationType.None;
    }

    @FXML
    private TextField leftInput;

    @FXML
    private TextField rightInput;

    @FXML
    private Label operationLabel;

    @FXML
    private Label eqSignLabel;

    @FXML
    private Label resultLabel;

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
            case None -> view.setOperation("?", operationLabel);
            case Sum -> view.setOperation("+", operationLabel);
            case Subtract -> view.setOperation("-", operationLabel);
            case Multiply -> view.setOperation("*", operationLabel);
            case Divide -> view.setOperation("/", operationLabel);
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
        view.setResult("Ans", resultLabel);
        view.setLeftOperand("", leftInput);
        view.setRightOperand("", rightInput);
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
            result = (double) method.invoke(model, Double.parseDouble(view.getLeftOperand(leftInput)), Double.parseDouble(view.getRightOperand(rightInput)));
        }
        catch (NumberFormatException e)
        {
            view.displayError("");
        }
        catch (InvocationTargetException e)
        {
            if (e.getTargetException().getMessage().equals("Division by zero!"))
            {
                view.displayError("Division by zero!");
            }
            else
            {
                view.displayError("Internal error. How did you produce it?");
            }
            return;
        }
        catch (IllegalArgumentException | IllegalAccessException e)
        {
            view.displayError("Internal error. How did you produce it?");
            return;
        }

        view.setResult(String.valueOf(result), resultLabel);
    }

    void concatOperand(short num, char symbol)
    {
        if (num == 0)
        {
            int caretPosition = view.getCaretPosition(leftInput);
            String leftInputString = view.getRightOperand(leftInput);
            String concatenatedStr = leftInputString.substring(0, caretPosition) + symbol + leftInputString.substring(caretPosition);
            view.setLeftOperand(concatenatedStr, leftInput);
            view.setCaretPosition(leftInput, caretPosition + 1);
        }
        else if (num == 1)
        {
            int caretPosition = view.getCaretPosition(rightInput);
            String leftInputString = view.getRightOperand(rightInput);
            String concatenatedStr = leftInputString.substring(0, caretPosition) + symbol + leftInputString.substring(caretPosition);
            view.setRightOperand(concatenatedStr, rightInput);
            view.setCaretPosition(rightInput, caretPosition + 1);
        }
    }

    @FXML
    void onZeroClicked(MouseEvent event)
    {
        concatOperand(view.getFocusedField(leftInput, rightInput), '0');
    }

    @FXML
    void onOneClicked(MouseEvent event)
    {
        concatOperand(view.getFocusedField(leftInput, rightInput), '1');
    }

    @FXML
    void onTwoClicked(MouseEvent event)
    {
        concatOperand(view.getFocusedField(leftInput, rightInput), '2');
    }

    @FXML
    void onThreeClicked(MouseEvent event)
    {
        concatOperand(view.getFocusedField(leftInput, rightInput), '3');
    }

    @FXML
    void onFourClicked(MouseEvent event)
    {
        concatOperand(view.getFocusedField(leftInput, rightInput), '4');
    }

    @FXML
    void onFiveClicked(MouseEvent event)
    {
        concatOperand(view.getFocusedField(leftInput, rightInput), '5');
    }

    @FXML
    void onSixClicked(MouseEvent event)
    {
        concatOperand(view.getFocusedField(leftInput, rightInput), '6');
    }

    @FXML
    void onSevenClicked(MouseEvent event)
    {
        concatOperand(view.getFocusedField(leftInput, rightInput), '7');
    }

    @FXML
    void onEightClicked(MouseEvent event)
    {
        concatOperand(view.getFocusedField(leftInput, rightInput), '8');
    }

    @FXML
    void onNineClicked(MouseEvent event)
    {
        concatOperand(view.getFocusedField(leftInput, rightInput), '9');
    }
}
