package fr.swingy.rpg;

import fr.swingy.rpg.controller.GameController;

public class Main
{
	public static void main(String[] args)
	{
		if (args.length != 1)
			throw new IllegalArgumentException("Error : Illegal Arguments");
		switch (args[0].toUpperCase())
		{
			case "GUI":
				break;
			case "CONSOLE":
				break;
			default:
				throw new IllegalArgumentException("Error : Illegal Arguments");
		}

		GameController controller = new GameController();

		controller.startGame(args[0].toUpperCase());
	}
}
