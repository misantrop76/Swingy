package fr.swingy.rpg.controller;

import fr.swingy.rpg.view.ConsoleView;
import fr.swingy.rpg.view.MenuView;
import fr.swingy.rpg.model.GameState;
import fr.swingy.rpg.model.entity.Player;
import fr.swingy.rpg.model.factory.PlayerFactory;

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

	private void loadGame()
	{
		mview.showGameListMenu();
		String input = view.askInput();
		while (!input.equals("3"))
		{	
			handleUserInputGameList(input);
			if (state.getPlayer() != null)
			{
				view.showMessage("Starting Game");
				view.showPlayer(state.getPlayer());
				state.stop();
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
				view.showMessage("Starting Game");
				view.showPlayer(state.getPlayer());
				state.stop();
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
