package fr.swingy.rpg.model.entity;

import fr.swingy.rpg.view.ConsoleView;
import java.util.Random;

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
		Random random = new Random();

		double variance = 0.85 + (random.nextDouble() * 0.30);
		double reduction = this.getDefence() / (this.getDefence() + 100.0);
		double degats = (double)character.getAttack() * (1 - reduction);
		Boolean isCritical = random.nextInt(100) < 10;

		if (isCritical)
			degats *= 1.5;
		view.showFightUpdate(character, this, (int)degats, isCritical);
		hp -= (int)degats;
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
