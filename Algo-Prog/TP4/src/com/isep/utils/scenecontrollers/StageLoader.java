package com.isep.utils.scenecontrollers;


import com.isep.rpg.Combatant;
import com.isep.rpg.hero.Hero;
import com.isep.rpg.item.Consumable;
import com.isep.utils.GUIParser;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.ArrayList;

import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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
        primaryStage.setTitle("MINI RPG LITE 3000 : The return of the fallens angels");
        primaryStage.show();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                // End the JavaFX thread when the window is closed
                Platform.exit();
                System.exit(0);
            }
        });
    }

    public static boolean sound = false;
    public static boolean choiceEnd = false;
    public static int herosNumber = 4;
    public static int action;
    public static Hero hero1;
    public static Hero hero2;
    public static Hero hero3;
    public static Hero hero4;
    public static int player = 0;
    public static ArrayList<Combatant> enemies = new ArrayList<>();
    public static ArrayList<Combatant> boss = new ArrayList<>();
    public static ArrayList<Combatant> heros = new ArrayList<>();
    public static ArrayList<Consumable> consumables = new ArrayList<>();
    public static String consumable2use;

    public static void sleep(int n)
    {
        try {
            Thread.sleep(n);
        } catch (InterruptedException e) {
            // Handle the exception
        }
    }

    public static void deepCopyCombatant(ArrayList<Combatant> input, ArrayList<Combatant> output)
    {
        output.clear();
        for(Combatant element : input)
        {
            output.add(element);
        }
    }

    public static void deepCopyComsumables(ArrayList<Consumable> input, ArrayList<Consumable> output)
    {
        output.clear();
        for(Consumable element : input)
        {
            output.add(element);
        }
    }
}
