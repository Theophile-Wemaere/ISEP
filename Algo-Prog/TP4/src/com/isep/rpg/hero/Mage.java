package com.isep.rpg.hero;

import com.isep.rpg.Magie;
import com.isep.rpg.item.*;
import com.isep.rpg.*;

public class Mage extends SpellCaster
{
    private String name;
    private int mana;
    private Magie spell;

    public Mage(String name)
    {
        super(name,7);
        this.name = name;
        this.mana = 7;
    }

    @Override
    public void addMana(int n)
    {
        this.mana += n;
    }

    @Override
    public int getMana()
    {
        return this.mana;
    }


    @Override
    public void fight(Combatant target)
    {
        // complicated structure in case I have the time to add special effects stuff like zone effect or secodary effect
        System.out.println("\n" + this.name + " inflicts " + Integer.toString(this.spell.getDamage()) + " damages to " + target.getName());
        switch(this.spell.getName())
        {
            case "ice pick":
                target.Damage(this.spell.getDamage());
                this.mana -= this.spell.getManaCost();
                break;

            case "lightning":
                target.Damage(this.spell.getDamage());
                this.mana -= this.spell.getManaCost();
                break;

            case "fire storm":
                target.Damage(this.spell.getDamage());
                this.mana -= this.spell.getManaCost();
                break;
        }

        if (this.mana <= 0)
        {
            System.out.println(this.name + " is out of mana");
            this.mana = 0;
        }
    }
    
    @Override
    public void chooseSpell(int n)
    {
        switch(n)
        {
            case 1: // todo : add frost effect (prevent the enemy to attack for one round)
                this.spell = new Magie("ice pick",2,1);
                break;

            case 2:
                this.spell = new Magie("lightning",3,3);
                break;

            case 3: // todo : add zone effect 
                this.spell = new Magie("fire storm",5,5);
                break;
        }
    }

    @Override
    public void changeDamages(int n)
    {
        this.spell.changeDamage(n);
    }

    @Override
    public void chooseItem(Consumable item)
    {
        int hp = item.getHealingPoints();
        super.gainHP(hp,item);
        addMana(hp*2); // for now healing potion add mana -> todo : add mana potion
    }
}
