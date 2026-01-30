package fr.swingy.rpg.model;

import fr.swingy.rpg.model.entity.Player;
import fr.swingy.rpg.model.entity.Enemy;
import fr.swingy.rpg.model.artefacts.Artefact;
import fr.swingy.rpg.model.world.Map;
import fr.swingy.rpg.controller.GameController;

public class GameState
{
	private GameController.MenuLvl menu = null;
	private GameController.GameLvl gameLvl = null;
	private Enemy	currentEnnemy;
	private boolean running = true;
	private Player	player = null;
	private Map		map = null;

	public boolean isRunning()
	{
		return (running);
	}

	public void stop()
	{
		this.running = false;
	}

	public Player getPlayer()
	{
		return (this.player);
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

	public GameController.MenuLvl getMenuLvl()
	{
		return (this.menu);
	}

	public void setMenuLvl(GameController.MenuLvl menu)
	{
		this.menu = menu;
	}

	public GameController.GameLvl getGameLvl()
	{
		return (this.gameLvl);
	}

	public void setGameLvl(GameController.GameLvl gameLvl)
	{
		this.gameLvl = gameLvl;
	}

	public void setCurrentEnnemy(Enemy currentEnnemy)
	{
		this.currentEnnemy = currentEnnemy;
	}

	public Enemy getCurrentEnnemy()
	{
		return (this.currentEnnemy);
	}
}

