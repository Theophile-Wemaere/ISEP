package com.isep.utils;

import java.util.ArrayList;
import com.isep.rpg.*;
import com.isep.rpg.hero.*;
import com.isep.rpg.item.*;
import com.isep.utils.scenecontrollers.*;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.LineUnavailableException;

public class GUIParser extends Application implements InputParser
{
    private Stage stage;
    private int heroNumber = 1;
    @Override
    public void start(Stage stage) throws IOException
    {
        // -_-_-_-_-_-_-_-_-_- load the fxml scene -_-_-_-_-_-_-_-_-_-
        this.stage = stage;
        StageLoader.setStage(stage);
        StageLoader.loadFXMLScene("/data/scenes/welcomeMenu.fxml");
    }

    public void launchInterface()
    {
        launch();
    }

    public int getHeroNum()
    {
        boolean end = false;
        while(!end)
        {
            StageLoader.sleep(500);
            end = StageLoader.choiceEnd;
        }
        StageLoader.choiceEnd = false;
        return StageLoader.herosNumber;
    }

    public Hero getHeroClasse()
    {
        switch(this.heroNumber)
        {
            case 1:
                this.heroNumber = 2;
                return StageLoader.hero1;
            case 2:
                this.heroNumber = 3;
                return StageLoader.hero2;
            case 3:
                this.heroNumber = 4;
                return StageLoader.hero3;
            case 4:
                this.heroNumber = 5;
                return StageLoader.hero4;
        }

        return null;
    }

    public int getAction(Hero hero, int size)
    {
        boolean end = false;
        while(!end)
        {
            StageLoader.sleep(500);
            end = StageLoader.choiceEnd;
        }
        int choice = StageLoader.action;
        return choice;
    }

    public void chooseSpell(SpellCaster hero)
    {
        Scanner scanner = new Scanner(System.in);
        boolean c;
        int max = 0, choice = 0;
        System.out.println("\nChoose your spell : ");
        if(hero instanceof Healer)
        {
            System.out.println("[1] Holy ray -> Damage = 2, Mana cost = 2");
            System.out.println("[2] Healing touch -> Heal = 2, Mana cost = 2");
            max = 2;
        }
        else if(hero instanceof Mage)
        {
            System.out.println("[1] Ice pick -> Damage = 2, Mana cost = 1");
            System.out.println("[2] Lightning -> Damage = 3, Mana cost = 3");
            System.out.println("[3] Fire storm -> Damage = 5, Mana cost = 5");
            max = 3;
        }
        c = false;
        while(!c)
        {
            System.out.print("Enter your choice : ");
            choice = scanner.nextInt();
            if(choice >= 1 && choice <= max)
            {
                hero.chooseSpell(choice);
                int manaCost = hero.getSpellCost();
                int manaAmount = hero.getMana();
                if(manaCost <= manaAmount)
                {
                    c = true;
                }
                else
                {
                    System.out.println("\u001B[33m" + "Carefull, " + hero.getName() + " doesn't have enough mana for this spell" + "\u001B[0m");
                }
            }
        }

    }

    public void chooseWeapon(Hero hero)
    {
        Scanner scanner = new Scanner(System.in);
        boolean c = false;
        int weapon;
        System.out.println("\nChoose your weapons : ");
        if(hero instanceof Warrior)
        {
            System.out.println("[1] Saber | 2 damages");
            System.out.println("[2] Giant sword | 3 damages (1/5 of failing the attack)");
            c = false;
            while(!c)
            {
                System.out.print("Enter your choice : ");
                weapon = scanner.nextInt();
                if(weapon == 1 || weapon == 2)
                {
                    c = true;
                    ((Warrior) hero).chooseWeapons(weapon);
                }
            }
        }
        else if(hero instanceof Hunter)
        {
            System.out.println("[1] Bow | 2 damages");
            System.out.println("[2] Crossbow | 3 damages (1/5 of failing the attack)");
            c = false;
            while(!c)
            {
                System.out.print("Enter your choice : ");
                weapon = scanner.nextInt();
                if(weapon == 1 || weapon == 2)
                {
                    c = true;
                    ((Hunter) hero).chooseWeapons(weapon);
                }
            }
        }
    }

    public int getTarget(ArrayList<Combatant> targets)
    {
        Scanner scanner = new Scanner(System.in);
        boolean c;
        int choice = 0;
        System.out.println("\nChoose your target : ");
        for(int i=0;i<targets.size();i++)
        {
            System.out.println("[" + Integer.toString(i+1) + "]" + " " + targets.get(i).getName() + "(" + Integer.toString(targets.get(i).getHP()) +")");
        }
        c = false;
        while(!c)
        {
            System.out.print("Enter your choice : ");
            choice = scanner.nextInt();
            if(choice >= 1 && choice <= targets.size())
            {
                c = true;
            }
        }
        return choice-1;
    }

    public int chooseItem(ArrayList<Consumable> consumables)
    {
        String c = StageLoader.consumable2use;
        int choice;
        for(int i = 0;i<consumables.size();i++)
        {
            Consumable current = consumables.get(i);
            if(current.getName().equals(c));
            {
                System.out.println("Using " + c);
                choice = i;
                return choice;
            }
        }
        return -1;
    }

    public void waitKey()
    {
        StageLoader.choiceEnd = false;
    }
}