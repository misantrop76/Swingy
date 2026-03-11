package fr.swingy.rpg.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.swingy.rpg.database.DataBaseManager;
import fr.swingy.rpg.database.HeroDAO;
import fr.swingy.rpg.factory.PlayerFactory;
import fr.swingy.rpg.factory.ViewFactory;
import fr.swingy.rpg.model.GameState;
import fr.swingy.rpg.model.dto.GameViewData;
import fr.swingy.rpg.model.dto.PlayerViewData;
import fr.swingy.rpg.model.entity.Enemy;
import fr.swingy.rpg.model.entity.Player;
import fr.swingy.rpg.model.world.Map;
import fr.swingy.rpg.model.world.Tile;
import fr.swingy.rpg.validator.ValidatorUtil;
import fr.swingy.rpg.view.View;

public class GameController
{
	private View view;
	private final GameState state;
	private final HeroDAO heroDAO;
	private List<PlayerViewData> savedPlayer;

	public GameController()
	{
		this.state = new GameState();
		this.state.setMenuLvl(MenuLvl.MAIN_MENU);
		this.state.setGameLvl(null);
		Connection conn = DataBaseManager.createDataBase();
		this.heroDAO = new HeroDAO(conn);
		try
		{
			savedPlayer = heroDAO.loadHeroes();
		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage());
			state.stop();
		}
	}

	public enum MenuLvl
	{
		MAIN_MENU,
		CHARACTER_MENU,
		LOAD_MENU,
		RM_SAVE,
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
		if (state.getMenuLvl() != null)
		{
			switch (state.getMenuLvl())
			{
				case MAIN_MENU -> view.showMainMenu();
				case CHARACTER_MENU -> view.showNewCharacterMenu();
				case LOAD_MENU ->
				{
					try
					{
						savedPlayer = heroDAO.loadHeroes();
						view.showGameListMenu(savedPlayer);
					}
					catch (SQLException e)
					{
						System.err.println(e.getMessage());
						state.stop();
					}
				}
				case NAME -> view.showNameInput();
				case RM_SAVE -> view.showRmMenu(savedPlayer);
				default -> {}
			}
		}
		else if (state.getGameLvl() != null)
		{
			switch (state.getGameLvl())
			{
				case MAP -> view.showGame(getGameViewData());
				case FIGHT -> view.showFightChoice(getGameViewData());
				case ARTEFACT -> view.showFight(getGameViewData());
				case LOSE -> view.showLoseGame(getGameViewData());
				case WIN -> view.showWinGame(getGameViewData());
				default -> {}
			}
		}
	}

	public void resetGame(String mode)
	{
		this.view = ViewFactory.create(mode, this);
		this.view.start();
		refresh();
	}

	private void handleLoadCharacter(String input)
	{
		switch (input)
		{
			case "1", "2", "3", "4", "5" ->
			{
				if (Integer.parseInt(input) > savedPlayer.size())
					return;
				try
				{
					Player player = heroDAO.getPlayer(savedPlayer.get(Integer.parseInt(input) - 1).id);
					heroDAO.deleteHero(savedPlayer.get(Integer.parseInt(input) - 1).id);
					this.state.setPlayer(player);
					startGame();
				}
				catch (SQLException e)
				{
					System.err.println(e.getMessage());
					state.stop();
				}
			}
			case "6" -> switchView();
			case "7" -> state.setMenuLvl(MenuLvl.MAIN_MENU);
			default -> {}
		}
	}

	private void handleRmCharacter(String input)
	{
		switch (input)
		{
			case "1", "2", "3", "4", "5" ->
			{
				try
				{
					heroDAO.deleteHero(savedPlayer.get(Integer.parseInt(input) - 1).id);
					state.setMenuLvl(MenuLvl.CHARACTER_MENU);
				}
				catch (SQLException e)
				{
					System.err.println(e.getMessage());
					state.stop();
				}
			}
			case "6" -> switchView();
			case "7" -> state.setMenuLvl(MenuLvl.MAIN_MENU);
			default -> {}
		}
	}

	private void handleLoseMenu(String input)
	{
		switch (input)
		{
			case "1" -> 
			{
				state.setGameLvl(null);
				state.setMenuLvl(MenuLvl.MAIN_MENU);
				view.showMainMenu();
            }
			case "2" -> switchView();
			case "3" -> state.stop();
			default -> {}
		}
	}

	private void startGame()
	{
		state.setMap(new Map());
		state.getMap().updateMap(state.getPlayer());
		state.setGameLvl(GameLvl.MAP);
		state.setMenuLvl(null);
	}

	public	void handleInputPlayer(String input)
	{
		if (state.getMenuLvl() != null)
		{
			switch (state.getMenuLvl())
			{
				case MAIN_MENU -> handleMainMenu(input);
				case CHARACTER_MENU -> handleCharacter(input);
				case NAME ->
				{
					state.getPlayer().setName(input);
					if (!ValidatorUtil.validate(state.getPlayer()))
					{
					    System.out.println("Invalid hero data. Please try again.");
					    return;
					}
					startGame();
                }
				case LOAD_MENU -> handleLoadCharacter(input);
				case RM_SAVE -> handleRmCharacter(input);
				default -> {}
			}
		}
		else if (state.getGameLvl() != null)
		{
			switch (state.getGameLvl())
			{
				case MAP -> handleMove(input);
				case FIGHT -> FightController.handleUserChoiceFight(this, state, input);
				case ARTEFACT ->
				{
					if (state.getCurrentEnnemy() != null && state.getCurrentEnnemy().getArtefact() != null)
						FightController.handleArtefactChoice(state, input);
					else
						state.setGameLvl(GameLvl.MAP);
                }
				case LOSE, WIN -> handleLoseMenu(input);
				default -> {}
			}
		}
		if (state.isRunning() == false)
		{
			view.close();
			return;
		}
		refresh();
		if (state.getPlayer() != null && state.getPlayer().getHp() == 0)
			state.setGameLvl(GameLvl.LOSE);
		state.isLvlUp = false;
		state.xpWin = 0;
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
			case "1" -> y--;
			case "2" -> y++;
			case "3" -> x++;
			case "4" -> x--;
			case "5" -> switchView();
			case "6" ->
			{
				try
				{
					heroDAO.saveHero(player);
					this.state.setMenuLvl(MenuLvl.MAIN_MENU);
				}
				catch (SQLException e)
				{
					System.err.println(e.getMessage());
					state.stop();
				}
			}
			default -> {}
		}
		if (x >= 0 && x < mapHeight && y >= 0 && y < mapHeight)
		{
			Enemy isEnemy = map.getEnemy((y * mapHeight) + x);
			if (isEnemy != null)
			{
				if (player.getArtefact() != null && isEnemy.getArtefact() != null && player.getArtefact().getBonus().equals(isEnemy.getArtefact().getBonus()))
					isEnemy.setArtefact(null);
				state.setCurrentEnnemy(isEnemy);
				state.setGameLvl(GameLvl.FIGHT);
			}
			else if (isEnemy == null)
				player.setPos((y * mapHeight) + x);
			map.addCharacter(player.getPrevPos(), null, null);
			map.addCharacter(player.getPos(), player, null);
		}
		else
			state.setGameLvl(GameLvl.WIN);
	}

	public void handleMainMenu(String input)
	{
		if (input == null)
			return;
		switch (input)
		{
			case "1" ->
			{
				try
				{
					if (heroDAO.countHeroes() < 5)
						this.state.setMenuLvl(MenuLvl.CHARACTER_MENU);
					else
						this.state.setMenuLvl(MenuLvl.RM_SAVE);
				}
				catch (SQLException e)
				{
					System.err.println(e.getMessage());
					state.stop();
				}
			}
			case "2" -> this.state.setMenuLvl(MenuLvl.LOAD_MENU);
			case "3" -> switchView();
			case "4" -> this.state.stop();
			default -> {}
		}
	}

	private void handleCharacter(String input)
	{
		switch (input)
		{
			case "1", "2", "3", "4", "5" ->
			{
				Player player = PlayerFactory.createPlayer(input, "default");
				this.state.setPlayer(player);
				this.state.setMenuLvl(MenuLvl.NAME);
			}
			case "6" -> switchView();
			case "7" -> this.state.setMenuLvl(MenuLvl.MAIN_MENU);
			default -> {}
		}
	}

	private void switchView()
	{
		View newView;
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
		String artefactPlayer = null;
		String artefactEnemy = null;
		String enemyName = null;
		String name = player.getName();
		ArrayList<Tile> map = state.getMap().getMap();
		int height = state.getMap().getHeight();
		String[] mapData = new String[height + 1];
		int previousHp = 0;

		if (player.getArtefact() != null)
			artefactPlayer = player.getArtefact().getBonus();
		if (state.getCurrentEnnemy() != null)
		{
			if (state.getCurrentEnnemy().getArtefact() != null)
				artefactEnemy = state.getCurrentEnnemy().getArtefact().getBonus();
			enemyName = state.getCurrentEnnemy().getName();
			previousHp = state.getCurrentEnnemy().getPreviousHp();
		}

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
		PlayerViewData pvd = new PlayerViewData(name, player.getLvl(), player.getHpMax(),
		player.getHp(), player.getXpMax(), player.getXp(), player.getDefence(), player.getAttack(),
		player.getClassName(), artefactPlayer, player.getPreviousHp(), 0);

		return (new GameViewData(
			pvd,
			mapData,
			state.fightUpdate,
			enemyName,
			artefactEnemy,
			null,
			state.xpWin,
			state.isLvlUp,
			previousHp
		));
	}
}