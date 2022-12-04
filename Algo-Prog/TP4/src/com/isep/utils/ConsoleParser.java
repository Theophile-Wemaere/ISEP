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
        switch(choice)
        {
            case 1:
                Warrior warrior = new Warrior("Aragorn");
                return warrior;
            
            case 2:
                Hunter hunter = new Hunter("Legolas");
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
        boolean c, minMana = false;
        int choice = 0;
        System.out.println("What will " + hero.getName() + " do : ");
        if(hero instanceof SpellCaster)
        {
            if(((SpellCaster) hero).getSpellName() != null )
                System.out.println("Current spell : " + ((SpellCaster) hero).getSpellName() + " - D=" + Integer.toString(((SpellCaster) hero).getSpellDamage()));
            else
                System.out.println("No spell selected");
            System.out.println("[1] Attack");
            System.out.println("[2] Use a spell");
        }        
        else
        {
            if(hero instanceof Warrior)
                if(((Warrior) hero).getWeaponName() != null )
                    System.out.println("Current weapon : " +((Warrior) hero).getWeaponName() + " - D=" + Integer.toString(((Warrior) hero).getDamage()));
                else
                    System.out.println("No weapon selected");
            else if(hero instanceof Hunter)
                if(((Hunter) hero).getWeaponName() != null )
                    System.out.println("Current weapon : " +((Hunter) hero).getWeaponName() + " - D=" + Integer.toString(((Hunter) hero).getDamage()));
                else
                    System.out.println("No weapon selected");
            System.out.println("[1] Attack");
            System.out.println("[2] Choose a weapon");
        }
        System.out.println("[3] Use consumables");
        System.out.println("[4] Pass this tour");
        c = false;
        
        while(!c)
        {
            System.out.print("Enter your choice : ");
            choice = scanner.nextInt();
            if(hero instanceof SpellCaster)
            {
                if(hero instanceof Mage)
                {
                    if(hero.getMana() > 0)
                    {
                        minMana = true;
                    }
                    else
                    {
                        System.out.println("\u001B[33m" + "Carefull, " + hero.getName() + " doesn't have enough mana to use spells" + "\u001B[0m");
                    }
                }
                else if(hero instanceof Healer)
                {
                    if(hero.getMana() > 1)
                    {
                        minMana = true;
                    }
                    else
                    {
                        System.out.println("\u001B[33m" + "Carefull, " + hero.getName() + " doesn't have enough mana to use spells" + "\u001B[0m");
                    }
                }
                if((choice == 1 && ((SpellCaster) hero).getSpellName() != null) && minMana)
                {
                    c = true;
                }
                else if (choice == 1)
                {
                    System.out.println("\u001B[33m" + "Please select a spell first to attack" + "\u001B[0m");
                }
                
                if((choice == 2 && minMana) || (choice == 3 && size != 0) || choice == 4 )
                {
                    c = true;
                }
            }
            else
            {
                if(hero instanceof Warrior)
                {
                    if((choice == 1 && ((Warrior) hero).getWeaponName() != null))
                    {
                        c = true;
                    }
                    else if(choice == 1)
                    {
                        System.out.println("\u001B[33m" + "Please select a weapon first to attack" + "\u001B[0m");
                    }

                    if(choice == 2 || (choice == 3 && size != 0) || choice == 4)
                    {
                        c = true;
                    }
                }
                else if(hero instanceof Hunter)
                {
                    if((choice == 1 && ((Hunter) hero).getWeaponName() != null))
                    {
                        c = true;
                    }
                    else if(choice == 1)
                    {
                        System.out.println("\u001B[33m" + "Please select a weapon first to attack" + "\u001B[0m");
                    }
                    
                    if(choice == 2 || (choice == 3 && size != 0) || choice == 4)
                    {
                        c = true;
                    }
                }
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