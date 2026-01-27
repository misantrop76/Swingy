package fr.swingy.rpg.model;

public class GameViewData
{
	public final int menuLvl;
	public final String heroName;
	public final int heroLevel;
	public final int heroHp;
	public final int heroDefence;
	public final int heroAttack;
	public final String heroArtefact;
	public final int heroHp;
	public final char[] map;


	public GameViewData(String heroName, int heroLevel, int heroHp,
	int heroDefence, int heroAttack, String heroArtefact, char [][]map, int menuLvl)
	{
		this.heroName = heroName;
		this.heroLevel = heroLevel;
		this.heroHp = heroHp;
		this.map = map;
		this.menuLvl = menuLvl;
	}
}
