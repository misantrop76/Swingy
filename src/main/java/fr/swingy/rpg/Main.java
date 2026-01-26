package fr.swingy.rpg;

import fr.swingy.rpg.controller.ConsoleController;
import fr.swingy.rpg.model.GameState;
import fr.swingy.rpg.view.ConsoleView;
import fr.swingy.rpg.view.MenuView;
import fr.swingy.rpg.config.ViewMode;

public class Main 
{
	public static void main( String[] args )
	{
		if (args.length != 1)
			throw new IllegalArgumentException("Error : Illegal Arguments");
		ViewMode mode;
		switch (args[0].toUpperCase())
		{
			case "GUI":
				mode = ViewMode.GUI;
				break;
			case "CONSOLE":
				mode  = ViewMode.CONSOLE;
				break;
			default:
				throw new IllegalArgumentException("Error : Illegal Arguments");
		}
		ConsoleView view = new ConsoleView();
		GameState state = new GameState();
		MenuView mview = new MenuView();

		ConsoleController controller = new ConsoleController(mview, view, state);

		controller.startGame();
	}
}
