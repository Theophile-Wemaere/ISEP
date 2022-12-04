package com.isep.rpg.hero;

import com.isep.rpg.item.*;
import com.isep.rpg.*;

public class Warrior extends Hero
{
    private Weapon weapon;
    private int damages;
    private String name;

    public Warrior(String name)
    {
        super(name,0,5); // warrior has 5 defense
        this.name = name;
    }

    @Override
    public void fight(Combatant target)
    {
        boolean doFight = this.weapon.doFight();
        if(doFight)
        {
            System.out.println("\n" + this.name + " inflicts " + Integer.toString(this.damages) + " damages to " + target.getName());
            target.Damage(this.damages);
        }
        else
        {
            System.out.println("\n" + this.name + " miss his attack using " + this.weapon.getName());
        }
    }

    public void chooseWeapons(int n)
    {
        Weapon item;
        switch(n)
        {
            case 1:
                item = new Weapon("giant sword");
                this.weapon = item;
                break;

            case 2:
                item = new Weapon("saber");
                this.weapon = item;
                break;
        }
        this.damages = this.weapon.getDamage();
    }

    @Override
    public void chooseItem(Consumable item)
    {
        int hp = item.getHealingPoints();
        super.gainHP(hp,item);
    }

    @Override
    public void changeDamages(int n)
    {
        this.damages += n;
    }
}