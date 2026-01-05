package fr.swingy.rpg.model.world;

import java.util.ArrayList;
import fr.swingy.rpg.model.entity.Character;
import fr.swingy.rpg.model.entity.Player;
import fr.swingy.rpg.model.entity.Monster;
import java.util.Random;

public class Map 
{
	private ArrayList <Tile>	map = new ArrayList<Tile>();
	private int					height;

	public Map(int lvl, Player player)
	{
		updateMap(lvl, player);
	}

	public int		getHeight()
	{
		return (this.height);
	}


	public void removeCharacter(int pos)
	{
		int i = 0;
		for (Tile tile : this.map)
		{	
			if (pos == i)
				tile.setCharacter(null);
			i++;
		}
	}

	public void updateMap(int playerLvl, Player player)
	{
		this.height = (playerLvl - 1) * 5 + 10 - (playerLvl % 2);
		map.clear();
		for (int i = 0; i < this.height * this.height; i++)
			this.map.add(new Tile());
		addCharacter(this.map.size() / 2, player);
		Random random = new Random();
		for (int i = 0; i < this.height * 2; i++)
		{
			Monster monster = new Monster("Igor");
			int pos = random.nextInt(this.map.size());
			while (map.get(pos).getCharacter() != null)
				pos = random.nextInt(this.map.size());
			addCharacter(pos, monster);
		}
	}

	public void	addCharacter(int pos, Character character)
	{
		int i = 0;
		if (character == null)
			return;
		for (Tile tile : this.map)
		{
			if (pos == i)
				tile.setCharacter(character);
			i++;
		}
	}

	public ArrayList <Tile> getMap()
	{
		return this.map;
	}
}
