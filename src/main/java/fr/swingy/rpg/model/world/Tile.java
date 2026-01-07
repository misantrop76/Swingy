package fr.swingy.rpg.model.world;

import fr.swingy.rpg.model.entity.Character;
import fr.swingy.rpg.model.entity.Enemy;

public class Tile 
{
	Character	character = null;
	Enemy		enemy = null;
	

	public void setCharacter(Character character, Enemy enemy)
	{
		this.character = character;
		this.enemy = enemy;
	}

	public Character getCharacter()
	{
		return (this.character);
	}

	public Enemy getEnemy()
	{
		return (this.enemy);
	}
}
