package ru.miet.testing.View;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public interface CalculatorView {

    /**
     * Отображает результат вычисления
     */
    void printResult(double result, Label resultLabel);

    /**
     * Показывает ошибку, например деление на 0, пустые аргументы и прочее
     */
    void displayError(String message);

    /**
     * Возвращает значение, введенное в поле первого аргументы
     */
    String getFirstArgumentAsString(TextField leftInput);

    /**
     * Возвращает значение, введенное в поле второго аргументы
     */
    String getSecondArgumentAsString(TextField rightInput);

    /**
     * Custom methods to implement a calculator and have polymorphism
     */
    void setOperation(String str, Label operationLabel);

    void setResult(String str, Label resultLabel);

    String getLeftOperand(TextField leftInput);

    String getRightOperand(TextField rightInput);

    void setLeftOperand(String str, TextField leftInput);

    void setRightOperand(String str, TextField rightInput);

    short getFocusedField(String str, TextField... fields);
}
