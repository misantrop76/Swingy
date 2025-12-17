package fr.swingy.rpg.model.entity;

public abstract class Player extends Character
{
	protected int lvl;
	protected int xp;

	public Player (String name, int hp, int attack, int defence)
	{
		super(name, hp, attack, defence);
		this.lvl = 1;
		this.xp = 0;
	}

	public int getXp()
	{
		return (this.xp);
	}

	public int getLvl()
	{
		return (this.lvl);
	}

	public abstract String getClassName();
}
