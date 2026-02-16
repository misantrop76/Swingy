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
			case "GUI" -> {
                }
			case "CONSOLE" -> {
                }
			default -> throw new IllegalArgumentException("Error : Illegal Arguments");
		}

		GameController controller = new GameController();
		controller.resetGame(args[0].toUpperCase());
	}
}
