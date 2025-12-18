package fr.swingy.rpg.model.weapon;

public abstract class Weapon 
{
	protected int damage;
	protected String name;

	public Weapon(int damage, String name)
	{
		this.damage = damage;
		this.name = name;
	}
}

