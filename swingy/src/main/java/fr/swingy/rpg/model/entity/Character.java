package fr.swingy.rpg.model.entity;

public abstract class Character
{
	protected String name;
	protected int hp;
	protected int attack;
	protected int defence;

	public Character(String name, int hp, int attack, int defence)
	{
		this.name = name;
		this.hp = hp;
		this.attack = attack;
		this.defence = defence;
	}

	public boolean isAlive()
	{
		return hp > 0;
	}

	public void takeDamage(int damage)
	{
		hp -= damage;
	}

	public String getName()
	{
		return (this.name);
	}

	public void setName(String name)
	{
		this.name = name;
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
