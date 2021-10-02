package ru.miet.testing.View;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ru.miet.testing.OperationType;

public interface CalculatorView {

    /**
     * Отображает результат вычисления
     */
    void printResult(double result, TextArea resultArea);

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
    void setOperation(String str, TextArea operationArea);

    void setResult(String str, TextArea resultArea);

    String getLeftOperand(TextField leftInput);

    String getRightOperand(TextField rightInput);

    void setLeftOperand(String str, TextField leftInput);

    void setRightOperand(String str, TextField rightInput);

    short addStrToActiveField(String str, TextField... fields);
}
