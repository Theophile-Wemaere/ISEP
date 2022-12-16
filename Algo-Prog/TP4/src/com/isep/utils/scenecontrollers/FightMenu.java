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
import javafx.scene.effect.Bloom;
import javafx.scene.effect.Effect;
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
    private Label labelWeaponHunter,labelBow,labelDamageBow,labelCrossbow,labelDamageCrossbow,labelMalusCrossbow;
    @FXML
    private Label labelWeaponWarrior,labelSaber,labelDamageSaber,labelSword,labelDamageSword,labelMalusSword;
    @FXML
    private Label labelSpellMage, labelDamageIce, labelCostIce, labelDamageLightning, labelCostLightning, labelDamageFire, labelCostFire;
    @FXML
    private Label labelSpellHealer, labelDamageLight, labelCostLight, labelDamageHeal, labelCostHeal, labelWeapon, currentDamage, currentCost;
    @FXML
    private AnchorPane weaponHunter, weaponWarrior, spellMage, spellHealer;
    @FXML
    private AnchorPane boxHero1,boxHero2,boxHero3,boxHero4,boxAzog,boxSmaug,boxShelob,boxLurtz,boxBag;
    @FXML
    private Button sound,buttonCloseBag,closeWarrior,closeHunter,closeMage,closeHealer;
    @FXML
    private ImageView imageHero1,imageHero2,imageHero3,imageHero4,imageBag, currentWeapon;

    @FXML
    ImageView imageSmaug, imageShelob, imageAzog, imageLurtz;
    @FXML
    ImageView graveSmaug,graveShelob,graveAzog,graveLurtz;

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

        labelWeapon.setFont(font);
        currentDamage.setFont(font);
        currentCost.setFont(font);

        statusHero1.setFont(font);
        statusHero2.setFont(font);
        statusHero3.setFont(font);
        statusHero4.setFont(font);

        labelShelob.setFont(font);
        labelSmaug.setFont(font);
        labelAzog.setFont(font);
        labelLurtz.setFont(font);

        labelSpeech.setFont(font);

        labelMalusSword.setFont(font);
        labelMalusCrossbow.setFont(font);

        Font font2 = Font.loadFont(new FileInputStream("src/data/fonts/TheWildBreathOfZelda-15Lv.ttf"), 30);
        labelInventory.setFont(font2);
        labelOnigiri.setFont(font2);
        labelSushis.setFont(font2);
        labelRamen.setFont(font2);
        labelLowPotion.setFont(font2);
        labelMediumPotion.setFont(font2);
        labelHighPotion.setFont(font2);

        labelWeaponHunter.setFont(font2);
        labelWeaponWarrior.setFont(font2);

        labelSword.setFont(font2);
        labelDamageSword.setFont(font2);


        labelSaber.setFont(font2);
        labelDamageSaber.setFont(font2);

        labelBow.setFont(font2);
        labelDamageBow.setFont(font2);

        labelCrossbow.setFont(font2);
        labelDamageCrossbow.setFont(font2);

        Font font3 = Font.loadFont(new FileInputStream("src/data/fonts/TheWildBreathOfZelda-15Lv.ttf"), 23);
        labelSpellMage.setFont(font2);
        labelSpellHealer.setFont(font2);

        labelDamageIce.setFont(font3);
        labelCostIce.setFont(font3);

        labelDamageLightning.setFont(font3);
        labelCostLightning.setFont(font3);

        labelDamageFire.setFont(font3);
        labelCostFire.setFont(font3);

        labelDamageLight.setFont(font3);
        labelCostLight.setFont(font3);

        labelDamageHeal.setFont(font3);
        labelCostHeal.setFont(font3);

        closeHunter.setFont(Font.loadFont(new FileInputStream("src/data/fonts/MesloLGS-NF.ttf"), 40));
        closeWarrior.setFont(Font.loadFont(new FileInputStream("src/data/fonts/MesloLGS-NF.ttf"), 40));
        closeMage.setFont(Font.loadFont(new FileInputStream("src/data/fonts/MesloLGS-NF.ttf"), 40));
        closeHealer.setFont(Font.loadFont(new FileInputStream("src/data/fonts/MesloLGS-NF.ttf"), 40));
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

        sound.setFont(Font.loadFont(new FileInputStream("src/data/fonts/MesloLGS-NF.ttf"), 20));
        this.clip.open(AudioSystem.getAudioInputStream(new File("src/data/musics/enemyAudio.wav")));
        if(StageLoader.sound)
            this.clip.loop(Clip.LOOP_CONTINUOUSLY);
        else
            sound.setText("\uF026");
        updateWeapon();
        updateInventory();
        highLightHero();
        updateSpeech("What will " + StageLoader.heros.get(StageLoader.player).getName() + " do ?");
    }

    public void update()
    {
        StageLoader.sleep(1000);
        Platform.runLater(() -> {

            boxHero1.setVisible(false);
            boxHero2.setVisible(false);
            boxHero3.setVisible(false);
            boxHero4.setVisible(false);

            statusHero1.setVisible(false);
            statusHero2.setVisible(false);
            statusHero3.setVisible(false);
            statusHero4.setVisible(false);

            closeBag();
            closeWarrior();
            closeHunter();
            closeMage();
            closeHealer();

            if (StageLoader.heros.size() == 0) {
                System.out.println("heros are dead");
                System.exit(0);
            }

            if (StageLoader.doBoss)
            {
                System.out.println("all enemies are dead ----> boss");
                if(StageLoader.sound)
                {
                    this.clip.stop();
                }
                try {
                    StageLoader.loadFXMLScene("/data/scenes/bossRamiel.fxml");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

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
            updateWeapon();
            highLightHero();

            updateSpeech("What will " + StageLoader.heros.get(StageLoader.player).getName() + " do ?");
            this.thread.interrupt();
        });
    }

    private void highLightHero()
    {
        Bloom bloom = new Bloom();
        bloom.setThreshold(0);

        imageHero1.setEffect(null);
        imageHero2.setEffect(null);
        imageHero3.setEffect(null);
        imageHero4.setEffect(null);

        switch(StageLoader.player)
        {
            case 0:
                imageHero1.setEffect(bloom);
                break;
            case 1:
                imageHero2.setEffect(bloom);
                break;
            case 2:
                imageHero3.setEffect(bloom);
                break;
            case 3:
                imageHero4.setEffect(bloom);
                break;
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

    private void updateWeapon()
    {
        currentCost.setVisible(false);
        labelWeapon.setText("Current Weapon");
        Hero current = (Hero) StageLoader.heros.get(StageLoader.player);
        if(current instanceof Warrior)
        {
            String name = ((Warrior) current).getWeaponName();
            int d = ((Warrior) current).getDamage();
            currentDamage.setText("Damage : " + Integer.toString(d));
            switch(name)
            {
                case "giant sword":
                    currentWeapon.setImage(new Image("/data/imgs/sword.png"));
                    break;
                case "saber":
                    currentWeapon.setImage(new Image("/data/imgs/saber.png"));
                    break;
            }
        }
        else if(current instanceof Hunter)
        {
            String name = ((Hunter) current).getWeaponName();
            int d = ((Hunter) current).getDamage();
            currentDamage.setText("Damage : " + Integer.toString(d));
            switch(name)
            {
                case "bow":
                    currentWeapon.setImage(new Image("/data/imgs/bow.png"));
                    break;
                case "crossbow":
                    currentWeapon.setImage(new Image("/data/imgs/crossbow.png"));
                    break;
            }
        }
        else if(current instanceof SpellCaster)
        {
            String name = ((SpellCaster) current).getSpellName();
            labelWeapon.setText("Current Spell");
            currentCost.setVisible(true);
            int d = ((SpellCaster) current).getSpellDamage();
            currentDamage.setText("Damage : " + Integer.toString(d));
            d = ((SpellCaster) current).getSpellCost();
            currentCost.setText("Mana cost : " + Integer.toString(d));
            switch(name)
            {
                case "ice pick":
                    currentWeapon.setImage(new Image("/data/imgs/icepick.gif"));
                    break;
                case "lightning":
                    currentWeapon.setImage(new Image("/data/imgs/lightning.gif"));
                    break;
                case "fire storm":
                    currentWeapon.setImage(new Image("/data/imgs/firestorm.gif"));
                    break;
                case "holy ray":
                    currentWeapon.setImage(new Image("/data/imgs/lightray.gif"));
                    break;
                case "healing touch":
                    currentWeapon.setImage(new Image("/data/imgs/healingtouch.gif"));
                    break;
            }
        }
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
            graveSmaug.setVisible(true);
        }
        if(StageLoader.Lurtz && !boxLurtz.isVisible())
        {
            graveLurtz.setVisible(true);
        }
        if(StageLoader.Azog && !boxAzog.isVisible())
        {
            graveAzog.setVisible(true);
        }
        if(StageLoader.Shelob && !boxShelob.isVisible())
        {
            graveShelob.setVisible(true);
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
            status.setText(" \uF7D0  " + Integer.toString(hp) + " 聯  " + Integer.toString(def) + " 懲" + Integer.toString(mana) + " " + hero.getName());
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
        labelSpeech.setText(s);
    }

    @FXML
    protected void onHero1Clicked()
    {
        if(StageLoader.heros.get(StageLoader.player) instanceof Healer && ((Healer) StageLoader.heros.get(StageLoader.player)).getSpellName().equals("healing touch"))
        {
            StageLoader.action = 1;
            StageLoader.currentTarget = StageLoader.heros.get(0).getName();
            StageLoader.choiceEnd = true;
            this.thread = new Thread(() -> update());
            this.thread.start();
        }
    }
    @FXML
    protected void onHero2Clicked()
    {
        if(StageLoader.heros.get(StageLoader.player) instanceof Healer && ((Healer) StageLoader.heros.get(StageLoader.player)).getSpellName().equals("healing touch"))
        {
            StageLoader.action = 1;
            StageLoader.currentTarget = StageLoader.heros.get(1).getName();
            StageLoader.choiceEnd = true;
            this.thread = new Thread(() -> update());
            this.thread.start();
        }
    }
    @FXML
    protected void onHero3Clicked()
    {
        if(StageLoader.heros.get(StageLoader.player) instanceof Healer && ((Healer) StageLoader.heros.get(StageLoader.player)).getSpellName().equals("healing touch"))
        {
            StageLoader.action = 1;
            StageLoader.currentTarget = StageLoader.heros.get(2).getName();
            StageLoader.choiceEnd = true;
            this.thread = new Thread(() -> update());
            this.thread.start();
        }
    }
    @FXML
    protected void onHero4Clicked()
    {
        if(StageLoader.heros.get(StageLoader.player) instanceof Healer && ((Healer) StageLoader.heros.get(StageLoader.player)).getSpellName().equals("healing touch"))
        {
            StageLoader.action = 1;
            StageLoader.currentTarget = StageLoader.heros.get(3).getName();
            StageLoader.choiceEnd = true;
            this.thread = new Thread(() -> update());
            this.thread.start();
        }
    }
    @FXML
    protected void closeWarrior()
    {
        weaponWarrior.setVisible(false);
    }
    @FXML
    protected void closeHunter()
    {
        weaponHunter.setVisible(false);
    }
    @FXML
    protected void closeMage()
    {
        spellMage.setVisible(false);
    }
    @FXML
    protected void closeHealer()
    {
        spellHealer.setVisible(false);
    }

    @FXML
    protected void openWeapon()
    {
        if(boxBag.isVisible())
            boxBag.setVisible(false);

        if(StageLoader.heros.get(StageLoader.player) instanceof Hunter)
        {
            if(weaponHunter.isVisible())
                weaponHunter.setVisible(false);
            else
                weaponHunter.setVisible(true);
        }
        else if(StageLoader.heros.get(StageLoader.player) instanceof Warrior)
        {
            if(weaponWarrior.isVisible())
                weaponWarrior.setVisible(false);
            else
                weaponWarrior.setVisible(true);
        }
        else if(StageLoader.heros.get(StageLoader.player) instanceof Mage)
        {
            if(spellMage.isVisible())
                spellMage.setVisible(false);
            else
                spellMage.setVisible(true);
        }
        else if(StageLoader.heros.get(StageLoader.player) instanceof Healer)
        {
            if(spellHealer.isVisible())
                spellHealer.setVisible(false);
            else
                spellHealer.setVisible(true);
        }
    }
    @FXML
    protected void onSaberClicked()
    {
        Hero hero = (Hero) StageLoader.heros.get(StageLoader.player);
        ((Warrior) hero).chooseWeapons(1);
        weaponWarrior.setVisible(false);
        updateWeapon();
    }

    @FXML
    protected void onSwordClicked()
    {
        Hero hero = (Hero) StageLoader.heros.get(StageLoader.player);
        ((Warrior) hero).chooseWeapons(2);
        weaponWarrior.setVisible(false);
        updateWeapon();
    }

    @FXML
    protected void onBowClicked()
    {
        Hero hero = (Hero) StageLoader.heros.get(StageLoader.player);
        ((Hunter) hero).chooseWeapons(1);
        weaponHunter.setVisible(false);
        updateWeapon();
    }

    @FXML
    protected void onCrossbowClicked()
    {
        Hero hero = (Hero) StageLoader.heros.get(StageLoader.player);
        ((Hunter) hero).chooseWeapons(2);
        weaponHunter.setVisible(false);
        updateWeapon();
    }

    @FXML
    protected void onIceClicked()
    {
        Hero hero = (Hero) StageLoader.heros.get(StageLoader.player);
        ((Mage) hero).chooseSpell(1);
        spellMage.setVisible(false);
        updateWeapon();
    }
    @FXML
    protected void onLightningClicked()
    {
        Hero hero = (Hero) StageLoader.heros.get(StageLoader.player);
        ((Mage) hero).chooseSpell(2);
        spellMage.setVisible(false);
        updateWeapon();
    }
    @FXML
    protected void onFireClicked()
    {
        Hero hero = (Hero) StageLoader.heros.get(StageLoader.player);
        ((Mage) hero).chooseSpell(3);
        spellMage.setVisible(false);
        updateWeapon();
    }
    @FXML
    protected void onLightClicked()
    {
        Hero hero = (Hero) StageLoader.heros.get(StageLoader.player);
        ((Healer) hero).chooseSpell(1);
        spellHealer.setVisible(false);
        updateWeapon();
    }
    @FXML
    protected void onHealClicked()
    {
        Hero hero = (Hero) StageLoader.heros.get(StageLoader.player);
        ((Healer) hero).chooseSpell(2);
        spellHealer.setVisible(false);
        updateWeapon();
    }

    @FXML
    protected void attackAzog()
    {
        if(labelAzog.isVisible() && !(StageLoader.heros.get(StageLoader.player) instanceof Healer && ((Healer)StageLoader.heros.get(StageLoader.player)).getSpellName().equals("healing touch")))
        {
            StageLoader.action = 1;
            StageLoader.currentEnemy = "Azog";
            StageLoader.choiceEnd = true;
            this.thread = new Thread(() -> update());
            this.thread.start();
        }
    }
    @FXML
    protected void attackSmaug()
    {
        if(labelSmaug.isVisible() && !(StageLoader.heros.get(StageLoader.player) instanceof Healer && ((Healer)StageLoader.heros.get(StageLoader.player)).getSpellName().equals("healing touch")))
        {
            StageLoader.action = 1;
            StageLoader.currentEnemy = "Smaug";
            StageLoader.choiceEnd = true;
            this.thread = new Thread(() -> update());
            this.thread.start();
        }

    }
    @FXML
    protected void attackLurtz()
    {
        if(labelLurtz.isVisible() && !(StageLoader.heros.get(StageLoader.player) instanceof Healer && ((Healer)StageLoader.heros.get(StageLoader.player)).getSpellName().equals("healing touch")))
        {
            StageLoader.action = 1;
            StageLoader.currentEnemy = "Lurtz";
            StageLoader.choiceEnd = true;
            this.thread = new Thread(() -> update());
            this.thread.start();
        }
    }
    @FXML
    protected void attackShelob()
    {
        if(labelShelob.isVisible() && !(StageLoader.heros.get(StageLoader.player) instanceof Healer && ((Healer)StageLoader.heros.get(StageLoader.player)).getSpellName().equals("healing touch")))
        {
            StageLoader.action = 1;
            StageLoader.currentEnemy = "Shelob";
            StageLoader.choiceEnd = true;
            this.thread = new Thread(() -> update());
            this.thread.start();
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
            this.thread = new Thread(() -> update());
            this.thread.start();
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
            this.thread = new Thread(() -> update());
            this.thread.start();
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
            this.thread = new Thread(() -> update());
            this.thread.start();
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
            this.thread = new Thread(() -> update());
            this.thread.start();
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
            this.thread = new Thread(() -> update());
            this.thread.start();
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
            this.thread = new Thread(() -> update());
            this.thread.start();
        }
    }
}