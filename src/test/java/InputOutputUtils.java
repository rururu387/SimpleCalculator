import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import jdk.jshell.spi.ExecutionControl;
import org.testfx.api.FxRobot;
import org.testfx.service.query.NodeQuery;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class InputOutputUtils
{
    static DecimalFormat trunkZeros;

    static
    {
        DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols();
        formatSymbols.setDecimalSeparator('.');
        formatSymbols.setGroupingSeparator('.');
        trunkZeros = new DecimalFormat(
                "0.##########################################################", formatSymbols);
    }

    static public void fillInputString(FxRobot robot, double leftNumber, double rightNumber)
    {
        robot.doubleClickOn("#leftInput");
        robot.write(String.valueOf(leftNumber));
        robot.doubleClickOn("#rightInput");
        robot.write(String.valueOf(rightNumber));
    }

    static public void fillLeftInputString(FxRobot robot, double leftNumber)
    {
        robot.doubleClickOn("#leftInput");
        robot.write(String.valueOf(leftNumber));
    }

    static public void fillLeftInputString(FxRobot robot, String leftInput)
    {
        robot.doubleClickOn("#leftInput");
        robot.write(leftInput);
    }

    static public void fillRightInputString(FxRobot robot, double rightNumber)
    {
        robot.doubleClickOn("#rightInput");
        robot.write(String.valueOf(rightNumber));
    }

    static public void fillRightInputString(FxRobot robot, String rightInput)
    {
        robot.doubleClickOn("#rightInput");
        robot.write(rightInput);
    }

    static public void inputSymbol(FxRobot robot, String inputFieldId, char symbol)
    {
        switch (symbol)
        {
            case '0' -> robot.clickOn("#zero");
            case '1' -> robot.clickOn("#one");
            case '2' -> robot.clickOn("#two");
            case '3' -> robot.clickOn("#three");
            case '4' -> robot.clickOn("#four");
            case '5' -> robot.clickOn("#five");
            case '6' -> robot.clickOn("#six");
            case '7' -> robot.clickOn("#seven");
            case '8' -> robot.clickOn("#eight");
            case '9' -> robot.clickOn("#nine");
            case '.' ->
                    {
                        robot.clickOn(inputFieldId);
                        robot.press(KeyCode.END);
                        robot.write(".");
                    }
            case '-' ->
                    {
                        robot.clickOn(inputFieldId);
                        robot.press(KeyCode.END);
                        robot.write("-");
                    }
            default -> throw new IllegalArgumentException("Some troubles with testing code! Input symbol: " + symbol);
        }
    }

    static public void fillLeftInputButtons(FxRobot robot, double leftNumber)
    {
        fillLeftInputButtons(robot, trunkZeros.format(leftNumber));
    }

    static public void fillLeftInputButtons(FxRobot robot, String leftNumber)
    {
        robot.doubleClickOn("#leftInput");

        for (char symbol : leftNumber.toCharArray())
        {
            inputSymbol(robot, "#leftInput", symbol);
        }
    }

    static public void fillRightInputButtons(FxRobot robot, double rightNumber)
    {
        fillRightInputButtons(robot, trunkZeros.format(rightNumber));
    }

    static public void fillRightInputButtons(FxRobot robot, String rightNumber)
    {
        robot.doubleClickOn("#rightInput");

        for (char symbol : rightNumber.toCharArray())
        {
            inputSymbol(robot, "#rightInput", symbol);
        }
    }

    static public void fillInputButtons(FxRobot robot, double leftNumber, double rightNumber)
    {
        fillLeftInputButtons(robot, leftNumber);
        fillRightInputButtons(robot, rightNumber);
    }

    static public void fillInputButtons(FxRobot robot, String leftNumber, String rightNumber)
    {
        fillLeftInputButtons(robot, leftNumber);
        fillRightInputButtons(robot, rightNumber);
    }

    static public void setOperator(FxRobot robot, String operator) throws ExecutionControl.NotImplementedException
    {
        switch (operator)
        {
            case "+" ->
            {
                robot.clickOn("#plus");
            }
            case "-" ->
            {
                robot.clickOn("#minus");
            }
            case "*" ->
            {
                robot.clickOn("#mult");
            }
            case "/" ->
            {
                robot.clickOn("#div");
            }
            default ->
            {
                throw new ExecutionControl.NotImplementedException("Not a known operator: '" + operator
                                                            + "'. Consider updating tests (and likely source code)");
            }
        }
    }

    static int verifyAlertText(FxRobot robot, String text)
    {
        Node dialogPane = robot.lookup(".dialog-pane").query();
        NodeQuery nodeQuery = robot.from(dialogPane).lookup((Text t) -> t.getText().equals(text));
        return nodeQuery.queryAll().size();
    }
}
