package fr.swingy.rpg.model.world;

import java.util.ArrayList;
import java.util.Random;

import javax.validation.constraints.Positive;

import fr.swingy.rpg.factory.ArtefactFactory;
import fr.swingy.rpg.factory.EnemyFactory;
import fr.swingy.rpg.model.entity.Character;
import fr.swingy.rpg.model.entity.Enemy;
import fr.swingy.rpg.model.entity.Player;

public class Map
{
	private final ArrayList <Tile>	map = new ArrayList<>();
	@Positive(message = "height cannot be negatif")
	private int					height;

	public int		getHeight()
	{
		return (this.height);
	}

	public void updateMap(Player player)
	{
		int playerLvl = player.getLvl();
	
		this.height = (playerLvl - 1) * 5 + 10 - (playerLvl % 2);
		map.clear();
		for (int i = 0; i < this.height * this.height; i++)
			this.map.add(new Tile());
		addCharacter(this.map.size() / 2, player, null);
		player.setPos(this.map.size() / 2);
		player.setPrevPos(this.map.size() / 2);
		Random random = new Random();
		for (int i = 0; i < this.height * 4; i++)
		{
			Enemy enemy = EnemyFactory.createEnemy(playerLvl);
			enemy.setArtefact(ArtefactFactory.createArtefact(enemy.getLvl()));
			int pos = random.nextInt(this.map.size());
			while (map.get(pos).getCharacter() != null)
				pos = random.nextInt(this.map.size());
			enemy.setPos(pos);
			addCharacter(pos, enemy, enemy);
		}
	}

	public Character getCharacter(int pos)
	{
		int i = 0;
		for (Tile tile : this.map)
		{
			if (pos == i)
				return (tile.getCharacter());
			i++;
		}
		return (null);
	}

	public Enemy getEnemy(int pos)
	{
		int i = 0;
		for (Tile tile : this.map)
		{
			if (pos == i)
				return (tile.getEnemy());
			i++;
		}
		return (null);
	}

	public void	addCharacter(int pos, Character character, Enemy enemy)
	{
		int i = 0;
		for (Tile tile : this.map)
		{
			if (pos == i)
				tile.setCharacter(character, enemy);
			i++;
		}
	}

	public ArrayList <Tile> getMap()
	{
		return this.map;
	}
}
