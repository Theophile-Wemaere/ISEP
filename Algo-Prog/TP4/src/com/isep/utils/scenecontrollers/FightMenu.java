package com.isep.utils.scenecontrollers;

import com.isep.rpg.Enemy;
import com.isep.rpg.hero.*;
import com.isep.rpg.item.Consumable;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.NodeOrientation;
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

    @FXML
    ImageView imageSmaug, imageShelob, imageAzog, imageLurtz;

    private Thread thread;
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

        updateEnemies();

        boxHero2.setVisible(false);
        boxHero3.setVisible(false);
        boxHero4.setVisible(false);

        setupHero((Hero) StageLoader.heros.get(0),imageHero1,nameHero1);
        updateStatus(statusHero1,(Hero) StageLoader.heros.get(0));

        if(StageLoader.heros.size() > 1)
        {
            setupHero((Hero) StageLoader.heros.get(1), imageHero2, nameHero2);
            boxHero2.setVisible(true);
            updateStatus(statusHero2,(Hero) StageLoader.heros.get(1));
        }
        if(StageLoader.heros.size() > 2)
        {
            setupHero((Hero) StageLoader.heros.get(2),imageHero3,nameHero3);
            boxHero3.setVisible(true);
            updateStatus(statusHero3,(Hero) StageLoader.heros.get(2));
        }
        if(StageLoader.heros.size() > 3)
        {
            setupHero((Hero) StageLoader.heros.get(3),imageHero4,nameHero4);
            boxHero4.setVisible(true);
            updateStatus(statusHero4,(Hero) StageLoader.heros.get(3));
        }

        for(int i=0;i<StageLoader.enemies.size();i++)
        {
            String current = StageLoader.enemies.get(i).getName();
            switch(current)
            {
                case "Smaug":
                    StageLoader.Smaug = true;
                    break;
                case "Shelob":
                    StageLoader.Shelob = true;
                    break;
                case "Lurtz":
                    StageLoader.Lurtz = true;
                    break;
                case "Azog":
                    StageLoader.Azog = true;
                    break;
            }
        }
        System.out.println(StageLoader.Smaug);
        System.out.println(StageLoader.Shelob);
        System.out.println(StageLoader.Azog);
        System.out.println(StageLoader.Lurtz);


        sound.setFont(Font.loadFont(new FileInputStream("src/data/fonts/MesloLGS-NF.ttf"), 20));
        this.clip.open(AudioSystem.getAudioInputStream(new File("src/data/musics/enemyAudio2.wav")));
        if(StageLoader.sound)
            this.clip.loop(Clip.LOOP_CONTINUOUSLY);
        else
            sound.setText("\uF026");
        updateInventory();
        updateSpeech("What will " + StageLoader.heros.get(StageLoader.player).getName() + " do ?");
    }

    public void update()
    {
        StageLoader.sleep(1000);
        Platform.runLater(() -> {

            System.out.print("UPDATING...");

            boxHero1.setVisible(false);
            boxHero2.setVisible(false);
            boxHero3.setVisible(false);
            boxHero4.setVisible(false);

            statusHero1.setVisible(false);
            statusHero2.setVisible(false);
            statusHero3.setVisible(false);
            statusHero4.setVisible(false);

            if (StageLoader.heros.size() > 0) {
                setupHero((Hero) StageLoader.heros.get(0), imageHero1, nameHero1);
                boxHero1.setVisible(true);
                updateStatus(statusHero1, (Hero) StageLoader.heros.get(0));
            }
            if (StageLoader.heros.size() > 1) {
                setupHero((Hero) StageLoader.heros.get(1), imageHero2, nameHero2);
                boxHero2.setVisible(true);
                updateStatus(statusHero2, (Hero) StageLoader.heros.get(1));
            }
            if (StageLoader.heros.size() > 2) {
                setupHero((Hero) StageLoader.heros.get(2), imageHero3, nameHero3);
                boxHero3.setVisible(true);
                updateStatus(statusHero3, (Hero) StageLoader.heros.get(2));
            }
            if (StageLoader.heros.size() > 3) {
                setupHero((Hero) StageLoader.heros.get(3), imageHero4, nameHero4);
                boxHero4.setVisible(true);
                updateStatus(statusHero4, (Hero) StageLoader.heros.get(3));
            }

            updateInventory();
            updateEnemies();
            updateSpeech("What will " + StageLoader.heros.get(StageLoader.player).getName() + " do ?");
            this.thread.interrupt();
        });
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

    private void updateEnemies()
    {
        int i;
        boxSmaug.setVisible(false);
        boxLurtz.setVisible(false);
        boxAzog.setVisible(false);
        boxShelob.setVisible(false);

        for(i=0;i<StageLoader.enemies.size();i++)
        {
            Enemy current =(Enemy) StageLoader.enemies.get(i);
            int hp = StageLoader.enemies.get(i).getHP();
            if(current.getHP() != 0)
            {
                switch (current.getName())
                {
                    case "Lurtz":
                        boxLurtz.setVisible(true);
                        labelLurtz.setText(" \uF7D0  [" + Integer.toString(hp) + "] " + current.getName());
                        break;
                    case "Smaug":
                        boxSmaug.setVisible(true);
                        labelSmaug.setText(" \uF7D0  [" + Integer.toString(hp) + "] " + current.getName());
                        break;
                    case "Azog":
                        boxAzog.setVisible(true);
                        labelAzog.setText(" \uF7D0  [" + Integer.toString(hp) + "] " + current.getName());
                        break;
                    case "Shelob":
                        boxShelob.setVisible(true);
                        labelShelob.setText(" \uF7D0  [" + Integer.toString(hp) + "] " + current.getName());
                        break;
                }
            }
        }
        if(StageLoader.Smaug && !boxSmaug.isVisible())
        {
            boxSmaug.setVisible(true);
            labelSmaug.setVisible(false);
            imageSmaug.setImage(new Image("/data/imgs/grave.png"));
            imageSmaug.setFitHeight(150);
            imageSmaug.setFitWidth(200);
            imageSmaug.setLayoutX(-14);
            imageSmaug.setLayoutX(26);
        }
        if(StageLoader.Lurtz && !boxLurtz.isVisible())
        {
            boxLurtz.setVisible(true);
            labelLurtz.setVisible(false);
            imageLurtz.setImage(new Image("/data/imgs/grave.png"));
            imageLurtz.setFitHeight(150);
            imageLurtz.setFitWidth(200);
            imageLurtz.setLayoutX(-14);
            imageLurtz.setLayoutX(26);
        }
        if(StageLoader.Azog && !boxAzog.isVisible())
        {
            boxAzog.setVisible(true);
            labelAzog.setVisible(false);
            imageAzog.setImage(new Image("/data/imgs/grave.png"));
            imageAzog.setFitHeight(109);
            imageAzog.setFitWidth(112);
            imageAzog.setLayoutX(9);
            imageAzog.setLayoutX(65);
        }
        if(StageLoader.Shelob && !boxShelob.isVisible())
        {
            boxShelob.setVisible(true);
            labelShelob.setVisible(false);
            imageShelob.setImage(new Image("/data/imgs/grave.png"));
            imageShelob.setFitHeight(150);
            imageShelob.setFitWidth(200);
            imageShelob.setLayoutX(234);
            imageShelob.setLayoutX(41);
            imageShelob.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        }
    }

    private void setupHero(Hero hero, ImageView image, Label name)
    {
        name.setText(hero.getName());
        if(hero instanceof Warrior)
        {
            image.setImage(new Image("/data/imgs/warrior.gif"));
        }
        if(hero instanceof Hunter)
        {
            image.setImage(new Image("/data/imgs/hunter.gif"));
        }
        if(hero instanceof Mage)
        {
            image.setImage(new Image("/data/imgs/mage.gif"));
        }
        if(hero instanceof Healer)
        {
            image.setImage(new Image("/data/imgs/healer.gif"));
        }
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
        System.out.println(StageLoader.consumables.size());
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
        }
        labelOnigiri.setText(Integer.toString(onigiri));
        labelSushis.setText(Integer.toString(sushis));
        labelRamen.setText(Integer.toString(ramen));
        labelLowPotion.setText(Integer.toString(lowPot));
        labelMediumPotion.setText(Integer.toString(medPot));
        labelHighPotion.setText(Integer.toString(highPot));
    }

    private void updateSpeech(String s)
    {
        String t = "";
        for(int i = 0; i<s.length();i++)
        {
            t += s.charAt(i);
            System.out.println(t);
            this.labelSpeech.setText(t);
            //StageLoader.sleep(10);
        }
    }

    @FXML
    protected void attackAzog()
    {
        if(labelAzog.isVisible())
        {
            StageLoader.action = 1;
            StageLoader.currentEnemy = "Azog";
            StageLoader.choiceEnd = true;
            //StageLoader.sleep(100);
            this.thread = new Thread(() -> update());
            this.thread.start();
            //updateEnemies();
        }
    }
    @FXML
    protected void attackSmaug()
    {
        if(labelSmaug.isVisible())
        {
            StageLoader.action = 1;
            StageLoader.currentEnemy = "Smaug";
            StageLoader.choiceEnd = true;
            //StageLoader.sleep(100);
            this.thread = new Thread(() -> update());
            this.thread.start();
            //updateEnemies();
        }

    }
    @FXML
    protected void attackLurtz()
    {
        if(labelLurtz.isVisible())
        {
            StageLoader.action = 1;
            StageLoader.currentEnemy = "Lurtz";
            StageLoader.choiceEnd = true;
            //StageLoader.sleep(100);
            this.thread = new Thread(() -> update());
            this.thread.start();
            //updateEnemies();
        }
    }
    @FXML
    protected void attackShelob()
    {
        if(labelShelob.isVisible())
        {
            StageLoader.action = 1;
            StageLoader.currentEnemy = "Shelob";
            StageLoader.choiceEnd = true;
            //StageLoader.sleep(100);
            this.thread = new Thread(() -> update());
            this.thread.start();
            //updateEnemies();
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
            StageLoader.sleep(1000);
            update();
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
            StageLoader.sleep(1000);
            update();
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
            StageLoader.sleep(1000);
            update();
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
            StageLoader.sleep(1000);
            update();
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
            StageLoader.sleep(1000);
            update();
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
            StageLoader.sleep(1000);
            update();
        }
    }
}