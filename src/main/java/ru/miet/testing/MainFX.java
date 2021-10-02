package ru.miet.testing;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;


public class MainFX extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        FXMLLoader loader = new FXMLLoader();

        String mainSceneFileName = "C:\\Users\\Lavrentiy_Gusev\\Олег\\MIET\\TestPO\\SimpleCalculator\\src\\main\\resources\\calculatorScene.fxml";

        URL xmlUrl = null;
        try
        {
            xmlUrl = new File(mainSceneFileName).toURI().toURL();
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
            System.out.println("File could not be located!");
        }

        try
        {
            loader.setLocation(xmlUrl);
            Parent root = loader.load();
            primaryStage.setScene(new Scene(root));
            primaryStage.setResizable(false);
            primaryStage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.out.println("Could not load .fxml file!");
        }
    }
}
