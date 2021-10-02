package ru.miet.testing.View;

import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class FXView implements CalculatorView
{
    private static FXView instance;

    private FXView(TextField leftInput, TextField rightInput)
    {
        leftInput.setText("0");
        rightInput.setText("0");
    }

    public static FXView initializeInstance(TextField leftInput, TextField rightInput)
    {
        if (instance == null)
        {
            instance = new FXView(leftInput, rightInput);
        }
        return instance;
    }

    public static FXView getInstance()
    {
        return instance;
    }

    @Override
    public void printResult(double result, TextArea resultArea)
    {
        resultArea.setText(String.valueOf(result));
    }

    @Override
    public void displayError(String message)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.YES);
        alert.showAndWait();
    }

    @Override
    public String getFirstArgumentAsString(TextField leftInput)
    {
        return leftInput.getText();
    }

    @Override
    public String getSecondArgumentAsString(TextField rightInput)
    {
        return rightInput.getText();
    }

    public void setOperation(String str, TextArea operationArea)
    {
        operationArea.setText(str);
    }

    @Override
    public void setResult(String str, TextArea resultArea)
    {
        resultArea.setText(str);
    }

    @Override
    public String getLeftOperand(TextField leftInput)
    {
        return leftInput.getText();
    }

    @Override
    public String getRightOperand(TextField rightInput)
    {
        return rightInput.getText();
    }

    @Override
    public void setLeftOperand(String str, TextField leftInput)
    {
        leftInput.setText(str);
    }

    @Override
    public void setRightOperand(String str, TextField rightInput)
    {
        rightInput.setText(str);
    }

    @Override
    public short addStrToActiveField(String str, TextField... fields)
    {
        short counter = 0;
        for (var field : fields)
        {
            if (field.isFocused())
            {
                field.setText(field.getText() + str);
                return counter;
            }
            counter++;
        }

        return -1;
    }
}
