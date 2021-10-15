package ru.miet.testing.View;

import javafx.scene.control.*;

public class FXView implements CalculatorView
{
    private static FXView instance;

    private FXView(TextField leftInput, TextField rightInput)
    {
        leftInput.setText("");
        rightInput.setText("");
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
    public void printResult(double result, Label resultLabel)
    {
        resultLabel.setText(String.valueOf(result));
    }

    @Override
    public void displayError(String message)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.getDialogPane().lookupButton(ButtonType.OK).setId("alertOkButton");
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

    public void setOperation(String str, Label operationLabel)
    {
        operationLabel.setText(str);
    }

    @Override
    public void setResult(String str, Label resultLabel)
    {
        resultLabel.setText(str);
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
    public int getCaretPosition(TextInputControl field)
    {
        return field.getCaretPosition();
    }

    public void setCaretPosition(TextInputControl field, int position)
    {
        field.positionCaret(position);
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
    public short getFocusedField(TextField... fields)
    {
        short counter = 0;
        for (var field : fields)
        {
            if (field.isFocused())
            {
                return counter;
            }
            counter++;
        }

        return -1;
    }
}
