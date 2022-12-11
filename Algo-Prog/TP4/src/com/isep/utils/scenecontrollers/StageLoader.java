package com.isep.utils.scenecontrollers;


import com.isep.rpg.hero.Hero;
import com.isep.utils.GUIParser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import javafx.stage.Stage;

public class StageLoader
{
    private static Stage primaryStage;
    private static FXMLLoader loader;

    public static void setStage(Stage stage)
    {
        primaryStage = stage;
    }

    public static void loadFXMLScene(String path) throws IOException
    {
        // Create a new FXMLLoader object
        loader = new FXMLLoader(GUIParser.class.getResource(path));

        // Load the FXML file
        Parent root = loader.load();
        // Set the root node of the scene to the newly loaded FXML file
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static boolean sound = false;
    public static boolean choiceEnd = false;
    public static int herosNumber = 4;
    public static Hero hero1;
    public static Hero hero2;
    public static Hero hero3;
    public static Hero hero4;
}
