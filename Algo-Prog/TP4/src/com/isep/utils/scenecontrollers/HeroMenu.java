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
    private Slider slider;
    @FXML
    private ChoiceBox choice1,choice2,choice3,choice4;

    @FXML
    private TextField field1,field2,field3,field4;
    @FXML
    private ImageView image1,image2,image3,image4;

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

        choice1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // Handle the change in the selected item here
                changeImage(image1,newValue);
            }
        });
        choice2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // Handle the change in the selected item here
                changeImage(image2,newValue);
            }
        });
        choice3.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // Handle the change in the selected item here
                changeImage(image3,newValue);
            }
        });
        choice4.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // Handle the change in the selected item here
                changeImage(image4,newValue);
            }
        });

        // -_-_-_-_-_-_-_-_-_- load fonts -_-_-_-_-_-_-_-_-_-

        Font font = Font.loadFont(new FileInputStream("src/data/fonts/TheWildBreathOfZelda-15Lv.ttf"), 24);

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
        sound.setFont(Font.loadFont(new FileInputStream("src/data/fonts/MesloLGS-NF.ttf"), 20));

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
    protected void onFightButtonClick() throws IOException
    {
        String name;
        if(StageLoader.sound)
        {
            this.clip.stop();
        }
        int number = (int) slider.getValue();
        StageLoader.herosNumber = number;
        if(number >= 1)
            StageLoader.hero1 = setHero(choice1,field1);
        if(number >= 2)
            StageLoader.hero2 = setHero(choice2,field2);
        if(number >= 2)
            StageLoader.hero3 = setHero(choice3,field3);
        if(number >= 4)
            StageLoader.hero4 = setHero(choice4,field4);

        StageLoader.choiceEnd = true;
        StageLoader.loadFXMLScene("/data/scenes/fightMenu.fxml");
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

    public Hero setHero(ChoiceBox choice, TextField textField)
    {
        String name;
        switch((String) choice.getValue())
        {
            case "Warrior":
                name = textField.getText();
                if(name.equals(""))
                    name = "Aragorn";
                Warrior warrior = new Warrior(name);
                return warrior;

            case "Hunter":
                name = textField.getText();
                if(name.equals(""))
                    name = "Legolas";
                Hunter hunter = new Hunter(name);
                return hunter;

            case "Mage":
                name = textField.getText();
                if(name.equals(""))
                    name = "Gandalf";
                Mage mage = new Mage(name);
                return mage;

            case "Healer":
                name = textField.getText();
                if(name.equals(""))
                    name = "Elrond";
                Healer healer = new Healer(name);
                return healer;
        }
        return null;
    }


    public void changeImage(ImageView image, String choice)
    {
        switch(choice)
        {
            case "Warrior":
                image.setImage(new Image("/data/imgs/warrior.gif"));
                break;
            case "Hunter":
                image.setImage(new Image("/data/imgs/hunter.gif"));
                break;
            case "Mage":
                image.setImage(new Image("/data/imgs/mage.gif"));
                break;
            case "Healer":
                image.setImage(new Image("/data/imgs/healer.gif"));
                break;
        }
    }
}