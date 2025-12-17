package fr.swingy.rpg.model;

import fr.swingy.rpg.model.entity.Player;

public class GameState
{

	private boolean running = true;
	private Player	player = null;

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
}
