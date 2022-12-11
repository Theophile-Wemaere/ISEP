package com.isep.utils.scenecontrollers;


import com.isep.utils.GUIParser;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import javafx.stage.Stage;

public class StageLoader
{
    private static Stage primaryStage;
    public static boolean sound = true;

    public static void setStage(Stage stage)
    {
        primaryStage = stage;
    }

    public static void loadFXMLScene(String path) throws IOException
    {
        // Create a new FXMLLoader object
        FXMLLoader loader = new FXMLLoader(GUIParser.class.getResource(path));

        // Load the FXML file
        Parent root = loader.load();

        // Set the root node of the scene to the newly loaded FXML file
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
