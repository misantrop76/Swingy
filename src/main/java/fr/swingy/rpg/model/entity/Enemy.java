package fr.swingy.rpg.model.entity;

import fr.swingy.rpg.model.entity.Character;

public abstract class Enemy extends Character
{
	public Enemy (String name, int hp, int attack, int defence, String icon)
	{
		super(name, hp, attack, defence, icon);
	}
}
