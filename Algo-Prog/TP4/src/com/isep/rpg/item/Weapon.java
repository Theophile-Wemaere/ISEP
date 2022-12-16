package com.isep.rpg.item;

public class Weapon extends Item
{
    private int damages;
    private String name;
    private boolean malus; // weapons with more damages have a chance to fail their attack (20% chance) 

    public Weapon(String name)
    {
        super(name);
        this.name = name;

        switch(name)
        {
            case "giant sword":
                this.damages = 3;
                this.malus = true;
                break;

            case "saber":
                this.damages = 20;
                this.malus = false;
                break;

            case "bow":
                this.damages = 20;
                this.malus = false;
                break;

            case "crossbow":
                this.damages = 3;
                this.malus = true;
                break;
        }
    }

    public int getDamage()
    {
        return this.damages;
    }

    public boolean doFight()
    {
        if(this.malus)
        {
            if (Math.random() < 0.2) // Math.random() return a random double : 0 <= Math.random() < 1
            {
                return false;
            } 
            else 
            {
                return true;
            }
        }
        else
        {
            return true;
        }
    }
}
