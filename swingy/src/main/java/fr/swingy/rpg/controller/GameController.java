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
				exitGame();
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
				view.showMessage("Loading Game 1");
				exitGame();
				break;
			case "2" : 
				view.showMessage("Loading Game 2");
				exitGame();
				break;
			case "3" :
				break;
			default  :
				view.showMessage("Invalid choice !");
				loadGame();
				break;
		}
	}

	private void handleUserInputCharacter(String input)
	{
		Player player = PlayerFactory.createPlayer(input, "check");
		if (input.equals("3"))
		{
			startGame();
			return;
		}
		if (player == null)
		{
			view.showMessage("Invalid choice !");
			startNewGame();
			return;
		}
		view.showMessage("Enter your name : ");
		String name = view.askInput();

		if (name == null)
		{
			startNewGame();
			return;
		}
		player.setName(name);
		state.setPlayer(player);
	}

	private void loadGame()
	{
		view.showMessage("Current game :");
		mview.showGameListMenu();
		handleUserInputGameList(view.askInput());
	}

	private void startNewGame()
	{
		mview.showNewCharacterMenu();
		handleUserInputCharacter(view.askInput());
		if (state.getPlayer() != null)
		{
			view.showMessage("Starting Game");
			view.showPlayer(state.getPlayer());
			state.stop();
		}
	}

	private void exitGame()
	{
		state.stop();
	}

	public void startGame()
	{
		view.showMessage("Welcome to the rpg Game");
		while (state.isRunning())
		{
			mview.showMainMenu();
			handleUserInput(view.askInput());
		}
	}
}
