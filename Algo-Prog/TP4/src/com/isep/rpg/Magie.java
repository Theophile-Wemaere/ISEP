package com.isep.rpg;

public class Magie
{
    private int damages;
    private int manaCost;
    private String name;

    public Magie(String name, int damages, int manaCost)
    {
        this.damages = damages;
        this.manaCost = manaCost;
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }

    public int getDamage()
    {
        return this.damages;
    }

    public int getManaCost()
    {
        return this.manaCost;
    }

    public void changeDamage(int n)
    {
        this.damages += n;
    }
}