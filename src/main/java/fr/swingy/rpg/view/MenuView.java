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
		showTitle();

		System.out.println("╔══════════════ LOAD GAME ══════════════╗");
		System.out.println("║ 1 ➜ Saved Game 1                      ║");
		System.out.println("║ 2 ➜ Saved Game 2                      ║");
		System.out.println("║ 3 ➜ Back                              ║");
		System.out.println("╚══════════════════════════════════════╝");
		System.out.print("➜ ");
	}

	public void showMainMenu()
	{
		clearConsole();
		showTitle();

		System.out.println("╔══════════════ MAIN MENU ═════════════╗");
		System.out.println("║ 1 ➜ New Game                         ║");
		System.out.println("║ 2 ➜ Load Game                        ║");
		System.out.println("║ 3 ➜ Exit                             ║");
		System.out.println("╚══════════════════════════════════════╝");
		System.out.print("➜ ");
	}

	public void showNewCharacterMenu()
	{
		clearConsole();
		showTitle();

		System.out.println("╔════════════ CREATE CHARACTER ════════════╗");
		System.out.println("║ 1 ➜ 🗡️  Warrior                           ║");
		System.out.println("║     HP : 120   ATK : 15   DEF : 10       ║");
		System.out.println("║                                          ║");
		System.out.println("║ 2 ➜ 🔮 Mage                              ║");
		System.out.println("║     HP : 80    ATK : 25   DEF : 3        ║");
		System.out.println("║                                          ║");
		System.out.println("║ 3 ➜ Back                                 ║");
		System.out.println("╚══════════════════════════════════════════╝");
		System.out.print("➜ ");
	}

	/* ======== TITLE ======== */
	private void showTitle()
	{
		System.out.println("╔══════════════════════════════════════╗");
		System.out.println("║          🐉  SWINGY RPG  🐉          ║");
		System.out.println("╚══════════════════════════════════════╝");
		System.out.println();
	}
}
