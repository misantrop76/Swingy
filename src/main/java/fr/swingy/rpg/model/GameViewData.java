package fr.swingy.rpg.model;

public class GameViewData
{
	public final String heroName;
	public final int heroLevel;
	public final int heroHpMax;
	public final int heroXp;
	public final int heroXpMax;
	public final int heroHp;
	public final int heroDefence;
	public final int heroAttack;
	public final String heroClassName;
	public final String heroArtefact;
	public final String[] map;


	public GameViewData(String heroName, int heroLevel, int heroXp, int heroXpMax,
		int heroHp, int heroHpMax, int heroDefence, int heroAttack, String heroArtefact,
		String [] map, String heroClassName)
	{
		this.heroName = heroName;
		this.heroLevel = heroLevel;
		this.heroXp = heroXp;
		this.heroXpMax = heroXpMax;
		this.heroHp = heroHp;
		this.heroHpMax = heroHpMax;
		this.heroDefence = heroDefence;
		this.heroAttack = heroAttack;
		this.heroArtefact = heroArtefact;
		this.heroClassName = heroClassName;
		this.map = map;
	}
}
