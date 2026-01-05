package fr.swingy.rpg.model.world;

import fr.swingy.rpg.model.entity.Character;

public class Tile 
{
	Character character = null;
	

	public void setCharacter(Character character)
	{
		this.character = character;
	}

	public Character getCharacter()
	{
		return (this.character);
	}
}
