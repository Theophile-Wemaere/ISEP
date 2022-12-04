package com.isep.rpg;

import java.util.Random;

public class Enemy extends Combatant
{
    private int damage, defense, healthPoint;
    private String name;

    public Enemy(String name, int defense)
    {
        super(name,1000, defense, 5); // infinite mana in case I want to implement special thing
    }

    @Override
    public void fight(Combatant target)
    {
        target.Damage(this.damage);
    }

    public int getDamage()
    {
        Random random = new Random();
        this.damage = random.nextInt(4 - 2 + 1) + 2; // generate number in [2;4], random attack value
        return this.damage;
    }
}