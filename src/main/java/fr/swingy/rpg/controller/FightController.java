package fr.swingy.rpg.controller;

import java.util.Random;
import fr.swingy.rpg.model.entity.Character;
import fr.swingy.rpg.model.entity.Player;
import fr.swingy.rpg.model.entity.Enemy;
import fr.swingy.rpg.view.View;
import fr.swingy.rpg.model.GameState;
import fr.swingy.rpg.model.world.Map;
import fr.swingy.rpg.model.artefacts.Artefact;

public class FightController 
{
	public static Boolean handleUserChoiceFight(Enemy enemy, View view)
	{
		Random random = new Random();
		int choice = 0;
		String enemystats = "You meet a " + enemy.getName() + " " + enemy.getIcon() + "(" + enemy.getHp() + "PV)";
		view.showFightChoice(enemystats);
		String input = view.askInput("Enter your choice");
		switch (input)
		{
			case "1" :
				return true;
			case "2" :
				if (random.nextBoolean())
					return true;
				else
					return false;
			default  :
				return true;
		}
	}

	public static Boolean handleArtefactChoice(Artefact artefactEnemy, Artefact artefactPlayer, View view)
	{
		String playerArtefact = null;
		if (artefactPlayer != null)
			playerArtefact = artefactEnemy.getBonus();
		view.showArtefactChoice(artefactEnemy.getBonus(), playerArtefact);
		String input = view.askInput("Enter your choice");
		switch (input)
		{
			case "1" : 
				return true;
			case "2" : 
				return false;
			default  :
				return false;
		}
	}

	public static void takeDamage(Character characterAtt, Character characterDef, View view)
	{
		Random random = new Random();

		double variance = 0.85 + (random.nextDouble() * 0.30);
		double reduction = characterDef.getDefence() / (characterDef.getDefence() + 100.0);
		double degats = (double)characterAtt.getAttack() * (1 - reduction) * variance;
		Boolean isCritical = random.nextInt(100) < 10;

		if (isCritical)
			degats *= 1.5;
		characterDef.setHp(characterDef.getHp() - (int)degats);

		String prefix = characterAtt.getIcon() + " ➜ " + characterDef.getIcon() + " : ";
		String message;

		if (isCritical)
		{
			message = "💥 CRITICAL! "
					+ characterAtt.getName()
					+ " attacks "
					+ characterDef.getName()
					+ " for "
					+ (int)degats
					+ " damage";
		}
		else
		{
			message = characterAtt.getName()
					+ " attacks "
					+ characterDef.getName()
					+ " for "
					+ (int)degats
					+ " damage";
		}

		message += " | HP: " + characterDef.getHp();

		view.showMessage(prefix + message);
	}

	public static void startFight(GameController controller,GameState state, Enemy enemy, View view)
	{
		Random random = new Random();
		Player player = state.getPlayer();
		Map map = state.getMap();
		view.showMessage("\n⚔️  LET THE BATTLE BEGIN ⚔️");

		Boolean rand = random.nextBoolean();
		while (player.getHp() > 0 && enemy.getHp() > 0)
		{
			if (rand)
				takeDamage(player, enemy, view);
			else
				takeDamage(enemy, player, view);
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
				view.showMessage(e.getMessage());
			}
		}
		if (enemy.getHp() != 0)
		{
			view.showMessage("\nYou lose the battle !");
			state.stop();
			view.showLoseGame(controller.getGameViewData());
		}
		else
		{
			view.showMessage("\nYou win the battle ! +" + ((enemy.getLvl() * 300) + (player.getLvl() * 100)) + "XP");
			Artefact artefact = enemy.getArtefact();
			if (artefact != null && handleArtefactChoice(artefact, player.getArtefact(), view))
			{
				player.setArtefact(artefact);
				player.setHp(player.getHp() + artefact.getHpBonus());
				enemy.setArtefact(null);
			}
			player.setPos(enemy.getPos());
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
