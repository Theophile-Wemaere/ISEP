package com.isep.rpg;

public class Enemy extends Combatant
{
    private int damage, defense, healthPoint;
    private String name;

    public Enemy(String name, int defense, int damage)
    {
        super(name,1000, defense, 5); // infinte mana in case I want to implement special thing
        this.damage = damage;
    }

    @Override
    public void fight(Combatant target)
    {
        target.Damage(this.damage);
    }
}