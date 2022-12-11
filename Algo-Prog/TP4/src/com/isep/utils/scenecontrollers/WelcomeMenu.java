package com.isep.utils.scenecontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.IOException;

public class WelcomeMenu
{
    @FXML
    private Label title1, title2;

    public void initialize() throws IOException
    {
        // -_-_-_-_-_-_-_-_-_- load fonts -_-_-_-_-_-_-_-_-_-

        // Load the font from the file at the specified path
        Font zeldaFont = Font.loadFont(new FileInputStream("src/data/fonts/TheWildBreathOfZelda-15Lv.ttf"), 30);
        title1.setFont(zeldaFont);
        title2.setFont(zeldaFont);
    }

    @FXML
    protected void onBeginButtonClick()
    {
        System.out.println("beginning");
    }

    @FXML
    protected void onQuitButtonClick() {
        System.exit(0);
    }

}