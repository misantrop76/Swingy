package fr.swingy.rpg.model.entity;

import fr.swingy.rpg.model.artefacts.Artefact;
import java.util.Random;

public abstract class Character
{
	protected String name;
	protected int hp;
	protected int attack;
	protected int defence;
	protected int pos;
	protected String icon;
	protected Artefact artefact;

	public Character(String name, int hp, int attack, int defence, String icon)
	{
		this.name = name;
		this.hp = hp;
		this.attack = attack;
		this.defence = defence;
		this.icon = icon;
		this.artefact = null;
	}

	public boolean isAlive()
	{
		return hp > 0;
	}

	public void setArtefact(Artefact artefact)
	{
		this.artefact = artefact;
	}

	public Artefact getArtefact()
	{
		return (this.artefact);
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

	public void setHp(int hp)
	{
		this.hp = hp;
		if (hp < 0)
			this.hp = 0;
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
