package fr.swingy.rpg.view;

import java.util.ArrayList;
import fr.swingy.rpg.model.world.Map;
import java.util.Scanner;
import fr.swingy.rpg.model.entity.Player;
import fr.swingy.rpg.model.entity.Enemy;
import fr.swingy.rpg.model.entity.Character;
import fr.swingy.rpg.model.world.Tile;
import fr.swingy.rpg.model.artefacts.Artefact;

public class ConsoleView
{
	private Scanner scanner;

	public ConsoleView ()
	{
		this.scanner = new Scanner(System.in);
	}

	public void clearConsole()
	{
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public String askInput()
	{
		System.out.print("➜ ");
		return scanner.nextLine();
	}

	public void showMessage(String message)
	{
		System.out.println(message);
	}

	public void showLoseGame(Player player)
	{
	    clearConsole();
	    System.out.println("██╗   ██╗ ██████╗ ██╗   ██╗    ██╗      ██████╗ ███████╗███████╗");
	    System.out.println("╚██╗ ██╔╝██╔═══██╗██║   ██║    ██║     ██╔═══██╗██╔════╝██╔════╝");
	    System.out.println(" ╚████╔╝ ██║   ██║██║   ██║    ██║     ██║   ██║███████╗█████╗  ");
	    System.out.println("  ╚██╔╝  ██║   ██║██║   ██║    ██║     ██║   ██║╚════██║██╔══╝  ");
	    System.out.println("   ██║   ╚██████╔╝╚██████╔╝    ███████╗╚██████╔╝███████║███████╗");
	    System.out.println("   ╚═╝    ╚═════╝  ╚═════╝     ╚══════╝ ╚═════╝ ╚══════╝╚══════╝");
	    System.out.println();
	    System.out.println("        ☠️ ☠️ ☠️   GAME OVER  ☠️ ☠️ ☠️");
	    System.out.println();
	    showPlayer(player);
	}

	public static void showArtefactChoice(Artefact eArtefact, Artefact pArtefact)
	{
		System.out.println();
		System.out.println("The enemy drop an Artefact !");
		if (pArtefact != null)
		{
			System.out.println("Current Artefact:");
			System.out.println(pArtefact.getName() + " (" + pArtefact.getBonus() + ")");
		}
		System.out.println("New Artefact:");
		System.out.println(eArtefact.getName() + " (" + eArtefact.getBonus() + ")");
		System.out.println();
		System.out.println("Do you want to equip it ?");
		System.out.println("╔══════════════════ ACTION ══════════════════╗");
		System.out.println("║ 1 ➜ YES                                    ║");
		System.out.println("║ 2 ➜ NO                                     ║");
		System.out.println("╚════════════════════════════════════════════╝");
	}

	public void showWinGame(Player player)
	{
		clearConsole();
		System.out.println("██╗   ██╗ ██████╗ ██╗   ██╗    ██╗    ██╗██╗███╗   ██╗");
		System.out.println("╚██╗ ██╔╝██╔═══██╗██║   ██║    ██║    ██║██║████╗  ██║");
		System.out.println(" ╚████╔╝ ██║   ██║██║   ██║    ██║ █╗ ██║██║██╔██╗ ██║");
		System.out.println("  ╚██╔╝  ██║   ██║██║   ██║    ██║███╗██║██║██║╚██╗██║");
		System.out.println("   ██║   ╚██████╔╝╚██████╔╝    ╚███╔███╔╝██║██║ ╚████║");
		System.out.println("   ╚═╝    ╚═════╝  ╚═════╝      ╚══╝╚══╝ ╚═╝╚═╝  ╚═══╝");
		System.out.println();
		System.out.println("        🏆🏆🏆  CONGRATULATIONS  🏆🏆🏆");
		System.out.println();
		showPlayer(player);
	}

	public void showFightChoice(Enemy enemy)
	{
		clearConsole();
		System.out.println("You meet a " + enemy.getName() + " " + enemy.getIcon() + "(" + enemy.getHp() + "PV)");
		System.out.println("╔══════════════════ ACTION ══════════════════╗");
		System.out.println("║ 1 ➜ Fight                                  ║");
		System.out.println("║ 2 ➜ Run                                    ║");
		System.out.println("╚════════════════════════════════════════════╝");
	}

	public void showGame(Map map, Player player)
	{
		clearConsole();
		showPlayer(player);
		showMap(map, player.getPos());

		System.out.println();
		System.out.println("╔══════════════════ ACTION ══════════════════╗");
		System.out.println("║ 1 ➜ Move Up                                ║");
		System.out.println("║ 2 ➜ Move Down                              ║");
		System.out.println("║ 3 ➜ Move Right                             ║");
		System.out.println("║ 4 ➜ Move Left                              ║");
		System.out.println("║ 5 ➜ Exit Game                              ║");
		System.out.println("╚════════════════════════════════════════════╝");
	}

public void showFightUpdate(Character attacker, Character target, int damage, boolean isCritical)
{
    String prefix = attacker.getIcon() + " ➜ " + target.getIcon() + " : ";
    String message;

    if (isCritical)
    {
        message = "💥 CRITICAL! "
                + attacker.getName()
                + " attacks "
                + target.getName()
                + " for "
                + damage
                + " damage";
    }
    else
    {
        message = attacker.getName()
                + " attacks "
                + target.getName()
                + " for "
                + damage
                + " damage";
    }

    message += " | HP: " + target.getHp();

    System.out.println(prefix + message);
}


	public void showPlayer(Player player)
	{
		System.out.println("╔══════════════════ PLAYER STATS ═════════════════╗");
		System.out.println("║ 👤 Character	: " + player.getName());
		System.out.println("║ 🧙 Class	: " + player.getClassName());
		System.out.println("║	❤️  HP		: " + player.getHp() + "/" + player.getHpMax());
		System.out.println("║	🛡️  Defence	: " + player.getDefence());
		System.out.println("║	⚔️  Attack	: " + player.getAttack());
		System.out.println("║	📈 Lvl		: " + player.getLvl() + " (" + player.getXp()  + "/" + player.getXpMax() + "XP)");
		if (player.getArtefact() != null)
			System.out.println("║ Artefact : " + player.getArtefact().getName() + " (" + player.getArtefact().getBonus() + ")");
		System.out.println("╚═════════════════════════════════════════════════╝");
	}

	public void showMap(Map map, int playerPos)
	{
		int x = 0;
		int height = map.getHeight();
		ArrayList<Tile> mymap = map.getMap();

		System.out.println();
		System.out.println("🗺️  MAP");
		System.out.println("────────────────────────");

		for (Tile tile : mymap)
		{
			if (tile.getCharacter() == null)
				System.out.print("⬛");
			// else
			// 	System.out.print(tile.getCharacter().getIcon());
			else if (x == playerPos)
				System.out.print(tile.getCharacter().getIcon());
			else
				System.out.print("❓");

			x++;
			if (x % height == 0)
				System.out.println();
		}
	}
}
