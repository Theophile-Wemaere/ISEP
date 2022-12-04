package com.isep.rpg.item;

public class Food extends Consumable
{
    private int power; // the healing power

    public Food(String name)
    {
        super(name);

        switch(name)
        {
            case "onigiri":
                this.power = 2;
                break;

            case "sushis":
                this.power = 3;
                break;

            case "ramen":
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