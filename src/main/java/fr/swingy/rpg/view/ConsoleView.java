package fr.swingy.rpg.view;

import java.util.Scanner;
import fr.swingy.rpg.model.entity.Player;

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
		return (scanner.nextLine());
	}

	public void showMessage(String message)
	{
		clearConsole();
		System.out.println(message);
	}

	public void showPlayer(Player player)
	{
		clearConsole();
		System.out.print("Character : ");
		System.out.println(player.getName());
		System.out.print("Class : ");
		System.out.println(player.getClassName());
		System.out.print("HP : ");
		System.out.println(player.getHp());
		System.out.print("Defence : ");
		System.out.println(player.getDefence());
		System.out.print("Attack : ");
		System.out.println(player.getAttack());
	}
}
