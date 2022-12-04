package com.isep.rpg.item;

public abstract class Consumable extends Item
{
    public Consumable(String name)
    {
        super(name);
    }

    public abstract int getHealingPoints();
}