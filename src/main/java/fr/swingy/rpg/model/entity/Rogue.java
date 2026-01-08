package fr.swingy.rpg.model.entity;

public class Rogue extends Player
{
	public Rogue(String name)
	{
		super(name, 120, 18, 10, "🥷");
	}

	@Override
	public String getClassName()
	{
		return ("Rogue");
	}
}
