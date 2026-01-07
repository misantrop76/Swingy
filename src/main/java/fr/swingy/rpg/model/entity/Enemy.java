package fr.swingy.rpg.model.entity;

import fr.swingy.rpg.model.entity.Character;

public abstract class Enemy extends Character
{
	protected	int lvl;

	public Enemy (String name, int hp, int attack, int defence, String icon, int lvl)
	{
		super(name, hp, attack, defence, icon);
		this.lvl = lvl;
	}

	public int getLvl()
	{
		return (this.lvl);
	}
}
