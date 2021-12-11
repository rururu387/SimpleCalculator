import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import ru.miet.testing.MainFX;

import java.util.concurrent.TimeoutException;

public class TestFXBase extends ApplicationTest {

    static {
        try
        {
            ApplicationTest.launch(MainFX.class);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    Pane anchorPane;
    Stage mainStage;

    private static boolean isHeadless = false;

    @BeforeAll
    public static void setupHeadlessMode() throws Exception
    {
        if(isHeadless){
            System.setProperty("testfx.robot", "glass");
            System.setProperty("testfx.headless", "true");
            System.setProperty("prism.order", "sw");
            System.setProperty("prism.text", "t2k");
            System.setProperty("java.awt.headless", "true");
        }


    }

    @BeforeEach
    public void setUpClass() throws Exception
    {
        ApplicationTest.launch(MainFX.class);
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        stage.show();
        /*String fxmlPath = "src/main/resources/calculatorScene.fxml";
        URL fxmlUrl = new File(fxmlPath).toURI().toURL();
        anchorPane = (Pane) FXMLLoader.load(fxmlUrl);
        mainStage = stage;
        stage.setScene(new Scene(anchorPane));
        stage.show();
        stage.toFront();*/
    }

    @AfterAll
    public void afterEachTest() throws TimeoutException
    {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

    /* Helper method to retrieve Java FX GUI Components */
    public <T extends Node> T find (final  String query){
        return (T) lookup(query).queryAll().iterator().next();
    }

}