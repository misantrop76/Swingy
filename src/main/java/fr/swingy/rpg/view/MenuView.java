package fr.swingy.rpg.view;

public class MenuView
{
	public void clearConsole()
	{
        System.out.print("\033[H\033[2J");
        System.out.flush();
	}

	public void showGameListMenu()
	{
		clearConsole();
		System.out.println("====== SWINGY RPG ======");
		System.out.println("1. Game 1");
		System.out.println("2. Game 2");
		System.out.println("3. Back");
		System.out.print("> ");
	}

	public void showMainMenu()
	{
		clearConsole();
		System.out.println("====== SWINGY RPG ======");
		System.out.println("1. New Game");
		System.out.println("2. Load Game");
		System.out.println("3. Exit");
		System.out.print("> ");
	}

	public void showNewCharacterMenu()
	{
		clearConsole();
		System.out.println("====== SWINGY RPG ======");
		System.out.println("Choose your character :");
		System.out.println("1. Warrior	(HP:120 ATK:15 DEF:10)");
		System.out.println("2. Mage		(HP:80  ATK:25 DEF:3)");
		System.out.println("3. Back");
		System.out.print("> ");
	}
}
