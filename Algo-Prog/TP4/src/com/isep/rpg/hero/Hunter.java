package com.isep.rpg.hero;

import com.isep.rpg.item.*;
import com.isep.rpg.*;

public class Hunter extends Hero
{
    private Weapon weapon;
    private int damages;
    private String name;

    public Hunter(String name)
    {
        super(name,0,4); // hunter has 4 defense 
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
        System.out.println(n);
        switch(n)
        {
            case 1:
                item = new Weapon("bow");
                this.weapon = item;
                break;

            case 2:
                item = new Weapon("crossbow");
                this.weapon = item;
                break;
        }
        this.damages = this.weapon.getDamage();
    }

    @Override
    public void chooseItem(Consumable item)
    {
        int hp = item.getHealingPoints();
        super.gainHP(hp, item);
    }

    @Override
    public void changeDamages(int n)
    {
        this.damages += n;
    }

    public String getWeaponName()
    {
         if(this.weapon != null)
            return this.weapon.getName();
        else
            return null;   
    }

    public int getDamage()
    {
        return this.damages;
    }
}