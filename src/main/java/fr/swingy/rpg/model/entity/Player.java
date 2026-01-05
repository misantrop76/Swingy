package fr.swingy.rpg.model.entity;

import fr.swingy.rpg.model.entity.Character;

public abstract class Player extends Character
{
	protected int lvl;
	protected int xp;
	protected int prevPos;

	public Player (String name, int hp, int attack, int defence, String icon)
	{
		super(name, hp, attack, defence, icon);
		this.lvl = 1;
		this.xp = 0;
	}

	public int getXpMax()
	{
		int lvl = this.lvl;
		return (lvl * 1000 + ((lvl - 1) * (lvl - 1)) * 450);
	}

	public int getXp()
	{
		return (this.xp);
	}

	public void setXp(int xp)
	{
		this.xp = xp;
	}

	public int getLvl()
	{
		return (this.lvl);
	}

	public void setLvl(int lvl)
	{
		this.lvl = lvl;
	}

	public void setPrevPos(int prevPos)
	{
		this.prevPos = prevPos;
	}

	public int getPrevPos()
	{
		return (this.prevPos);
	}

	public abstract String getClassName();
}
