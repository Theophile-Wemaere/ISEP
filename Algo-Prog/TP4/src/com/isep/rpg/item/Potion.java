package com.isep.rpg.item;

public class Potion extends Consumable
{
    private int power; // the healing power

    public Potion(String name)
    {
        super(name);

        switch(name)
        {
            case "simple healing potion":
                this.power = 2;
                break;
            
            case "medium healing potion":
                this.power = 3;
                break;

            case "high healing potion":
                this.power = 4;
                break;
        }
    }

    @Override
    public int getHealingPoints()
    {
        return this.power;
    }
}
