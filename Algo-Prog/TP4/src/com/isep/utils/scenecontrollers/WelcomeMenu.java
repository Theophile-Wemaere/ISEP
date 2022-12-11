package com.isep.utils.scenecontrollers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class WelcomeMenu
{
    String button_style = "-fx-border-color: #000000; -fx-background-color: #00000000; -fx-background-image: url('file:src/data/imgs/button-bg.jpg');";

    @FXML
    private Label title1, title2, corner1, corner2;
    @FXML
    private Button begin, quit, sound;
    private Clip clip = AudioSystem.getClip();

    private boolean beginning = false;

    public WelcomeMenu() throws LineUnavailableException {
    }

    public void initialize() throws IOException, UnsupportedAudioFileException, LineUnavailableException {

        // -_-_-_-_-_-_-_-_-_- add events -_-_-_-_-_-_-_-_-_-
        begin.setOnMouseEntered(event -> begin.setStyle(button_style + "-fx-border-width: 4;"));
        begin.setOnMouseExited(event -> begin.setStyle(button_style + "-fx-border-width: 3;"));

        quit.setOnMouseEntered(event -> quit.setStyle(button_style + "-fx-border-width: 4;"));
        quit.setOnMouseExited(event -> quit.setStyle(button_style + "-fx-border-width: 3;"));

        // -_-_-_-_-_-_-_-_-_- load fonts -_-_-_-_-_-_-_-_-_-

        // Load the font from the file at the specified path
        title1.setFont(Font.loadFont(new FileInputStream("src/data/fonts/TheWildBreathOfZelda-15Lv.ttf"), 48));
        title2.setFont(Font.loadFont(new FileInputStream("src/data/fonts/TheWildBreathOfZelda-15Lv.ttf"), 32));
        corner1.setFont(Font.loadFont(new FileInputStream("src/data/fonts/MesloLGS-NF.ttf"), 13));
        corner2.setFont(Font.loadFont(new FileInputStream("src/data/fonts/MesloLGS-NF.ttf"), 13));
        begin.setFont(Font.loadFont(new FileInputStream("src/data/fonts/MesloLGS-NF.ttf"), 16));
        quit.setFont(Font.loadFont(new FileInputStream("src/data/fonts/MesloLGS-NF.ttf"), 16));
        sound.setFont(Font.loadFont(new FileInputStream("src/data/fonts/MesloLGS-NF.ttf"), 20));

        this.clip.open(AudioSystem.getAudioInputStream(new File("src/data/musics/welcomeAudio.wav")));
        if(StageLoader.sound)
            this.clip.loop(Clip.LOOP_CONTINUOUSLY);
        else
            sound.setText("\uF026");
    }

    @FXML
    protected void onBeginButtonClick() throws IOException
    {
        if(StageLoader.sound)
            this.clip.stop();
        StageLoader.loadFXMLScene("/data/scenes/heroMenu.fxml");
    }

    @FXML
    protected void onQuitButtonClick() {
        System.exit(0);
        if(StageLoader.sound)
            this.clip.stop();
    }

    @FXML
    protected void onSoundButton(){
        if(StageLoader.sound)
        {
            StageLoader.sound = false;
            sound.setText("\uF026");
            this.clip.stop();
        }
        else
        {
            StageLoader.sound = true;
            sound.setText("\uF028");
            this.clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }
}