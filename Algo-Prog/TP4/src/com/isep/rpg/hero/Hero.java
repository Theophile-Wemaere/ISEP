package com.isep.rpg.hero;

import com.isep.rpg.item.*;
import com.isep.rpg.*;

public abstract class Hero extends Combatant
{ 
    public Hero(String name, int mana, int defense)
    {
        super(name,mana,defense,4); // heros have 4 HP
    }

    public void gainHP(int n, Item item)
    {
        super.gainHP(n,item);
    }

    public abstract void chooseItem(Consumable item);
    // public abstract void chooseWeapons(int n);
    public abstract void changeDamages(int c);
}