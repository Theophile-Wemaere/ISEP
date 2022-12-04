package com.isep.utils;

import java.util.ArrayList;

import com.isep.rpg.hero.*;
import com.isep.rpg.*;

public interface InputParser
{
    public int getHeroNum();
    public Hero getHeroClasse();
    public int getAction(Hero hero);
    public void chooseSpell(SpellCaster hero);
    public int getTarget(ArrayList<Enemy> enemies);
    public void waitKey();
}