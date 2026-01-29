package fr.swingy.rpg.controller;

import fr.swingy.rpg.view.View;
import fr.swingy.rpg.model.GameState;
import fr.swingy.rpg.model.entity.Player;
import fr.swingy.rpg.model.entity.Enemy;
import fr.swingy.rpg.model.factory.PlayerFactory;
import fr.swingy.rpg.model.factory.PlayerFactory;
import fr.swingy.rpg.model.artefacts.Artefact;
import fr.swingy.rpg.model.world.Map;
import fr.swingy.rpg.model.world.Tile;
import fr.swingy.rpg.model.GameViewData;
import fr.swingy.rpg.factory.ViewFactory;
import java.util.ArrayList;

public class GameController
{
	private View view;
	private GameState state;

	public enum menuLvl
	{
		MAIN_MENU,
		CHARACTER_MENU,
		LOAD_MENU
	}
	public GameController()
	{
		this.state = new GameState();
		this.state.setMenuLvl(menuLvl.MAIN_MENU);
	}

	public void move(String input)
	{
		Player player = state.getPlayer();
		Map map = state.getMap();
		int mapHeight = map.getHeight();
		int x = player.getPos() % mapHeight;
		int y = player.getPos() / mapHeight;
		player.setPrevPos(player.getPos());
		switch (input)
		{
			case "1" :
				y--;
				break;
			case "2" :
				y++;
				break;
			case "3" :
				x++;
				break;
			case "4" :
				x--;
				break;
			default  :
				return;
		}
		if (x >= 0 && x < mapHeight && y >= 0 && y < mapHeight)
		{
			Enemy isEnemy = map.getEnemy((y * mapHeight) + x);
			if (isEnemy != null && FightController.handleUserChoiceFight(isEnemy, view))
				FightController.startFight(this, state, isEnemy, view);
			else if (isEnemy == null)
				player.setPos((y * mapHeight) + x);
			map.addCharacter(player.getPrevPos(), null, null);
			map.addCharacter(player.getPos(), player, null);
		}
		else
		{
			state.stop();
			view.showWinGame(getGameViewData());
		}
	}

	public void startGame(String mode)
	{
		this.view = ViewFactory.create(mode);
		this.view.start();
		String input = null;
		gameMenu();
		while (this.state.isRunning())
		{
			this.view.showGame(getGameViewData());
			input = this.view.askInput("Enter your choice");
			if (input.length() == 1 && input.charAt(0) >= '1' && input.charAt(0) <= '4')
				move(input);
			else if (input.equals("5"))
				switchView();
			else if (input.equals("6"))
				this.state.stop();
		}
		view.close();
	}

	public void handleMainMenu()
	{
		this.view.showMainMenu();
		String input = this.view.askInput("Enter your choice");
		if (input == null)
			return;
		switch (input)
		{
			case "1":
				this.state.setMenuLvl(menuLvl.CHARACTER_MENU);
				break;
			case "2":
				this.state.setMenuLvl(menuLvl.LOAD_MENU);
				break;
			case "3":
				switchView();
				return;
			case "4":
				this.state.stop();
			default:
				return;
		}
	}

	public void handleCharacter()
	{
		this.view.showNewCharacterMenu();
		String input = this.view.askInput("Enter your choice");
		if (input.length() != 1 || input.charAt(0) < '1' || input.charAt(0) > '7')
			return;
		if (input.charAt(0) == '6')
		{
			switchView();
			return;
		}
		else if (input.charAt(0) == '7')
		{
			this.state.setMenuLvl(menuLvl.MAIN_MENU);
			return;
		}
		String name = this.view.askInput("Enter your name");

		while (name == null || name.length() == 0)
			name = this.view.askInput("Enter your name");
		Player player = PlayerFactory.createPlayer(input, name);
		this.state.setPlayer(player);
		this.state.setMap(new Map(state.getPlayer().getLvl(), player));
		this.state.setMenuLvl(null);
	}

	public void gameMenu()
	{
		while (this.state.isRunning() && this.state.getMenuLvl() != null)
		{
			menuLvl menu = this.state.getMenuLvl();
			switch (menu)
			{
				case MAIN_MENU:
					handleMainMenu();
					break;
				case LOAD_MENU:
					System.out.println("loadmenu");		/// TO DO
					this.state.setMenuLvl(null);
					break;
				case CHARACTER_MENU:
					handleCharacter();
					break;
			}
		}

	}

	public void switchView()
	{
		View newView = null;
		String viewName = view.getViewName();
		if (viewName.equals("CONSOLE"))
			newView = ViewFactory.create("GUI");
		else
			newView = ViewFactory.create("CONSOLE");
		this.view.close();
		this.view = newView;
		view.start();
	}

	public GameViewData getGameViewData()
	{
		Player player = state.getPlayer();
		String artefact = null;
		ArrayList<Tile> map = state.getMap().getMap();
		int height = state.getMap().getHeight();
		String[] mapData = new String[height + 1];

		if (player != null && player.getArtefact() != null)
			artefact = player.getArtefact().getBonus();
		int a = 0;
		int x = 0;
		mapData[height] = null;
		for (int i = 0; i < height; i++)
			mapData[i] = "";
		for (Tile tile : map)
		{
			if (tile.getCharacter() == null)
				mapData[a] += "⬛";
			else if (x == player.getPos())
				mapData[a] += tile.getCharacter().getIcon();
			else
				mapData[a] += "❓";
			x++;
			if (x % height == 0)
				a++;
		}

		return (new GameViewData(
			player.getName(),
			player.getLvl(),
			player.getXp(),
			player.getXpMax(),
			player.getHp(),
			player.getHpMax(),
			player.getDefence(),
			player.getAttack(),
			artefact,
			mapData,
			player.getClassName()
		));
	}
}