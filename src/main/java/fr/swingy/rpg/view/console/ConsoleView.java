package fr.swingy.rpg.view.console;

import java.util.Scanner;

import fr.swingy.rpg.controller.GameController;
import fr.swingy.rpg.model.GameViewData;
import fr.swingy.rpg.view.View;

public class ConsoleView implements View, Runnable
{
	private final Scanner scanner = new Scanner(System.in);;
	private Thread inputThread;
	private boolean isRunning;
	private GameController controller;

	public ConsoleView(GameController controller)
	{
		this.isRunning = false;
		this.controller = controller;
	}

	@Override
	public void start()
	{
		this.inputThread = new Thread(this);
		isRunning = true;
		inputThread.start();
	}

	@Override
	public void run()
	{
		System.out.println();
		while (isRunning)
			controller.handleInputPlayer(scanner.nextLine());
	}

	@Override
	public void close()
	{
		isRunning = false;
	}

	public void clearConsole()
	{
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	@Override
	public void showStartFight()
	{
		System.out.println("LET'S BATTLE BEGIN !");
	}

	@Override
	public void showUpdateFight(String fightUpdate)
	{
		System.out.println(fightUpdate);
	}

	@Override
	public void showMessage(String message)
	{
		System.out.println(message);
	}

	@Override
	public void showNameInput()
	{
		System.out.print("Enter your name : ");
	}

	@Override
	public void showLoseGame(GameViewData data)
	{
	    clearConsole();
	    System.out.println("██╗   ██╗ ██████╗ ██╗   ██╗    ██╗      ██████╗ ███████╗███████╗");
	    System.out.println("╚██╗ ██╔╝██╔═══██╗██║   ██║    ██║     ██╔═══██╗██╔════╝██╔════╝");
	    System.out.println(" ╚████╔╝ ██║   ██║██║   ██║    ██║     ██║   ██║███████╗█████╗  ");
	    System.out.println("  ╚██╔╝  ██║   ██║██║   ██║    ██║     ██║   ██║╚════██║██╔══╝  ");
	    System.out.println("   ██║   ╚██████╔╝╚██████╔╝    ███████╗╚██████╔╝███████║███████╗");
	    System.out.println("   ╚═╝    ╚═════╝  ╚═════╝     ╚══════╝ ╚═════╝ ╚══════╝╚══════╝");
	    System.out.println();
	    System.out.println("                       GAME OVER");
	    System.out.println();
	    showPlayer(data);
	}

	@Override
	public void showArtefactChoice(String eArtefact, String pArtefact)
	{
		System.out.println();
		System.out.println("The enemy drop an Artefact !");
		System.out.println();
		if (pArtefact != null)
		{
			System.out.println("Current Artefact:");
			System.out.println(pArtefact);
			System.out.println();
		}
		System.out.println("New Artefact:");
		System.out.println(eArtefact);
		System.out.println();
		System.out.println("Do you want to equip it ?");
		System.out.println("╔══════════════════ ACTION ══════════════════╗");
		System.out.println("║ 1 -> YES                                   ║");
		System.out.println("║ 2 -> NO                                    ║");
		System.out.println("╚════════════════════════════════════════════╝");
		System.out.print("Enter your choice : ");
	}

	@Override
	public void showWinGame(GameViewData data)
	{
		clearConsole();
		System.out.println("██╗   ██╗ ██████╗ ██╗   ██╗    ██╗    ██╗██╗███╗   ██╗");
		System.out.println("╚██╗ ██╔╝██╔═══██╗██║   ██║    ██║    ██║██║████╗  ██║");
		System.out.println(" ╚████╔╝ ██║   ██║██║   ██║    ██║ █╗ ██║██║██╔██╗ ██║");
		System.out.println("  ╚██╔╝  ██║   ██║██║   ██║    ██║███╗██║██║██║╚██╗██║");
		System.out.println("   ██║   ╚██████╔╝╚██████╔╝    ╚███╔███╔╝██║██║ ╚████║");
		System.out.println("   ╚═╝    ╚═════╝  ╚═════╝      ╚══╝╚══╝ ╚═╝╚═╝  ╚═══╝");
		System.out.println();
		System.out.println("               CONGRATULATIONS");
		System.out.println();
		showPlayer(data);
	}

	@Override
	public void showFightChoice(String enemyName)
	{
		clearConsole();
		System.out.println(enemyName);
		System.out.println("╔══════════════════ ACTION ══════════════════╗");
		System.out.println("║ 1 -> Fight                                 ║");
		System.out.println("║ 2 -> Run                                   ║");
		System.out.println("╚════════════════════════════════════════════╝");
		System.out.print("Enter your choice : ");
	}

	@Override
	public void showGame(GameViewData data)
	{
		clearConsole();
		showPlayer(data);
		showMap(data);

		System.out.println();
		System.out.println("╔══════════════════ ACTION ══════════════════╗");
		System.out.println("║ 1 -> Move Up                               ║");
		System.out.println("║ 2 -> Move Down                             ║");
		System.out.println("║ 3 -> Move Right                            ║");
		System.out.println("║ 4 -> Move Left                             ║");
		System.out.println("║ 5 -> Switch to GUI mode                    ║");
		System.out.println("║ 6 -> Exit Game                             ║");
		System.out.println("╚════════════════════════════════════════════╝");
		System.out.print("Enter your choice : ");
	}

	private void showPlayer(GameViewData data)
	{
		System.out.println("╔══════════════════ PLAYER STATS ═════════════════╗");
		System.out.println("║ Character : " + data.heroName);
		System.out.println("║     Class : " + data.heroClassName);
		System.out.println("║           HP : " + data.heroHp + "/" + data.heroHpMax);
		System.out.println("║	   Defence : " + data.heroDefence);
		System.out.println("║	    Attack : " + data.heroAttack);
		System.out.println("║          Lvl : " + data.heroLevel + " (" + data.heroXp  + "/" + data.heroXpMax + "XP)");
		if (data.heroArtefact != null)
			System.out.println("║     Artefact : " + data.heroArtefact);
		System.out.println("╚═════════════════════════════════════════════════╝");
	}

	private void showMap(GameViewData data)
	{
		System.out.println();
		System.out.println("🗺️  MAP");
		System.out.println("────────────────────────");

		for (int x = 0; data.map[x] != null; x++)
		{
			System.out.print(data.map[x]);
			System.out.println();
		}
	}

	@Override
	public void showGameListMenu()
	{
		clearConsole();
		showTitle();

		System.out.println("╔══════════════ LOAD GAME ══════════════╗");
		System.out.println("║ 1 -> Saved Game 1                     ║");
		System.out.println("║ 2 -> Saved Game 2                     ║");
		System.out.println("║ 3 -> Saved Game 2                     ║");
		System.out.println("║ 4 -> Switch to GUI mode               ║");	
		System.out.println("║ 5 -> Back                             ║");
		System.out.println("╚═══════════════════════════════════════╝");
		System.out.print("Enter your choice : ");
	}

	@Override
	public void showMainMenu()
	{
		clearConsole();
		showTitle();

		System.out.println("╔══════════════ MAIN MENU ═════════════╗");
		System.out.println("║ 1 -> New Game                        ║");
		System.out.println("║ 2 -> Load Game                       ║");
		System.out.println("║ 3 -> Switch to GUI mode              ║");
		System.out.println("║ 4 -> Exit                            ║");
		System.out.println("╚══════════════════════════════════════╝");
		System.out.print("Enter your choice : ");
	}

	@Override
	public void showNewCharacterMenu()
	{
		clearConsole();
		showTitle();

		System.out.println("╔══════════ CHOOSE YOUR CHARACTER ══════════╗");
		System.out.println("║ 1 -> Warrior                              ║");
		System.out.println("║     HP : 130   ATK : 15   DEF : 12        ║");
		System.out.println("║                                           ║");
		System.out.println("║ 2 -> Mage                                 ║");
		System.out.println("║     HP : 80    ATK : 18   DEF : 5         ║");
		System.out.println("║                                           ║");
		System.out.println("║ 3 -> Rogue                                ║");
		System.out.println("║     HP : 120   ATK : 18   DEF : 10        ║");
		System.out.println("║                                           ║");
		System.out.println("║ 4 -> Paladin                              ║");
		System.out.println("║     HP : 120   ATK : 14   DEF : 15        ║");
		System.out.println("║                                           ║");
		System.out.println("║ 5 -> Berserker                            ║");
		System.out.println("║     HP : 150   ATK : 20   DEF : 7         ║");
		System.out.println("║                                           ║");
		System.out.println("║ 6 -> Switch to GUI mode                   ║");
		System.out.println("║                                           ║");
		System.out.println("║ 7 -> Back                                 ║");
		System.out.println("╚═══════════════════════════════════════════╝");
		System.out.print("Enter your choice : ");
	}

	private void showTitle()
	{
		System.out.println("╔══════════════════════════════════════╗");
		System.out.println("║              SWINGY RPG              ║");
		System.out.println("╚══════════════════════════════════════╝");
		System.out.println();
	}

	@Override
	public String getViewName()
	{
		return ("CONSOLE");
	}
}
