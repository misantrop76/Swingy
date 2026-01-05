package fr.swingy.rpg.controller;

import fr.swingy.rpg.view.ConsoleView;
import fr.swingy.rpg.view.MenuView;
import fr.swingy.rpg.model.GameState;
import fr.swingy.rpg.model.entity.Player;
import fr.swingy.rpg.model.factory.PlayerFactory;
import fr.swingy.rpg.model.world.Map;


public class GameController
{
	private final MenuView		mview;
	private final GameState		state;
	private final ConsoleView	view;

	public GameController(MenuView mview, ConsoleView view,GameState state)
	{
		this.mview = mview;
		this.state = state;
		this.view = view;
	}

	private void handleUserInput(String input)
	{
		switch (input)
		{
			case "1" : 
				startNewGame();
				break;
			case "2" : 
				loadGame();
				break;
			case "3" :
				state.stop();
				break;
			default  :
				view.showMessage("Invalid choice !");
				break;
		}
	}

	private void handleUserInputGameList(String input)
	{
		switch (input)
		{
			case "1" :
				state.setPlayer(PlayerFactory.createPlayer("1", "save1"));
				break;
			case "2" :
				state.setPlayer(PlayerFactory.createPlayer("1", "save2"));
				break;
			default  :
				view.showMessage("Invalid choice !");
				break;
		}
	}

	private void handleUserInputCharacter(String input)
	{
		Player player = PlayerFactory.createPlayer(input, "check");
		if (player == null)
			return;
		view.showMessage("Enter your name : ");
		String name = view.askInput();

		if (name == null)
			return;
		player.setName(name);
		state.setPlayer(player);
	}

	private void handleUserChoice(String input, Player player, int mapHeight)
	{
		int x = player.getPos() % mapHeight;
		int y = player.getPos() / mapHeight;
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
				state.stop();
				return;
			default  :
				view.showMessage("Invalid choice !");
				return;
		}
		if (x >= 0 && x < mapHeight && y >= 0 && y < mapHeight)
			player.setPos((y * mapHeight) + x);
		else
		{
			state.stop();
			view.showWinGame(player);
		}
	}

	private void gameLoop()
	{
		Player player = state.getPlayer();
		state.setMap(new Map(state.getPlayer().getLvl()));
		Map map = state.getMap();
		view.showMessage("Starting Game");
		player.setPos(map.getMap().size() / 2);
		player.setPrevPos(player.getPos());
		map.addCharacter(player.getPos(), player);
		while (state.isRunning())
		{
			view.showGame(state.getMap(), player);
			handleUserChoice(view.askInput(), player, map.getHeight());
			map.removeCharacter(player.getPrevPos());
			map.addCharacter(player.getPos(), player);
			player.setPrevPos(player.getPos());
		}
		state.stop();
	}

	private void loadGame()
	{
		mview.showGameListMenu();
		String input = view.askInput();
		while (!input.equals("3"))
		{	
			handleUserInputGameList(input);
			if (state.getPlayer() != null)
			{
				state.getPlayer().setLvl(7);
				gameLoop();
				return;
			}
			mview.showGameListMenu();
			input = view.askInput();
		}
	}

	private void startNewGame()
	{
		mview.showNewCharacterMenu();
		String input = view.askInput();
		while (!input.equals("3"))
		{
			handleUserInputCharacter(input);
			if (state.getPlayer() != null)
			{
				gameLoop();
				return;
			}
			mview.showNewCharacterMenu();
			input = view.askInput();
		}
	}

	public void startGame()
	{
		while (state.isRunning())
		{
			mview.showMainMenu();
			handleUserInput(view.askInput());
		}
	}
}
