package com.isep.rpg.hero;

import com.isep.rpg.Magie;
import com.isep.rpg.item.*;
import com.isep.rpg.*;

public class Healer extends SpellCaster
{
    private String name;
    private int mana;
    private Magie spell;

    public Healer(String name)
    {
        super(name,8);
        this.name = name;
        this.mana = 8;      
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
        // complicated structure in case I have the time to add special effects stuff like zone effect or secondary effect
        System.out.println("\n" + this.name + " inflicts " + Integer.toString(this.spell.getDamage()) + " damages to " + target.getName());
        switch(this.spell.getName())
        {
            case "holy ray":
                target.Damage(this.spell.getDamage());
                this.mana -= this.spell.getManaCost();
                break;

            case "healing touch":
                target.Heal(this.spell.getDamage());
                if (target instanceof SpellCaster) // also add mana for now
                {
                    target.addMana(this.spell.getDamage()*2);
                }
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
            case 1:
                this.spell = new Magie("holy ray",2,2);
                break;

            case 2:
                this.spell = new Magie("healing touch",2,3);
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