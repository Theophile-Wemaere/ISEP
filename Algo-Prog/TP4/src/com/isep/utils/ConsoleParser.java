package com.isep.utils;

import java.util.ArrayList;
import java.util.Scanner;

import com.isep.rpg.*;
import com.isep.rpg.hero.*;
import com.isep.rpg.item.*;

public class ConsoleParser implements InputParser
{
    public int getHeroNum()
    {
        Scanner scanner = new Scanner(System.in);
        boolean c;
        int choice = 0;
        c = false;
        while(!c)
        {
            System.out.print("\nHow many heros (max 4) : ");
            choice = scanner.nextInt();
            if(choice > 0 && choice <= 4)
            {
                c = true;
            }
        }
        return choice;
    }

    public Hero getHeroClasse()
    {
        Scanner scanner = new Scanner(System.in);
        boolean c;
        int choice = 0;
        // System.out.print("Enter your hero name : ");
        // String name = scanner.nextLine();
        System.out.println("\nChoose your hero classe : ");
        System.out.println("[1] Warrior : powerfull and solid (HP:4|Defense:5|MaxDamage:3)");
        System.out.println("[2] Hunter : fast and precise (HP:4|Defense:4|MaxDamage:3)");
        System.out.println("[3] Mage : master of the arcane arts (HP:4|Defense:3|MaxDamage:5|Mana:7)");
        System.out.println("[4] Healer : the best companion for any fighter (HP:4|Defense:3|MaxDamage:2|Mana:8|Possibility of healing other heros)");
        c = false;
        while(!c)
        {
            System.out.print("Enter your choice : ");
            choice = scanner.nextInt();
            if(choice >= 1 && choice <= 4)
            {
                c = true;
            }
        }
        int weapon;
        switch(choice)
        {
            case 1:
                Warrior warrior = new Warrior("Aragorn");
                System.out.println("\nChoose your weapons : ");
                System.out.println("[1] Giant sword");
                System.out.println("[2] Saber");
                c = false;
                while(!c)
                {
                    System.out.print("Enter your choice : ");
                    weapon = scanner.nextInt();
                    if(weapon == 1 || weapon == 2)
                    {
                        c = true;
                        warrior.chooseWeapons(weapon);
                    }
                }
                return warrior;
            
            case 2:
                Hunter hunter = new Hunter("Legolas");
                System.out.println("\nChoose your weapons : ");
                System.out.println("[1] Bow");
                System.out.println("[2] Crossbow");
                c = false;
                while(!c)
                {
                    System.out.print("Enter your choice : ");
                    weapon = scanner.nextInt();
                    if(weapon == 1 || weapon == 2)
                    {
                        c = true;
                        hunter.chooseWeapons(weapon);
                    }
                }
                return hunter;

            case 3:
                Mage mage = new Mage("Gandalf");
                return mage;

            case 4:
                Healer healer = new Healer("Elrond");
                return healer;
        }
        return null;     
    }

    public int getAction(Hero hero, int size)
    {
        Scanner scanner = new Scanner(System.in);
        boolean c;
        int choice = 0;
        System.out.println("What will " + hero.getName() + " do : ");
        if(hero instanceof SpellCaster)
        {
            System.out.println("[1] Use a spell");
        }        
        else
        {
            System.out.println("[1] Attack");
        }
        System.out.println("[2] Use consumables");
        c = false;
        while(!c)
        {
            System.out.print("Enter your choice : ");
            choice = scanner.nextInt();
            if(choice == 1 || (choice == 2 && size != 0)) // we need to have items in the inventory
            {
                c = true;
            }
        }
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
                c = true;
            }
        }
        hero.chooseSpell(choice);
    }

    public int getTarget(ArrayList<Enemy> enemies)
    {
        Scanner scanner = new Scanner(System.in);
        boolean c;
        int choice = 0;
        System.out.println("\nChoose your target : ");
        for(int i=0;i<enemies.size();i++)
        {
            System.out.println("[" + Integer.toString(i+1) + "]" + " " + enemies.get(i).getName());
        }
        c = false;
        while(!c)
        {
            System.out.print("Enter your choice : ");
            choice = scanner.nextInt();
            if(choice >= 1 && choice <= enemies.size())
            {
                c = true;
            }
        }
        return choice-1;
    }

    public int chooseItem(ArrayList<Consumable> consumables)
    {
        Scanner scanner = new Scanner(System.in);
        boolean c;
        int choice = 0;
        System.out.println("\nWhich item do you want to use : ");
        for(int i=0;i<consumables.size();i++)
        {
            System.out.println("[" + Integer.toString(i+1) + "]" + " " + consumables.get(i).getName() + " - Power : " + Integer.toString(consumables.get(i).getHealingPoints()));
        }
        c = false;
        while(!c)
        {
            System.out.print("Enter your choice : ");
            choice = scanner.nextInt();
            if(choice >= 1 && choice <= consumables.size())
            {
                c = true;
            }
        }
        return choice-1;
    }

    public void waitKey()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nPress any key to continue...\n");
        scanner.nextLine();
    }

}