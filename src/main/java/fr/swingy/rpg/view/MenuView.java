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
	}

	public void showNewCharacterMenu()
	{
		clearConsole();
		showTitle();

		System.out.println("╔════════════ CREATE CHARACTER ════════════╗");
		System.out.println("║ 1 ➜ 🤺 Warrior                           ║");
		System.out.println("║     HP : 130   ATK : 15   DEF : 12       ║");
		System.out.println("║                                          ║");
		System.out.println("║ 2 ➜ 🧙 Mage                              ║");
		System.out.println("║     HP : 80    ATK : 18   DEF : 5        ║");
		System.out.println("║                                          ║");
		System.out.println("║ 3 ➜ 🥷 Rogue                             ║");
		System.out.println("║     HP : 100   ATK : 16   DEF : 8        ║");
		System.out.println("║                                          ║");
		System.out.println("║ 4 ➜ 🌟 Paladin                           ║");
		System.out.println("║     HP : 120   ATK : 14   DEF : 15       ║");
		System.out.println("║                                          ║");
		System.out.println("║ 5 ➜ 🪓 Berserker                         ║");
		System.out.println("║     HP : 150   ATK : 20   DEF : 7        ║");
		System.out.println("║                                          ║");
		System.out.println("║ 6 ➜ Back                                 ║");
		System.out.println("╚══════════════════════════════════════════╝");
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
