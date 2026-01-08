package fr.swingy.rpg.model.artefacts;

public abstract class Artefact
{
	protected int attackBonus;
	protected int hpBonus;
	protected int defenceBonus;
	protected String icon;
	protected String name;
	protected int lvl;

	public int getAttackBonus()
	{
		return (this.attackBonus);
	}

	public int getHpBonus()
	{
		return (this.hpBonus);
	}

	public int getlvl()
	{
		return (this.hpBonus);
	}

	public int getDefenceBonus()
	{
		return (this.defenceBonus);
	}

	public String getIcon()
	{
		return (this.icon);
	}

	public String getName()
	{
		return (this.name);
	}
}
