package fr.swingy.rpg.model.dto;

import fr.swingy.rpg.model.dto.PlayerViewData;
import fr.swingy.rpg.model.dto.FightUpdateView;
import java.util.ArrayList;

public class GameViewData
{
	public final PlayerViewData heroData;
	public final String[] map;
	public final ArrayList<FightUpdateView> fightUpdate;
	public final String enemyClassName;
	public final String enemyArtefact;

	public GameViewData(PlayerViewData heroData, String [] map, ArrayList<FightUpdateView> fightUpdate, String enemyClassName, String enemyArtefact)
	{
		this.heroData = heroData;
		this.map = map;
		this.fightUpdate = fightUpdate;
		this.enemyClassName = enemyClassName;
		this.enemyArtefact = enemyArtefact;
	}
}
