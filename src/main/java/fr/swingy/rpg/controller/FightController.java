package fr.swingy.rpg.controller;

import java.util.Random;
import fr.swingy.rpg.model.entity.Character;
import fr.swingy.rpg.model.entity.Player;
import fr.swingy.rpg.model.entity.Enemy;
import fr.swingy.rpg.view.ConsoleView;
import fr.swingy.rpg.model.world.Map;
import fr.swingy.rpg.model.artefacts.Artefact;
public class FightController 
{
	public static Boolean handleArtefactChoice(Artefact artefactEnemy, Artefact artefactPlayer)
	{
		ConsoleView view = new ConsoleView();
		view.showArtefactChoice(artefactEnemy, artefactPlayer);
		String input = view.askInput();
		switch (input)
		{
			case "1" : 
				return true;
			case "2" : 
				return false;
			default  :
				return true;
		}
	}

	public static void startFight(Player player, Enemy enemy, Map map)
	{
		Random random = new Random();
		System.out.println();
		System.out.println("⚔️  LET THE BATTLE BEGIN ⚔️");

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
			view.showMessage("\nYou lose the battle !");
		else
		{
			Artefact artefact = enemy.getArtefact();
			if (artefact != null && handleArtefactChoice(artefact, player.getArtefact()))
			{
				player.setArtefact(artefact);
				enemy.setArtefact(null);
			}
			map.removeCharacter(enemy.getPos());
			player.setPos(enemy.getPos());
			view.showMessage("\nYou win the battle ! +" + ((enemy.getLvl() * 300) + (player.getLvl() * 100)) + "XP");
			player.setXp(player.getXp() + (enemy.getLvl() * 300) + (player.getLvl() * 100));
			if (player.getXp() >= player.getXpMax())
			{
				while (player.getXp() >= player.getXpMax())
				{
					player.updateLvl();
					view.showMessage("\n⬆️  LEVEL UP ⬆️");
				}
				map.updateMap(player.getLvl(), player);
			}
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
