package fr.swingy.rpg.controller;

import java.util.Random;

import fr.swingy.rpg.model.GameState;
import fr.swingy.rpg.model.artefacts.Artefact;
import fr.swingy.rpg.model.entity.Character;
import fr.swingy.rpg.model.entity.Enemy;
import fr.swingy.rpg.model.entity.Player;
import fr.swingy.rpg.model.world.Map;
import fr.swingy.rpg.model.dto.FightUpdateView;

public class FightController 
{
	public static void handleUserChoiceFight(GameController controller, GameState state, String input)
	{
		Random random = new Random();

		switch (input)
		{
			case "1" :
				startFight(controller, state);
				break;
			case "2" :
				if (random.nextBoolean())
					startFight(controller, state);
				else
					state.setGameLvl(GameController.GameLvl.MAP);
				break;
			default  :
				startFight(controller, state);
				break;
		}
	}

	public static void handleArtefactChoice(GameState state, String input)
	{
		Artefact artefact = state.getCurrentEnnemy().getArtefact();
		Player player = state.getPlayer();

		switch (input)
		{
			case "1" : 
				player.setArtefact(artefact);
				player.setHp(player.getHp() + artefact.getHpBonus());
				break;
			default  :
				break;
		}
		state.setCurrentEnnemy(null);
		state.setGameLvl(GameController.GameLvl.MAP);
	}

	public static FightUpdateView newFightUpdate(Character characterAtt, Character characterDef, boolean isPlayerAttacking)
	{
		Random random = new Random();

		double variance = 0.85 + (random.nextDouble() * 0.30);
		double reduction = characterDef.getDefence() / (characterDef.getDefence() + 100.0);
		double degats = (double)characterAtt.getAttack() * (1 - reduction) * variance;
		Boolean isCritical = random.nextInt(100) < 10;

		if (isCritical)
			degats *= 1.5;
		characterDef.setHp(characterDef.getHp() - (int)degats);
		FightUpdateView fightUpdate = new FightUpdateView(isPlayerAttacking, isCritical, (int)degats,
		isPlayerAttacking ? characterAtt.getHp(): characterDef.getHp(), isPlayerAttacking ? characterDef.getHp(): characterAtt.getHp());

		return (fightUpdate);
	}

	public static void startFight(GameController controller,GameState state)
	{
		Random random = new Random();
		Player player = state.getPlayer();
		Map map = state.getMap();
		Boolean rand = random.nextBoolean();
		Enemy enemy = state.getCurrentEnnemy();
		state.fightUpdate = new FightUpdateView[0];
		int i = 0;
		while (player.getHp() > 0 && enemy.getHp() > 0)
		{
			if (rand)
				state.fightUpdate[i] = newFightUpdate(player, enemy, true);
			else
				state.fightUpdate[i] = newFightUpdate(enemy, player, false);
			rand = rand ? false: true;
			i++;
		}
		if (enemy.getHp() != 0)
			state.setGameLvl(GameController.GameLvl.LOSE);
		else
		{
			state.setGameLvl(GameController.GameLvl.ARTEFACT);
			//iew.showUpdateFight("\nYou win the battle ! +" + ((enemy.getLvl() * 300) + (player.getLvl() * 100)) + "XP");
			state.getMap().addCharacter(player.getPos(), null, null);
			player.setPos(enemy.getPos());
			state.getMap().addCharacter(enemy.getPos(), player, null);
			player.setXp(player.getXp() + (enemy.getLvl() * 300) + (player.getLvl() * 100));
			while (player.getXp() >= player.getXpMax())
			{
				player.updateLvl();
				map.updateMap(player.getLvl(), player);
			}
		}
	}
}
