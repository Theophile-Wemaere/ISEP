package com.isep.utils.scenecontrollers;

import com.isep.rpg.hero.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.event.*;
import javafx.scene.input.MouseEvent;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;


import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class FightMenu
{
    String button_style = "-fx-border-color: #000000; -fx-background-color: #00000000; -fx-background-image: url('file:src/data/imgs/button-bg.jpg');";

    @FXML
    private Label title,name1,name2,name3,name4,class1,class2,class3,class4;
    @FXML
    private AnchorPane box1,box2,box3,box4;
    @FXML
    private Button fightButton,sound;
    @FXML
    private Slider slider;
    @FXML
    private ChoiceBox choice1,choice2,choice3,choice4;

    @FXML
    private TextField field1,field2,field3,field4;
    @FXML
    private ImageView image1,image2,image3,image4;

    private Clip clip = AudioSystem.getClip();

    public FightMenu() throws LineUnavailableException {
    }

    public void initialize() throws IOException, UnsupportedAudioFileException, LineUnavailableException {

        sound.setFont(Font.loadFont(new FileInputStream("src/data/fonts/MesloLGS-NF.ttf"), 20));
        this.clip.open(AudioSystem.getAudioInputStream(new File("src/data/musics/enemyAudio.wav")));
        if(StageLoader.sound)
            this.clip.loop(Clip.LOOP_CONTINUOUSLY);
        else
            sound.setText("\uF026");
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