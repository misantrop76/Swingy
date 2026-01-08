package fr.swingy.rpg.model.artefacts;

public class Health extends Artefact
{
	public Health(int lvl, String name)
	{
		this.name = name;
		this.defenceBonus = 0;
		this.attackBonus = 0;
		this.hpBonus = 30 * lvl;
		this.lvl = lvl;
	}
}