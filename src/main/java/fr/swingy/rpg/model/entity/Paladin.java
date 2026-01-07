package fr.swingy.rpg.model.entity;

public class Paladin extends Player
{
	public Paladin(String name)
	{
		super(name, 120, 14, 15, "🌟");
	}

	@Override
	public String getClassName()
	{
		return ("Paladin");
	}
}
