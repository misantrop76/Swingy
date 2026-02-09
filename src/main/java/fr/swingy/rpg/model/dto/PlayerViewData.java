package fr.swingy.rpg.model.dto;

public class PlayerViewData
{
	public final String heroName;
	public final int heroLevel;
	public final int heroHpMax;
	public final int heroHp;
	public final int heroXpMax;
	public final int heroXp;
	public final int heroDefence;
	public final int heroAttack;
	public final String heroClassName;
	public final String heroArtefact;

	public PlayerViewData(String heroName, int heroLevel, int heroHpMax, int heroHp,
	int heroXpMax, int heroXp, int heroDefence, int heroAttack, String heroClassName, String heroArtefact)
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
	}
}
