package com.isep.utils.scenecontrollers;

import com.isep.rpg.hero.*;
import com.isep.rpg.item.Consumable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.event.*;
import javafx.scene.input.MouseEvent;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.Duration;



import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class FightMenu
{
    @FXML
    private Label nameHero1,nameHero2,nameHero3,nameHero4,statusHero1,statusHero2,statusHero3,statusHero4,labelAzog,labelSmaug,labelShelob,labelLurtz, labelSpeech;
    @FXML
    private Label labelInventory,labelOnigiri,labelSushis,labelRamen,labelLowPotion,labelMediumPotion,labelHighPotion;
    @FXML
    private AnchorPane boxHero1,boxHero2,boxHero3,boxHero4,boxAzog,boxSmaug,boxShelob,boxLurtz,boxBag;
    @FXML
    private Button sound,buttonCloseBag;

    @FXML
    private ImageView imageHero1,imageHero2,imageHero3,imageHero4,imageBag;

    private Clip clip = AudioSystem.getClip();

    public FightMenu() throws LineUnavailableException {
    }

    public void initialize() throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
        // -_-_-_-_-_-_-_-_-_- load fonts -_-_-_-_-_-_-_-_-_-

        Font font = Font.loadFont(new FileInputStream("src/data/fonts/TheWildBreathOfZelda-15Lv.ttf"), 17);

        nameHero1.setFont(font);
        nameHero2.setFont(font);
        nameHero3.setFont(font);
        nameHero4.setFont(font);

        statusHero1.setFont(font);
        statusHero2.setFont(font);
        statusHero3.setFont(font);
        statusHero4.setFont(font);

        labelShelob.setFont(font);
        labelSmaug.setFont(font);
        labelAzog.setFont(font);
        labelLurtz.setFont(font);

        labelSpeech.setFont(font);

        Font font2 = Font.loadFont(new FileInputStream("src/data/fonts/TheWildBreathOfZelda-15Lv.ttf"), 30);
        labelInventory.setFont(font2);
        labelOnigiri.setFont(font2);
        labelSushis.setFont(font2);
        labelRamen.setFont(font2);
        labelLowPotion.setFont(font2);
        labelMediumPotion.setFont(font2);
        labelHighPotion.setFont(font2);

        buttonCloseBag.setFont(Font.loadFont(new FileInputStream("src/data/fonts/MesloLGS-NF.ttf"), 40));

        boxLurtz.setVisible(false);
        boxSmaug.setVisible(false);
        boxAzog.setVisible(false);
        boxShelob.setVisible(false);

        statusHero2.setVisible(false);
        statusHero3.setVisible(false);
        statusHero4.setVisible(false);

        boxBag.setVisible(false);

        int i;

        for(i=0;i<StageLoader.enemies.size();i++)
        {
            String current = StageLoader.enemies.get(i).getName();
            int hp = StageLoader.enemies.get(i).getHP();
            switch(current)
            {
                case "Lurtz":
                    boxLurtz.setVisible(true);
                    labelLurtz.setText(" \uF7D0  [" + Integer.toString(hp) + "] " + current);
                    break;
                case "Smaug":
                    boxSmaug.setVisible(true);
                    labelSmaug.setText(" \uF7D0  [" + Integer.toString(hp) + "] " + current);
                    break;
                case "Azog":
                    boxAzog.setVisible(true);
                    labelAzog.setText(" \uF7D0  [" + Integer.toString(hp) + "] " + current);
                    break;
                case "Shelob":
                    boxShelob.setVisible(true);
                    labelShelob.setText(" \uF7D0  [" + Integer.toString(hp) + "] " + current);
                    break;
            }
        }

        boxHero2.setVisible(false);
        boxHero3.setVisible(false);
        boxHero4.setVisible(false);

        setupHero(StageLoader.hero1,imageHero1,nameHero1);
        updateStatus(statusHero1,StageLoader.hero1);

        if(StageLoader.hero2 != null)
        {
            setupHero(StageLoader.hero2, imageHero2, nameHero2);
            boxHero2.setVisible(true);
            updateStatus(statusHero2,StageLoader.hero2);
        }
        if(StageLoader.hero3 != null)
        {
            setupHero(StageLoader.hero3,imageHero3,nameHero3);
            boxHero3.setVisible(true);
            updateStatus(statusHero3,StageLoader.hero3);
        }
        if(StageLoader.hero4 != null)
        {
            setupHero(StageLoader.hero4,imageHero4,nameHero4);
            boxHero4.setVisible(true);
            updateStatus(statusHero4,StageLoader.hero4);
        }

        sound.setFont(Font.loadFont(new FileInputStream("src/data/fonts/MesloLGS-NF.ttf"), 20));
        this.clip.open(AudioSystem.getAudioInputStream(new File("src/data/musics/enemyAudio.wav")));
        if(StageLoader.sound)
            this.clip.loop(Clip.LOOP_CONTINUOUSLY);
        else
            sound.setText("\uF026");

        updateInventory();
        updateSpeech("What will " + StageLoader.hero1.getName() + " do ?");
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

    @FXML
    protected void closeBag()
    {
        boxBag.setVisible(false);
    }

    @FXML
    protected void openBagPack()
    {
        if(boxBag.isVisible())
            boxBag.setVisible(false);
        else
            boxBag.setVisible(true);
    }

    private void setupHero(Hero hero, ImageView image, Label name)
    {
        name.setText(hero.getName());
        if(hero instanceof Warrior)
            image.setImage(new Image("/data/imgs/warrior.gif"));
        if(hero instanceof Hunter)
            image.setImage(new Image("/data/imgs/hunter.gif"));
        if(hero instanceof Mage)
            image.setImage(new Image("/data/imgs/mage.gif"));
        if(hero instanceof Healer)
            image.setImage(new Image("/data/imgs/healer.gif"));
    }

    private void updateStatus(Label status, Hero hero)
    {
        int hp = hero.getHP();
        int def = hero.getDefense();
        int mana = hero.getMana();
        if(hp == 0)
        {
            status.setVisible(false);
        }
        else
        {
            status.setVisible(true);
            status.setText("\uF7D0 " + Integer.toString(hp) + " 聯 " + Integer.toString(def) + " 懲" + Integer.toString(mana) + " " + hero.getName());
        }
    }

    private void updateInventory()
    {
        int onigiri=0,sushis=0,ramen=0,lowPot=0,medPot=0,highPot=0;
        for(int i=0;i<StageLoader.consumables.size();i++)
        {
            String current = StageLoader.consumables.get(i).getName();
            switch(current)
            {
                case "onigiri":
                    onigiri++;
                    break;
                case "sushis":
                    sushis++;
                    break;
                case "ramen":
                    ramen++;
                    break;
                case "simple healing potion":
                    lowPot++;
                    break;
                case "medium healing potion":
                    medPot++;
                    break;
                case "high healing potion":
                    highPot++;
                    break;
            }
            labelOnigiri.setText(Integer.toString(onigiri));
            labelSushis.setText(Integer.toString(sushis));
            labelRamen.setText(Integer.toString(ramen));
            labelLowPotion.setText(Integer.toString(lowPot));
            labelMediumPotion.setText(Integer.toString(medPot));
            labelHighPotion.setText(Integer.toString(highPot));
        }
    }

    private void updateSpeech(String s) throws IOException, InterruptedException
    {
        String t = "";
        for(int i = 0; i<s.length();i++)
        {
            t += s.charAt(i);
            //System.out.println(t);
            labelSpeech.setText(t);
            Thread.sleep(10);
        }
    }

    @FXML
    protected void useOnigiri()
    {
        if(labelOnigiri.getText().equals("0"))
            System.out.println("No onigiri to use");
        else
        {
            StageLoader.action = 3;
            StageLoader.consumable2use = "onigiri";
            StageLoader.choiceEnd = true;
            StageLoader.sleep(500);
        }
    }
    @FXML
    protected void useSushis()
    {
        if(labelSushis.getText().equals("0"))
            System.out.println("No sushis to use");
        else
        {
            StageLoader.action = 3;
            StageLoader.consumable2use = "sushis";
            StageLoader.choiceEnd = true;
            StageLoader.sleep(500);
        }
    }
    @FXML
    protected void useRamen()
    {
        if(labelRamen.getText().equals("0"))
            System.out.println("No ramen to use");
        else
        {
            StageLoader.action = 3;
            StageLoader.consumable2use = "ramen";
            StageLoader.choiceEnd = true;
            StageLoader.sleep(500);
        }
    }
    @FXML
    protected void useLowPot()
    {
        if(labelLowPotion.getText().equals("0"))
            System.out.println("No simple potion to use");
        else
        {
            StageLoader.action = 3;
            StageLoader.consumable2use = "simple healing potion";
            StageLoader.choiceEnd = true;
            StageLoader.sleep(500);
        }
    }
    @FXML
    protected void useMedPot()
    {
        System.out.println("med pot");
        if(labelMediumPotion.getText().equals("0"))
            System.out.println("No medium potion to use");
        else
        {
            StageLoader.action = 3;
            StageLoader.consumable2use = "medium healing potion";
            StageLoader.choiceEnd = true;
            StageLoader.sleep(500);
        }
    }
    @FXML
    protected void useHighPot()
    {
        if(labelHighPotion.getText().equals("0"))
            System.out.println("No high potion to use");
        else
        {
            StageLoader.action = 3;
            StageLoader.consumable2use = "high healing potion";
            StageLoader.choiceEnd = true;
            StageLoader.sleep(500);
        }
    }
}