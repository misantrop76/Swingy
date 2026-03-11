package fr.swingy.rpg;

import fr.swingy.rpg.controller.GameController;

public class Main
{
	public static void main(String[] args)
	{
		if (args.length != 1 || (!args[0].toUpperCase().equals("GUI")
		&& !args[0].toUpperCase().equals("CONSOLE")))
		{
			System.err.println("Error : Illegal Arguments");
			return;
		}

		GameController controller = new GameController();
		controller.resetGame(args[0].toUpperCase());
	}
}
