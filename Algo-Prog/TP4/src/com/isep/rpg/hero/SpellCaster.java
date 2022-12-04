package com.isep.rpg.hero;

public abstract class SpellCaster extends Hero
{
    public SpellCaster(String name, int mana)
    {
        super(name,mana, 3); // spellcaster have 3 defense
    }

    public abstract void chooseSpell(int n);
    public abstract int getSpellCost();
    public abstract String getSpellName();
}