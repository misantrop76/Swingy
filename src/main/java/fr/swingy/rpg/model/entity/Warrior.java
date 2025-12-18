package fr.swingy.rpg.model.entity;

public class Warrior extends Player
{
	public Warrior(String name)
	{
		super(name, 120, 15, 10);
	}

	@Override
	public String getClassName()
	{
		return ("Warrior");
	}
}
