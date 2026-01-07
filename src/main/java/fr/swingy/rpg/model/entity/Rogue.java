package fr.swingy.rpg.model.entity;

public class Rogue extends Player
{
	public Rogue(String name)
	{
		super(name, 100, 16, 8, "🥷");
	}

	@Override
	public String getClassName()
	{
		return ("Rogue");
	}
}
