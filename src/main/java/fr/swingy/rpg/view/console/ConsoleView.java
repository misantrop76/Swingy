package fr.swingy.rpg.view.console;

import java.util.Scanner;

import fr.swingy.rpg.controller.GameController;
import fr.swingy.rpg.model.dto.FightUpdateView;
import fr.swingy.rpg.model.dto.GameViewData;
import fr.swingy.rpg.view.View;

public class ConsoleView implements View, Runnable
{
	private final Scanner scanner = new Scanner(System.in);
	private Thread inputThread;
	private boolean isRunning;
	private final GameController controller;

    private final String reset = "\u001B[0m";
    private final String red = "\u001B[31m";
    private final String green = "\u001B[32m";
    private final String blue = "\u001B[33m";
	private final String resetScreen = "\033[H\033[2J";


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
		System.out.print(resetScreen);
		System.out.flush();
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
		System.out.println("╔══════════════════ ACTION ══════════════════╗");
		System.out.println("║ 1 -> Main Menu                             ║");
		System.out.println("║ 2 -> Switch to GUI mode                    ║");
		System.out.println("║ 3 -> Exit Game                             ║");
		System.out.println("╚════════════════════════════════════════════╝");
		System.out.print("Enter your choice : ");
	}

	private void printSpace(int space)
	{
		for (int i = 0; i < space; i++)
			System.out.print(" ");
		System.out.println("║");
	}

	@Override
	public void showFight(GameViewData data)
	{
		clearConsole();
		for (FightUpdateView fightHit : data.fightUpdate)
		{
			String annonce = "";
			if (fightHit.isCritical)
				annonce += "Bim !! Critical Hit ! ";
			if (fightHit.isPlayerAttacking)
				annonce += "You attack the " + data.enemyClassName + ", causing " + fightHit.damage + " damage. " +fightHit.enemyHp + " HP left.";
			else
				annonce += "The " + data.enemyClassName + " attack You, causing " + fightHit.damage + " damage. " +fightHit.playerHp + " HP left.";
			System.out.println(annonce);
			try
			{
				Thread.sleep(500);
			}
			catch (Exception e)
			{
				System.err.println(e);
			}
		}
		if (data.enemyArtefact != null)
		{
			System.out.println();
			System.out.println("The enemy drop an Artefact !");
			System.out.println();
			if (data.heroData.heroArtefact != null)
			{
				System.out.println("Current Artefact:");
				System.out.println(data.heroData.heroArtefact);
				System.out.println();
			}
			System.out.println("New Artefact:");
			System.out.println(data.enemyArtefact);
			System.out.println();
			System.out.println("Do you want to equip it ?");
			System.out.println("╔══════════════════ ACTION ══════════════════╗");
			System.out.println("║ 1 -> YES                                   ║");
			System.out.println("║ 2 -> NO                                    ║");
			System.out.println("╚════════════════════════════════════════════╝");
			System.out.print("Enter your choice : ");
		}
		else
			controller.handleInputPlayer("");
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
	public void showFightChoice(GameViewData data)
	{
		clearConsole();
		System.out.println("You meet a " + data.enemyClassName);
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
		System.out.println("╔════════════════ PLAYER STATS ═══════════════╗");
		System.out.print("║ Character : " + data.heroData.heroName);
		printSpace(32 - data.heroData.heroName.length());
		System.out.print("║     Class : " + data.heroData.heroClassName);
		printSpace(32 - data.heroData.heroClassName.length());
		System.out.print("║        HP : " + data.heroData.heroHp + "/" + data.heroData.heroHpMax);
		printSpace(31 - (Integer.toString(data.heroData.heroHp).length() + Integer.toString(data.heroData.heroHp).length()));
		System.out.print("║   Defence : " + data.heroData.heroDefence);
		printSpace(32 - Integer.toString(data.heroData.heroDefence).length());
		System.out.print("║    Attack : " + data.heroData.heroAttack);
		printSpace(32 - Integer.toString(data.heroData.heroAttack).length());
		System.out.print("║       Lvl : " + data.heroData.heroLevel + " (" + data.heroData.heroXp  + "/" + data.heroData.heroXpMax + "XP)");
		printSpace(26 - (Integer.toString(data.heroData.heroLevel).length() + Integer.toString(data.heroData.heroXp).length() + Integer.toString(data.heroData.heroXpMax).length()));
		if (data.heroData.heroArtefact != null)
		{
			System.out.print("║  Artefact : " + data.heroData.heroArtefact);
			printSpace(32 - data.heroData.heroArtefact.length());
		}
		System.out.println("╚═════════════════════════════════════════════╝");
	}

	private void showMap(GameViewData data)
	{
		System.out.println();
		for (int i = 0; i < data.map[0].length() - 1; i++)
			System.out.print(" ");
		System.out.println("MAP");
		for (int i = 0; i < data.map[0].length() * 2; i++)
			System.out.print("─");
		System.out.println();

		for (int x = 0; data.map[x] != null; x++)
		{
			for (int i = 0; i < data.map[x].length(); i++)
			{
				switch (data.map[x].charAt(i))
				{
					case 'P':
						System.out.print(green + " P" + reset);
						break;
					case '?':
						System.out.print(red + " ?" + reset);
						break;
					default:
						System.out.print(blue +  " O" + reset);
						break;
				}
			}
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
