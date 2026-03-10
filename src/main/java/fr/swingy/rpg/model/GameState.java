package fr.swingy.rpg.model;

import java.util.ArrayList;

import javax.validation.constraints.Positive;

import fr.swingy.rpg.controller.GameController;
import fr.swingy.rpg.model.dto.FightUpdateView;
import fr.swingy.rpg.model.entity.Enemy;
import fr.swingy.rpg.model.entity.Player;
import fr.swingy.rpg.model.world.Map;

public class GameState
{
	private GameController.MenuLvl menu = null;
	private GameController.GameLvl gameLvl = null;
	public  ArrayList<FightUpdateView> fightUpdate = null;
	@Positive(message = "xpWin cannot be negatif")
	public int xpWin = 0;
	public Boolean isLvlUp = false;
	private Enemy	currentEnnemy = null;
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

