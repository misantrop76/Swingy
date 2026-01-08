package fr.swingy.rpg.model.artefacts;

public class Attack extends Artefact
{
	public Attack(int lvl, String name)
	{
		this.name = name;
		this.defenceBonus = 0;
		this.attackBonus = 5 * lvl;
		this.hpBonus = 0;
		this.lvl = lvl;
	}
}