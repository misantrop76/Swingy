package fr.swingy.rpg.model.artefacts;

public class Shield extends Artefact
{
	public Shield(int lvl, String name)
	{
		this.name = name;
		this.defenceBonus = 5 * lvl;
		this.attackBonus = 0;
		this.hpBonus = 0;
		this.lvl = lvl;
	}
}