package fr.swingy.rpg.model.dto;

import java.util.ArrayList;

public class GameViewData
{
	public final PlayerViewData heroData;
	public final String[] map;
	public final ArrayList<FightUpdateView> fightUpdate;
	public final String		enemyClassName;
	public final String		enemyArtefact;
	public final String		winText;
	public final int		xpWin;
	public final Boolean	isLvlUp;
	public final int		previousHp;

	public GameViewData(PlayerViewData heroData, String [] map, ArrayList<FightUpdateView> fightUpdate,
	String enemyClassName, String enemyArtefact, String winText, int xpWin, Boolean isLvlUp, int previousHp)
	{
		this.heroData = heroData;
		this.map = map;
		this.fightUpdate = fightUpdate;
		this.enemyClassName = enemyClassName;
		this.enemyArtefact = enemyArtefact;
		this.winText = winText;
		this.xpWin = xpWin;
		this.isLvlUp = isLvlUp;
		this.previousHp = previousHp;
	}
}
