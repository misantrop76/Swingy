package fr.swingy.rpg;

import fr.swingy.rpg.controller.GameController;
import fr.swingy.rpg.model.GameState;
import fr.swingy.rpg.view.ConsoleView;
import fr.swingy.rpg.view.MenuView;

public class Main 
{
	public static void main( String[] args )
	{
		ConsoleView view = new ConsoleView();
		GameState state = new GameState();
		MenuView mview = new MenuView();

		GameController controller = new GameController(mview, view, state);

		controller.startGame();
    }
}
