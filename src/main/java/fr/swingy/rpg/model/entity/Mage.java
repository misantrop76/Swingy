package fr.swingy.rpg.model.entity;

public class Mage extends Player
{
	public Mage(String name)
	{
		super(name, 120, 15, 10, "🧍");
	}

	@Override
	public String getClassName()
	{
		return ("Mage");
	}
}
