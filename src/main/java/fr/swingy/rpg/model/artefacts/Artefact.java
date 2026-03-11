package fr.swingy.rpg.model.artefacts;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public abstract class Artefact
{
	@Positive(message = "attackBonus cannot be negatif")
	protected int attackBonus;
	@Positive(message = "hpBonus cannot be negatif")
	protected int hpBonus;
	@Positive(message = "defenceBonus cannot be negatif")
	protected int defenceBonus;
	@NotBlank(message = "name cannot be blank")
	protected String name;
	@Positive(message = "lvl cannot be negatif")
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
		return (this.lvl);
	}

	public int getDefenceBonus()
	{
		return (this.defenceBonus);
	}

	public String getName()
	{
		return (this.name);
	}

	public String getBonus()
	{
		String bonus = "";
		if (this == null)
			return (null);
		bonus += this.name + " lvl " + this.lvl + "(";
		if (this.attackBonus != 0)
			bonus = bonus + "+" + this.attackBonus + "⚔️ ";
		if (this.hpBonus != 0)
			bonus = bonus + "+" + this.hpBonus + "❤️ ";
		if (this.defenceBonus != 0)
			bonus = bonus + "+" + this.defenceBonus + "⛨ ";
		bonus += ")";
		return bonus;
	}
}
