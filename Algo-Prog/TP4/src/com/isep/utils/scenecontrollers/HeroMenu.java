package com.isep.utils.scenecontrollers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.event.*;
import javafx.scene.input.MouseEvent;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javafx.collections.ObservableList;


import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class HeroMenu
{
    String button_style = "-fx-border-color: #000000; -fx-background-color: #00000000; -fx-background-image: url('file:src/data/imgs/button-bg.jpg');";

    @FXML
    private Label title,name1,name2,name3,name4,class1,class2,class3,class4;
    @FXML
    private AnchorPane box1,box2,box3,box4;
    @FXML
    private Button fightButton,sound;
    @FXML
    public Slider slider;
    @FXML
    public ChoiceBox choice1,choice2,choice3,choice4;

    private Clip clip = AudioSystem.getClip();

    public HeroMenu() throws LineUnavailableException {
    }

    public void initialize() throws IOException, UnsupportedAudioFileException, LineUnavailableException {

        // -_-_-_-_-_-_-_-_-_- add events -_-_-_-_-_-_-_-_-_-
        fightButton.setOnMouseEntered(event -> fightButton.setStyle(button_style + "-fx-border-width: 4;"));
        fightButton.setOnMouseExited(event -> fightButton.setStyle(button_style + "-fx-border-width: 3;"));

        // Add an event listener to the slider
        slider.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                onSliderMoved();
            }
        });
        slider.setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                onSliderMoved();
            }
        });

        // -_-_-_-_-_-_-_-_-_- setup choices box -_-_-_-_-_-_-_-_-_-

        ObservableList<String> list = FXCollections.observableArrayList("Warrior","Hunter", "Mage", "Healer");
        choice1.setItems(list);
        choice2.setItems(list);
        choice3.setItems(list);
        choice4.setItems(list);

        choice1.setValue("Warrior");
        choice2.setValue("Hunter");
        choice3.setValue("Mage");
        choice4.setValue("Healer");

        // -_-_-_-_-_-_-_-_-_- load fonts -_-_-_-_-_-_-_-_-_-

        Font font = Font.loadFont(new FileInputStream("src/data/fonts/TheWildBreathOfZelda-15Lv.ttf"), 24);
        slider.setStyle("-fx-font-family: '" + font.getFamily() + "'");

        title.setFont(Font.loadFont(new FileInputStream("src/data/fonts/TheWildBreathOfZelda-15Lv.ttf"), 50));

        name1.setFont(font);
        name2.setFont(font);
        name3.setFont(font);
        name4.setFont(font);

        class1.setFont(font);
        class2.setFont(font);
        class3.setFont(font);
        class4.setFont(font);

        box2.setVisible(false);
        box3.setVisible(false);
        box4.setVisible(false);

        fightButton.setFont(Font.loadFont(new FileInputStream("src/data/fonts/TheWildBreathOfZelda-15Lv.ttf"), 21));

        this.clip.open(AudioSystem.getAudioInputStream(new File("src/data/musics/heroMenu.wav")));
        if(StageLoader.sound)
            this.clip.loop(Clip.LOOP_CONTINUOUSLY);
        else
            sound.setText("\uF026");
    }

    protected void onSliderMoved()
    {
        double value = slider.getValue();
        switch((int) value)
        {
            case 1:
                box2.setVisible(false);
                box3.setVisible(false);
                box4.setVisible(false);
                break;

            case 2:
                box2.setVisible(true);
                box3.setVisible(false);
                box4.setVisible(false);
                break;

            case 3:
                box2.setVisible(true);
                box3.setVisible(true);
                box4.setVisible(false);
                break;

            case 4:
                box2.setVisible(true);
                box3.setVisible(true);
                box4.setVisible(true);
                break;
        }
    }

    @FXML
    protected void onFightButtonClick()
    {
        System.out.println("beginning");
        if(StageLoader.sound)
        {
            this.clip.stop();
        }
    }

    @FXML
    protected void onSoundButton()
    {
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