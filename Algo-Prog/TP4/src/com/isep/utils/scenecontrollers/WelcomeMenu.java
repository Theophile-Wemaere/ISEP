package com.isep.utils.scenecontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class WelcomeMenu
{
    @FXML
    private Label title1, title2, corner1, corner2;
    private Clip clip = AudioSystem.getClip();

    public WelcomeMenu() throws LineUnavailableException {
    }

    public void initialize() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        // -_-_-_-_-_-_-_-_-_- load fonts -_-_-_-_-_-_-_-_-_-

        // Load the font from the file at the specified path
        title1.setFont(Font.loadFont(new FileInputStream("src/data/fonts/TheWildBreathOfZelda-15Lv.ttf"), 48));
        title2.setFont(Font.loadFont(new FileInputStream("src/data/fonts/TheWildBreathOfZelda-15Lv.ttf"), 32));
        corner1.setFont(Font.loadFont(new FileInputStream("src/data/fonts/MesloLGS-NF.ttf"), 13));
        corner2.setFont(Font.loadFont(new FileInputStream("src/data/fonts/MesloLGS-NF.ttf"), 13));

        Class clazz = getClass();
        String audioPath = "/data/musics/welcomeAudio.wav";
        this.clip.open(AudioSystem.getAudioInputStream(clazz.getResource(audioPath)));
        this.clip.start();
    }

    @FXML
    protected void onBeginButtonClick()
    {
        System.out.println("beginning");
    }

    @FXML
    protected void onQuitButtonClick() {
        System.exit(0);
        this.clip.stop();
    }
}