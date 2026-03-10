package fr.swingy.rpg.model.entity;

import javax.validation.constraints.Positive;

public abstract class Enemy extends Character
{
	@Positive(message = "lvl cannot be negatif")
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
