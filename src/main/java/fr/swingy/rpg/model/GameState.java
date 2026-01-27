package fr.swingy.rpg.model;

import fr.swingy.rpg.model.entity.Player;
import fr.swingy.rpg.model.world.Map;

public class GameState
{
	private String	menuLvl = null;
	private boolean running = true;
	private Player	player = null;
	private Map		map = null;

	public boolean isRunning()
	{
		return running;
	}

	public void stop()
	{
		this.running = false;
	}

	public Player getPlayer()
	{
		return this.player;
	}

	public void setPlayer(Player player)
	{
		this.player = player;
	}

	public Map getMap()
	{
		return (this.map);
	}

	public void setMap(Map map)
	{
		this.map = map;
	}

	public String getMenuLvl()
	{
		return (this.menuLvl);
	}

	public void setMenuLvl(int menuLvl)
	{
		this.menuLvl = menuLvl;
	}
}
