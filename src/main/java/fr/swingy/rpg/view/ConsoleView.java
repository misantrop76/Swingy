package fr.swingy.rpg.view;

import java.util.ArrayList;
import fr.swingy.rpg.model.world.Map;
import java.util.Scanner;
import fr.swingy.rpg.model.entity.Player;
import fr.swingy.rpg.model.entity.Character;
import fr.swingy.rpg.model.world.Tile;

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
		clearConsole();
		System.out.println("╔════════════════════════════════════╗");
		System.out.println("║              MESSAGE               ║");
		System.out.println("╠════════════════════════════════════╣");
		System.out.println("  " + message);
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

	public void showPlayer(Player player)
	{
		System.out.println("╔══════════════════ PLAYER STATS ═════════════════╗");
		System.out.println("║ 👤 Character	: " + player.getName());
		System.out.println("║ 🧙 Class	: " + player.getClassName());
		System.out.println("║	❤️  HP		: " + player.getHp());
		System.out.println("║	🛡️  Defence	: " + player.getDefence());
		System.out.println("║	⚔️  Attack	: " + player.getAttack());
		System.out.println("║	📈 Lvl		: " + player.getLvl() + " (" + player.getXp()  + "/" + player.getXpMax() + "XP)");
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
			else
				System.out.print(tile.getCharacter().getIcon());

			x++;
			if (x % height == 0)
				System.out.println();
		}
	}
}
