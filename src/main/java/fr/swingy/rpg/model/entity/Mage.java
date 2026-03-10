package fr.swingy.rpg.model.entity;

public class Mage extends Player
{
	public Mage(String name)
	{
		super(name, 80, 25, 5, "🧙");
	}

	@Override
	public String getClassName()
	{
		return ("Mage");
	}
}
