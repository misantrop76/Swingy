package fr.swingy.rpg.model.entity;

import fr.swingy.rpg.view.ConsoleView;

public abstract class Character
{
	protected String name;
	protected int hp;
	protected int attack;
	protected int defence;
	protected int pos;
	protected String icon;

	public Character(String name, int hp, int attack, int defence, String icon)
	{
		this.name = name;
		this.hp = hp;
		this.attack = attack;
		this.defence = defence;
		this.icon = icon;
	}

	public boolean isAlive()
	{
		return hp > 0;
	}

	public void takeDamage(Character character)
	{
		ConsoleView view = new ConsoleView();
		double reduction = this.getDefence() / (this.getDefence() + 100.0);
		int degats = (int)(character.getAttack() * (1 - reduction));
		degats = Math.max(degats, 1);
		view.showFightUpdate(character, this, character.getAttack());
		hp -= degats;
		if (hp < 0)
			hp = 0;
	}

	public String getName()
	{
		return (this.name);
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setPos(int pos)
	{
		this.pos = pos;
	}

	public int getPos()
	{
		return (this.pos);
	}

	public void setIcon(String icon)
	{
		this.icon = icon;
	}

	public String getIcon()
	{
		return (this.icon);
	}

	public int getHp()
	{
		return (this.hp);
	}

	public int getAttack()
	{
		return (this.attack);
	}

	public int getDefence()
	{
		return (this.defence);
	}
}
