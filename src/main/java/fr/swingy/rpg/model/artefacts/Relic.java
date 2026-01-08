package fr.swingy.rpg.model.artefacts;

public class Relic extends Artefact
{
	public Relic(int lvl, String name)
	{
		this.name = name;
		this.defenceBonus = 2 * lvl;
		this.attackBonus = 2 * lvl;
		this.hpBonus = 15 * lvl;
		this.lvl = lvl;
	}
}