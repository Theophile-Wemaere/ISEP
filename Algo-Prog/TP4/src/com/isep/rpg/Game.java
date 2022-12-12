package com.isep.rpg;

import com.isep.rpg.item.*;
import com.isep.rpg.hero.*;
import com.isep.utils.ConsoleParser;
import com.isep.utils.GUIParser;
import com.isep.utils.InputParser;
import com.isep.utils.scenecontrollers.StageLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game
{
    private ArrayList<Consumable> consumables = new ArrayList<>();
    private ArrayList<Combatant> heros = new ArrayList<>();
    private ArrayList<Combatant> enemies = new ArrayList<>();
    private ArrayList<Combatant> boss = new ArrayList<>();

    private ArrayList<String> EnemiesNames = new ArrayList<>();
    private ArrayList<String> BossNames = new ArrayList<>();

    private int number; // number of heros
    private boolean playing = true, doBoss = false;

    // private Enemy boss;

    private InputParser parser;

    public Game(InputParser parser) // n = number of heros (default 3)
    {
        if(parser instanceof GUIParser)
        {
            (new Thread(){
                public void run(){
                    ((GUIParser) parser).launchInterface();
                }
            }).start();
        }
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
            int d = random.nextInt(7 - 5 + 1) + 5; // generate number in [5;7], random defense value
            int max = this.EnemiesNames.size() - 1;
            int n = random.nextInt(max - 0 + 1) + 0; // generate number in [0;max], random name
            Enemy enemy = new Enemy(this.EnemiesNames.get(n),5+d);
            this.enemies.add(enemy);
            this.EnemiesNames.remove(n);
        }

        // get the boss
        Random random = new Random();
        int max = this.BossNames.size() - 1;
        int n = random.nextInt(max - 0 + 1) + 0; // generate number in [0;max], random name
        Enemy boss = new Enemy(this.BossNames.get(n),20);
        this.boss.add(boss);

        StageLoader.enemies = (ArrayList<Combatant>) this.enemies.clone();
        StageLoader.boss = (ArrayList<Combatant>) this.boss.clone();
        StageLoader.heros = (ArrayList<Combatant>) this.heros.clone();
        StageLoader.consumables = (ArrayList<Consumable>) this.consumables.clone();
    }

    public void start()
    {
        while(this.playing)
        {
            // heros plays
            playHero();

            if(this.doBoss && this.playing)
            {
                System.out.println("\u001B[33m" + "The boss strikes back !" + "\u001B[0m");
                playBoss();
            }
            else if(this.playing)
            {
                // enemies plays
                System.out.println("\u001B[33m" + "The enemies strike back !" + "\u001B[0m");
                playEnemy();
            }

            parser.waitKey();
            healDefense();
        }
    }

    private void playHero()
    {
        Random random = new Random();
        ArrayList<Combatant> currentEnemy = new ArrayList<>();
        for(int i=0; i < this.heros.size(); i++)
        {
            Hero current = (Hero) heros.get(i);
            clearConsole();
            if(this.parser instanceof ConsoleParser)
            {
                printInventory();
                printHeros();
            }
            if(!this.doBoss)
            {
                if(this.parser instanceof ConsoleParser)
                    printEnemies();
                currentEnemy = (ArrayList<Combatant>) this.enemies.clone();
            }
            else
            {
                if(this.parser instanceof ConsoleParser)
                    printBoss();
                currentEnemy = (ArrayList<Combatant>) this.boss.clone();
            }

            System.out.println("\u001B[31m" + current.getName() + "\u001B[0m" + " has to play !\n");

            int choice = this.parser.getAction(current,this.consumables.size());
            int target = 0;
            boolean actionFinished = false;
            while(!actionFinished)
            {
                switch(choice)
                {
                    case 1: // attack mode
                        if(current instanceof SpellCaster)
                        {
                            if(current instanceof Healer)
                            {
                                if(((SpellCaster) current).getSpellName().equals("healing touch"))
                                {
                                    target = parser.getTarget(this.heros);
                                    current.fight(this.heros.get(target));
                                }
                                else
                                {
                                    target = parser.getTarget(currentEnemy);
                                    current.fight(currentEnemy.get(target));
                                    if(currentEnemy.get(target).getHP() == 0)
                                    {
                                        currentEnemy.remove(target);
                                    }
                                }
                            }
                        }
                        if(!(current instanceof Healer))
                        {
                            target = parser.getTarget(currentEnemy);
                            current.fight(currentEnemy.get(target));
                        
                            if(currentEnemy.get(target).getHP() == 0)
                            {
                                currentEnemy.remove(target);
                            }
                        }
                        actionFinished = true;
                        break;

                    case 2:
                        if(current instanceof SpellCaster)
                        {
                            parser.chooseSpell((SpellCaster) current);
                        }
                        else
                        {
                            parser.chooseWeapon(current);
                        }
                        choice = 1; // move to attack mode
                        break;

                    case 3:
                        int item = parser.chooseItem(this.consumables);
                        current.chooseItem(this.consumables.get(item));
                        this.consumables.remove(item);
                        actionFinished = true;
                        break;

                    case 4:
                        // pass
                        actionFinished = true;
                        break;
                }
            }

            if(currentEnemy.size() == 0 && !this.doBoss)
            {
                System.out.println("\u001B[33m" + "All the enemies are dead, the final boss is waiting..." + "\u001B[0m");
                this.doBoss = true;
            }
            else if(this.doBoss && currentEnemy.size() == 0)
            {
                System.out.println("\nCongratulations, you've won !\n");
                this.doBoss = false;
                this.playing = false;
            }
            else
            {
                parser.waitKey();
                // update the enemies or boss lists
                if(!this.doBoss)
                    this.enemies = (ArrayList<Combatant>) currentEnemy.clone();
                else
                    this.boss = (ArrayList<Combatant>) currentEnemy.clone();
            }
        }
    }

    private void playEnemy()
    {
        Random random = new Random();
        for(int i=0; i < this.enemies.size(); i++)
        {
            int max = this.heros.size() - 1;
            int t = random.nextInt(max - 0 + 1) + 0; // generate number in [0;max], random target
            Enemy current = (Enemy) this.enemies.get(i);
            System.out.println(current.getName() + " inflict " + Integer.toString(current.getDamage()) + " damages to " + this.heros.get(t).getName());
            this.enemies.get(i).fight(this.heros.get(t));
            if(this.heros.get(t).getHP() == 0)
            {
                this.heros.remove(t);
            }

            if(this.heros.size() == 0)
            {
                System.out.println("\nAll your heros are dead, darkness has won...");
                this.playing = false;
            }
        }
    }

    private void playBoss()
    {
        Random random = new Random();
        for(int i=0; i < (this.heros.size()/2)+0.5; i++)
        {
            int max = this.heros.size() - 1;
            int t = random.nextInt(max - 0 + 1) + 0; // generate number in [0;max], random target
            Enemy current = (Enemy) this.boss.get(0);
            System.out.println(current.getName() + " inflict " + Integer.toString(current.getDamage()) + " damages to " + this.heros.get(t).getName());
            current.fight(this.heros.get(t));
            if(this.heros.get(t).getHP() == 0)
            {
                this.heros.remove(t);
            }
        }

        if(this.heros.size() == 0)
        {
            System.out.println("\nAll your heros are dead, darkness has won...");
            System.out.println("\u001B[33m" + "通常兵器はエンジェルズに勝てない" + "\u001B[0m");
            this.playing = false;
        }

    }

    private void clearConsole()                                                                                                       
    {                                                                                                                                       
        System.out.print("\033[H\033[2J");                                                                                                  
        System.out.flush();                                                                                                                 
    }

    private void healDefense()
    {
        for(int i=0;i<this.heros.size();i++)
        {
            Hero current = (Hero) this.heros.get(i);
            current.gainDefense(2); // gain 2 defense each round
        }
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
            System.out.print(" " + current.getName() + "(" + Integer.toString(current.getHP()) + ")");
        }
        System.out.println(" ]\n");
    }

    private void printBoss()
    {
        System.out.print("Final Boss-> [");
        System.out.print(" " + this.boss.get(0).getName() + "(" + Integer.toString(this.boss.get(0).getHP()) + ")");
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
