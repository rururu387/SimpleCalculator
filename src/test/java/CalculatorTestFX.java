import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.TextInputControlMatchers;
import org.testfx.matcher.control.LabeledMatchers;
import org.testfx.service.query.NodeQuery;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import static org.testfx.matcher.base.NodeMatchers.isEnabled;

@ExtendWith(ApplicationExtension.class)
public class CalculatorTestFX
{
    Pane anchorPane;
    Stage mainStage;

    @Start
    public void start(Stage stage) throws IOException
    {
        String fxmlPath = "/home/lavrentiy/IdeaProjects/TestLab2/src/main/resources/calculatorScene.fxml";
        URL fxmlUrl = new File(fxmlPath).toURI().toURL();
        anchorPane = (Pane) FXMLLoader.load(fxmlUrl);
        mainStage = stage;
        stage.setScene(new Scene(anchorPane));
        stage.show();
        stage.toFront();
    }

    @AfterAll
    public static void tearDown(FxRobot robot) throws Exception
    {
        FxToolkit.hideStage();
        robot.release(new KeyCode[]{});
        robot.release(new MouseButton[]{});
    }

    void fillInputString(FxRobot robot, double leftNumber, double rightNumber)
    {
        robot.doubleClickOn("#leftInput");
        robot.write(String.valueOf(leftNumber));
        robot.doubleClickOn("#rightInput");
        robot.write(String.valueOf(rightNumber));
    }

    void inputSymbol(FxRobot robot, String inputFieldId, char symbol)
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

    void fillInputButtons(FxRobot robot, double leftNumber, double rightNumber)
    {
        robot.doubleClickOn("#leftInput");

        DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols();
        formatSymbols.setDecimalSeparator('.');
        formatSymbols.setGroupingSeparator('.');
        DecimalFormat trunkZeros = new DecimalFormat(
                "0.##########################################################", formatSymbols);

        for (char symbol : trunkZeros.format(leftNumber).toCharArray())
        {
            inputSymbol(robot, "#leftInput", symbol);
        }

        robot.doubleClickOn("#rightInput");

        for (char symbol : trunkZeros.format(rightNumber).toCharArray())
        {
            inputSymbol(robot, "#rightInput", symbol);
        }
    }

    int verifyAlertText(FxRobot robot, String text)
    {
        Node dialogPane = robot.lookup(".dialog-pane").query();
        NodeQuery nodeQuery = robot.from(dialogPane).lookup((Text t) -> t.getText().equals(text));
        return nodeQuery.queryAll().size();
    }

    boolean verifyDoubleStringResult(FxRobot robot, double expected, double accuracy)
    {
        Label resultLabel = robot.from(anchorPane).lookup("#resultLabel").query();
        double resultValue = Double.parseDouble(resultLabel.getText());
        return expected - resultValue > -accuracy && expected - resultValue < accuracy;
    }

    @Test
    public void divisionZeroPopupWindowTest1(FxRobot robot)
    {
        fillInputString(robot, 7.5, 0);
        robot.clickOn("#div");
        robot.clickOn("#equals");
        FxAssert.verifyThat("#alertOkButton", isEnabled());
        Assertions.assertEquals(verifyAlertText(robot, "Division by zero!"), 1);
        robot.clickOn("#alertOkButton");
    }

    @Test
    public void notSetOperationTest2(FxRobot robot)
    {
        fillInputString(robot, 11, 15);
        robot.clickOn("#equals");
        FxAssert.verifyThat("#alertOkButton", isEnabled());
        Assertions.assertEquals(verifyAlertText(robot, "Operation is not supported!"), 1);
        robot.clickOn("#alertOkButton");
    }

    @Test
    public void testClear3(FxRobot robot)
    {
        fillInputButtons(robot, 11, 65);
        robot.clickOn("#mult");
        robot.clickOn("#equals");
        robot.clickOn("#clear");
        FxAssert.verifyThat("#operationLabel", LabeledMatchers.hasText("?"));
        FxAssert.verifyThat("#leftInput", TextInputControlMatchers.hasText(""));
        FxAssert.verifyThat("#rightInput", TextInputControlMatchers.hasText(""));
        FxAssert.verifyThat("#resultLabel", LabeledMatchers.hasText("Ans"));
    }

    @Test
    public void sumOperationBasic4(FxRobot robot)
    {
        fillInputButtons(robot, 48.11, 58.3);
        robot.clickOn("#plus");
        robot.clickOn("#equals");

        FxAssert.verifyThat("#operationLabel", LabeledMatchers.hasText("+"));

        //Unimportant check
        FxAssert.verifyThat("#resultLabel", LabeledMatchers.hasText(String.valueOf(106.41)));
    }

    @Test
    public void subtractOperationBasic5(FxRobot robot)
    {
        fillInputButtons(robot, -35.6, -95.1056);
        robot.clickOn("#minus");
        robot.clickOn("#equals");

        FxAssert.verifyThat("#operationLabel", LabeledMatchers.hasText("-"));
        Assertions.assertTrue(verifyDoubleStringResult(robot, 59.5056, 0.001));
    }

    @Test
    public void multiplyOperationBasic5(FxRobot robot)
    {
        fillInputButtons(robot, -35.6, -95.1056);
        robot.clickOn("#mult");
        robot.clickOn("#equals");

        FxAssert.verifyThat("#operationLabel", LabeledMatchers.hasText("*"));
        Assertions.assertTrue(verifyDoubleStringResult(robot, 3385.75936, 0.00001));
    }

    @Test
    public void divOperationBasic6(FxRobot robot)
    {
        fillInputButtons(robot, -961.792, -83.2);
        robot.clickOn("#div");
        robot.clickOn("#equals");

        FxAssert.verifyThat("#operationLabel", LabeledMatchers.hasText("/"));
        FxAssert.verifyThat("#resultLabel", LabeledMatchers.hasText(String.valueOf(11.56)));
    }

    @Test
    public void stringInputTest7(FxRobot robot)
    {
        fillInputString(robot, 5323.13, 553467.3);

        FxAssert.verifyThat("#leftInput", TextInputControlMatchers.hasText("5323.13"));
        FxAssert.verifyThat("#rightInput", TextInputControlMatchers.hasText("553467.3"));
    }

    @Test
    public void buttonsInputTest8(FxRobot robot)
    {
        fillInputButtons(robot, 532391, 553467);

        FxAssert.verifyThat("#leftInput", TextInputControlMatchers.hasText("532391"));
        FxAssert.verifyThat("#rightInput", TextInputControlMatchers.hasText("553467"));
    }
}
