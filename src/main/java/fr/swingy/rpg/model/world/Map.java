package fr.swingy.rpg.model.world;

import java.util.ArrayList;
import fr.swingy.rpg.model.entity.Character;

public class Map 
{
	private ArrayList <Tile>	map = new ArrayList<Tile>();
	private int					height;

	public Map(int lvl)
	{
		this.height = (lvl - 1) * 5 + 10 - (lvl % 2);
		for (int i = 0; i < this.height * this.height; i++)
			this.map.add(new Tile());
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
