package com.isep.utils;

import java.util.ArrayList;

import com.isep.rpg.item.*;
import com.isep.rpg.hero.*;
import com.isep.rpg.*;

public interface InputParser
{
    public int getHeroNum();
    public Hero getHeroClasse();
    public int getAction(Hero hero, int size);
    public void chooseSpell(SpellCaster hero);
    public void chooseWeapon(Hero hero);
    public int getTarget(ArrayList<Combatant> enemies);
    public int chooseItem(ArrayList<Consumable> consumables);
    public void waitKey();
}