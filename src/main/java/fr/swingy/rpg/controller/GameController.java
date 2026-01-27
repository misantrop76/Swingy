package fr.swingy.rpg.controller;

import fr.swingy.rpg.view.View;
import fr.swingy.rpg.model.GameState;
import fr.swingy.rpg.factory.ViewFactory;
import fr.swingy.rpg.model.GameViewData;

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

	public void startGame(String mode)
	{
		this.view = ViewFactory.create(mode);
		this.view.start();
	}

	public handleMainMenu()
	{
		String input = this.view.showMainMenu();
		String viewName = view.getViewName();
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
				state.stop();
			default:
				return;
		}
	}

	public handleCharacter()
	{
		String input = this.view.showMainMenu();
		String viewName = this.view.getViewName();

	}

	public void gameMenu()
	{
		while (this.state.isRunning() && this.state.getMenuLvl != null)
		{
			int menuLvl = this.state.getMenuLvl();
			if (menuLvl.equals(menuLvl.MAIN_MENU))
				handleMainMenu();
			else if (menuLvl.equals(menuLvl.LOAD_MENU))
				System.out.println("loadmenu");			/// TO DO
			else if (menuLvl.equals(menuLvl.CHARACTER_MENU))
				handleCharacter();
		}
	}

	public void switchView()
	{
		View newView = null;
		String viewName = view.getViewName();

		if (viewName.equals("CONSOLE"))
			newView = new ViewFactory("GUI");
		else
			newView = new ViewFactory("CONSOLE");
		this.view.close();
		this.view = newView;
		view.start();
	}

	public GameViewData getGameViewData()
	{
		Player player = state.getPlayer();
		Artefact artefact = player.getArtefact();
		int height = map.getHeight();
		Map map = state.getMap();
		char[height][height] mapData;

		int a = 0;
		int b = 0;
		for (Tile tile : map)
		{
			if (tile.getCharacter() == null)
				map[a][b] = '⬛';
			else if (x == player.getPos())
				map[a][b] = tile.getCharacter().getIcon();
			else
				map[a][b] = '❓';
			b++;
			x++;
			if (x % height == 0)
			{
				b = 0;
				a++;
			}
		}

		return (new GameViewData(
			player.getName(),
			player.getLvl(),
			player.getHp(),
			player.getDefence(),
			player.getAttack(),
			player.getArtefact().getBonus(),
			mapData,
			state.getMenuLvl()
		))
	}
}