package fr.swingy.rpg.controller;

import java.util.Random;
import fr.swingy.rpg.model.entity.Character;
import fr.swingy.rpg.model.entity.Player;
import fr.swingy.rpg.model.entity.Enemy;
import fr.swingy.rpg.view.ConsoleView;

public class FightController 
{
	public static void startFight(Player player, Enemy enemy)
	{
		Random random = new Random();
		System.out.println("⚔️ LET THE BATTLE BEGIN ⚔️");

		Boolean rand = random.nextBoolean();
		while (player.getHp() > 0 && enemy.getHp() > 0)
		{
			if (rand)
				player.takeDamage(enemy);
			else
				enemy.takeDamage(player);
			if (rand)
				rand = false;
			else
				rand = true;
			try
			{
				Thread.sleep(500);
			}
			catch (Exception e)
			{
				System.out.println(e);
			}
		}
		ConsoleView view = new ConsoleView();
		if (enemy.getHp() != 0)
			view.showMessage("You lose the fight !");
		else
		{
			view.showMessage("You win the fight !");
			player.setXp(player.getXp() + (enemy.getLvl() * 300) + (player.getLvl() * 100));
		}
		try
		{
			Thread.sleep(2000);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
}
