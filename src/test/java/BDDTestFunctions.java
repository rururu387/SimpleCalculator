import io.cucumber.java.en.*;
import jdk.jshell.spi.ExecutionControl;
import org.junit.jupiter.api.Assertions;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.matcher.control.LabeledMatchers;
import org.testfx.robot.Motion;

//@ExtendWith(ApplicationExtension.class)
public class BDDTestFunctions extends TestFXBase
{
    //\d+(.\d+)?
    @Given("left operand is {double}")
    public void left_operand_is(Double value)
    {
        InputOutputUtils.fillLeftInputString(this, value);
    }

    @Given("right operand is {double}")
    public void right_operand_is(Double value)
    {
        InputOutputUtils.fillRightInputString(this, value);
    }

    @Given("left operand typed {double}")
    public void left_operand_typed(Double value)
    {
        InputOutputUtils.fillLeftInputButtons(this, value);
    }

    @Given("right operand typed {double}")
    public void right_operand_typed(Double value)
    {
        InputOutputUtils.fillRightInputString(this, value);
    }

    @Given("^operator set to (.*)$")
    public void operator_set_to(String operator) throws ExecutionControl.NotImplementedException
    {
        InputOutputUtils.setOperator(this, operator);
    }

    @Given("^clear button pressed$")
    public void clear_button_pressed()
    {
        this.clickOn("#clear");
    }

    @When("result is calculated")
    public void result_is_calculated()
    {
        this.clickOn("#equals");
    }

    @Then("^it should match (.*)$")
    public void it_should_match(String expected)
    {
        FxAssert.verifyThat("#resultLabel", LabeledMatchers.hasText(expected));
    }

    @Then("^division by zero message window pops up$")
    public void division_by_zero_message_window_pops_up()
    {
        Assertions.assertEquals(InputOutputUtils.verifyAlertText(this, "Division by zero!"), 1);
    }

    @Then("^operation not supported message window pops up$")
    public void operation_not_supported_message_window_pops_up()
    {
        Assertions.assertEquals(InputOutputUtils.verifyAlertText(this, "Operation is not supported!"), 1);
    }
}
