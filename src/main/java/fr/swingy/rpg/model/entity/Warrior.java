package fr.swingy.rpg.model.entity;

public class Warrior extends Player
{
	public Warrior(String name)
	{
		super(name, 130, 15, 12, "🤺");
	}

	@Override
	public String getClassName()
	{
		return ("Warrior");
	}
}
