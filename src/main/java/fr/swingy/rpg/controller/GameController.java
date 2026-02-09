package fr.swingy.rpg.controller;

import fr.swingy.rpg.view.View;
import fr.swingy.rpg.model.GameState;
import fr.swingy.rpg.model.entity.Player;
import fr.swingy.rpg.model.entity.Enemy;
import fr.swingy.rpg.model.factory.PlayerFactory;
import fr.swingy.rpg.model.artefacts.Artefact;
import fr.swingy.rpg.model.world.Map;
import fr.swingy.rpg.model.world.Tile;
import fr.swingy.rpg.model.dto.GameViewData;
import fr.swingy.rpg.model.dto.PlayerViewData;
import fr.swingy.rpg.model.dto.FightUpdateView;
import fr.swingy.rpg.factory.ViewFactory;
import java.util.ArrayList;

public class GameController
{
	private View view;
	private GameState state;

	public enum MenuLvl
	{
		MAIN_MENU,
		CHARACTER_MENU,
		LOAD_MENU,
		NAME
	}

	public enum GameLvl
	{
		MAP,
		FIGHT,
		ARTEFACT,
		LOSE,
		WIN
	}

	private void refresh()
	{
		Enemy currentEnnemy = state.getCurrentEnnemy();
		if (state.getMenuLvl() != null)
		{
			switch (state.getMenuLvl())
			{
				case MAIN_MENU:
					view.showMainMenu();
					break;
				case CHARACTER_MENU:
					view.showNewCharacterMenu();
					break;
				case LOAD_MENU:
					view.showGameListMenu();
					break;
				case NAME:
					view.showNameInput();
					break;
				default:
					break;
			}
		}
		else if (state.getGameLvl() != null)
		{
			switch (state.getGameLvl())
			{
				case MAP:
					view.showGame(getGameViewData());
					break;
				case FIGHT:
					view.showFightChoice(getGameViewData());				
					break;
				case ARTEFACT:
					view.showArtefactChoice(getGameViewData());
					break;
				case LOSE:
					view.showLoseGame(getGameViewData());
				default:
					break;
			}
		}
		return;
	}

	public GameController()
	{
		this.state = new GameState();
		this.state.setMenuLvl(MenuLvl.MAIN_MENU);
		this.state.setGameLvl(null);
	}

	public void startGame(String mode)
	{
		this.view = ViewFactory.create(mode, this);
		this.view.start();
		refresh();
	}

	private void handleLoadCharacter(String input)
	{
		switch (input)
		{
			case "1":
			case "2":
			case "3":
				Player player = PlayerFactory.createPlayer("5", "default");
				this.state.setPlayer(player);
				this.state.setMap(new Map(state.getPlayer().getLvl(), player));
				this.state.setMenuLvl(null);
				this.state.setGameLvl(GameLvl.MAP);
				break;
			case "4":
				switchView();
				break;
			case "5":
				state.setMenuLvl(MenuLvl.MAIN_MENU);
		}
	}

	public	void handleInputPlayer(String input)
	{
		if (state.getMenuLvl() != null)
		{
			switch (state.getMenuLvl())
			{
				case MAIN_MENU:
					handleMainMenu(input);
					break;
				case CHARACTER_MENU:
					handleCharacter(input);
					break;
				case NAME:
					state.getPlayer().setName(input);
					state.setMenuLvl(null);
					state.setGameLvl(GameLvl.MAP);
					break;
				case LOAD_MENU:
					handleLoadCharacter(input);
			}
		}
		else if (state.getGameLvl() != null)
		{
			switch (state.getGameLvl())
			{
				case MAP:
					handleMove(input);
					break;
				case FIGHT:
					FightController.handleUserChoiceFight(this, state, input);
					break;
				case ARTEFACT:
					FightController.handleArtefactChoice(state, input);
					break;
			}
		}
		if (state.isRunning() == false)
		{
			view.close();
			return;
		}
		refresh();
	}

	private void handleMove(String input)
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
			case "5" :
				switchView();
				break;
			case "6":
				state.stop();
				break;
			default  :
				break;
		}
		if (x >= 0 && x < mapHeight && y >= 0 && y < mapHeight)
		{
			Enemy isEnemy = map.getEnemy((y * mapHeight) + x);
			if (isEnemy != null)
			{
				state.setCurrentEnnemy(isEnemy);
				state.setGameLvl(GameLvl.FIGHT);
			}
			else if (isEnemy == null)
				player.setPos((y * mapHeight) + x);
			map.addCharacter(player.getPrevPos(), null, null);
			map.addCharacter(player.getPos(), player, null);
		}
		else
		{
			state.stop();
			view.showWinGame(getGameViewData());
			view.close();
		}
	}

	public void handleMainMenu(String input)
	{
		if (input == null)
			return;
		switch (input)
		{
			case "1":
				this.state.setMenuLvl(MenuLvl.CHARACTER_MENU);
				break;
			case "2":
				this.state.setMenuLvl(MenuLvl.LOAD_MENU);
				break;
			case "3":
				switchView();
				break;
			case "4":
				this.state.stop();
				break;
			default:
				break;
		}
	}

	private void handleCharacter(String input)
	{
		switch (input)
		{
			case "1":
			case "2":
			case "3":
			case "4":
			case "5":
				Player player = PlayerFactory.createPlayer(input, "default");
				this.state.setPlayer(player);
				this.state.setMap(new Map(state.getPlayer().getLvl(), player));
				this.state.setMenuLvl(MenuLvl.NAME);
				break;
			case "6":
				switchView();
				break;
			case "7":
				this.state.setMenuLvl(MenuLvl.MAIN_MENU);
				break;
			default:
				break;
		}
	}

	private void switchView()
	{
		View newView = null;
		String viewName = view.getViewName();
		this.view.close();
		if (viewName.equals("CONSOLE"))
			newView = ViewFactory.create("GUI", this);
		else
			newView = ViewFactory.create("CONSOLE", this);
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
				mapData[a] += "O";
			else if (x == player.getPos())
				mapData[a] += "P";
			else
				mapData[a] += "?";
			x++;
			if (x % height == 0)
				a++;
		}
		PlayerViewData pvd = new PlayerViewData(player.getName(), player.getLvl(), player.getHpMax(),
		player.getHp(), player.getXpMax(), player.getXp(), player.getDefence(), player.getAttack(),
		player.getClassName(), player.getArtefact() != null ? player.getArtefact().getBonus(): null);

		return (new GameViewData(
			pvd,
			mapData,
			state.fightUpdate,
			state.getCurrentEnnemy().getName(),
			state.getCurrentEnnemy() != null ? state.getCurrentEnnemy().getArtefact().getBonus(): null
		));
	}
}