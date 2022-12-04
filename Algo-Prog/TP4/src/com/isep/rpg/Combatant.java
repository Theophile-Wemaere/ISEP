package com.isep.rpg;

import com.isep.rpg.item.*;

public abstract class Combatant
{
    private String name;
    private int healthPoint, maxHealthPoint, defense, maxDefense, mana;

    public Combatant(String name, int mana, int defense, int healthPoint)
    {
        this.name = name;

        this.healthPoint = healthPoint;
        this.maxHealthPoint = healthPoint;

        this.defense = defense;
        this.maxDefense = defense;

        this.mana = mana;
    }

    public int getHP()
    {
        return this.healthPoint;
    }

    public int getDefense()
    {
        return this.defense;
    }

    public String getName()
    {
        return this.name;
    }

    public void Damage(int n)
    {
        this.defense -= n;
        if(this.defense <= 0)
        {
            int rest = 0 - this.defense;
            this.defense = 0;
            this.healthPoint -= rest;
            if(this.healthPoint <= 0)
            {
                this.healthPoint = 0;
                System.out.println("\u001B[31m" + this.name + " is dead" + "\u001B[0m");
            }
        }
    }

    public void Heal(int n)
    {
        this.healthPoint += n;
        if(this.healthPoint > this.maxHealthPoint)
        {
            int rest = this.healthPoint - this.maxHealthPoint;
            this.healthPoint = this.maxHealthPoint;
            this.defense += rest;
            if(this.defense > this.maxDefense)
            {
                this.defense = this.maxDefense;
                System.out.println(this.name + " is completly healed");
            }
        }
    }

    public void gainDefense(int n)
    {
        this.defense += n;
        if (this.defense > this.maxDefense)
        {
            this.defense = this.maxDefense;
        }
    }

    public void gainHP(int n, Item item)
    {
        this.healthPoint += n;
        if (this.healthPoint > this.maxHealthPoint && item instanceof Food)
        {
            // only the potion can add hp above the max hp limit
            System.out.println("Cannot add HP, max HP = " + this.maxHealthPoint);
            this.healthPoint = this.maxHealthPoint;    
        }
    }

    public void addMana(int n)
    {
        this.mana += n;
    }

    public int getMana()
    {
        return this.mana;
    }

    public abstract void fight(Combatant target);
}