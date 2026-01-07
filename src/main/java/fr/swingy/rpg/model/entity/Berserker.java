package fr.swingy.rpg.model.entity;

public class Berserker extends Player
{
	public Berserker(String name)
	{
		super(name, 150, 20, 7, "🪓");
	}

	@Override
	public String getClassName()
	{
		return ("Berserker");
	}
}
