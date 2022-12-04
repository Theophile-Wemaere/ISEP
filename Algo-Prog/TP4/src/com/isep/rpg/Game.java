package com.isep.rpg;

import com.isep.rpg.item.*;
import com.isep.rpg.hero.*;
import com.isep.utils.InputParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game
{
    private ArrayList<Consumable> consumables = new ArrayList<>();
    private ArrayList<Hero> heros = new ArrayList<>();
    private ArrayList<Enemy> enemies = new ArrayList<>();

    private ArrayList<String> EnemiesNames = new ArrayList<>();
    private ArrayList<String> BossNames = new ArrayList<>();

    private int number; // number of heros
    private boolean playing = true;

    private InputParser parser;

    public Game(InputParser parser) // n = number of heros (default 3)
    {
        clearConsole();
        this.parser = parser;
        this.number = this.parser.getHeroNum();
        initialiseInventory(this.number);
        initialiseNames();

        // get the heros
        for(int i=0;i<this.number;i++)
        {
            clearConsole();
            this.heros.add(parser.getHeroClasse());
        }

        // get the enemy
        for(int i=0;i<this.number;i++)
        {
            Random random = new Random();
            int d = random.nextInt(7 - 5 + 1) + 5; // generate number in [5;7], ranbom defense value
            int max = EnemiesNames.size() - 1;
            int n = random.nextInt(max - 0 + 1) + 0; // generate number in [0;max], random name
            Enemy enemy = new Enemy(this.EnemiesNames.get(n),d,3); // 3 damage
            this.enemies.add(enemy);
            this.EnemiesNames.remove(n);
        }
    }

    public void start()
    {
        while(this.playing)
        {
            Random random = new Random();
            for(int i=0; i < heros.size(); i++)
            {
                clearConsole();
                printInventory();
                printHeros();
                printEnemies();

                Hero current = heros.get(i);
                System.out.println("\u001B[31m" + current.getName() + "\u001B[0m" + " has to play !\n");

                int choice = this.parser.getAction(current);
                
                switch(choice)
                {
                    case 1: // attack mode
                        if(current instanceof SpellCaster)
                        {
                            parser.chooseSpell((SpellCaster) current);
                        }
                        int target = parser.getTarget(this.enemies);
                        current.fight(this.enemies.get(target));
                        if(this.enemies.get(target).getHP() == 0)
                        {
                            this.enemies.remove(target);
                        }
                        break;
                    case 2:
                        break;
                }
                
                if(this.enemies.size() == 0)
                {
                    System.out.println("\nCongratulations, you've won !\n");
                    this.playing = false;
                }
                else
                {
                    parser.waitKey();
                }
            }
        }

    }

    public void clearConsole()                                                                                                       
    {                                                                                                                                       
        System.out.print("\033[H\033[2J");                                                                                                  
        System.out.flush();                                                                                                                 
    }

    private void printInventory()
    {
        System.out.print("Inventory -> [");
        for(int i=0;i<this.consumables.size();i++)
        {
            if(i!=0)
            {
                System.out.print(" |");
            }
            System.out.print(" " + consumables.get(i).getName());
        }
        System.out.println(" ]");
    }

    private void printHeros()
    {
        System.out.print("Heros     -> [");
        for(int i=0;i<this.heros.size();i++)
        {
            if(i!=0)
            {
                System.out.print(" |");
            }
            Combatant current = heros.get(i);
            System.out.print(" " + current.getName() + "(" + Integer.toString(current.getHP()) + "-" + Integer.toString(current.getDefense()) + "-" + Integer.toString(current.getMana()) + ")");
        }
        System.out.println(" ]");
    }

    private void printEnemies()
    {
        System.out.print("Enemies   -> [");
        for(int i=0;i<this.enemies.size();i++)
        {
            if(i!=0)
            {
                System.out.print(" |");
            }
            Combatant current = enemies.get(i);
            System.out.print(" " + current.getName() + "(" + Integer.toString(current.getHP()) + "-" + Integer.toString(current.getDefense()) + ")");
        }
        System.out.println(" ]\n");
    }

    private void initialiseInventory(int n) // return random items to add to inventory
    {
        Random random = new Random();
        int i = 0;
        while(i<n)
        {
            int r = random.nextInt(5 - 0 + 1) + 0; // generate number in [0;5]
            // 0 = simple healing potion
            // 1 = medium healing potion
            // 2 = high healing potion
            // 3 = onigiri
            // 4 = sushis
            // 5 = ramen
            switch(r)
            {
                case 0:
                    this.consumables.add(new Potion("simple healing potion"));
                    break;
                case 1:
                    this.consumables.add(new Potion("medium healing potion"));
                    break;
                case 2:
                    this.consumables.add(new Potion("high healing potion"));
                    break;
                case 3:
                    this.consumables.add(new Food("onigiri"));
                    break;
                case 4:
                    this.consumables.add(new Food("sushis"));
                    break;
                case 5:
                    this.consumables.add(new Food("ramen"));
                    break;
            }
            i++;
        }
    }

    private void initialiseNames()
    {
        // nerd references :)
        this.EnemiesNames.add("Lurtz");
        this.EnemiesNames.add("Shelob");
        this.EnemiesNames.add("Azog");
        this.EnemiesNames.add("Smaug");

        this.BossNames.add("Sachiel");
        this.BossNames.add("Shamshel");
        this.BossNames.add("Ramiel");
    }
}