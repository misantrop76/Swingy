package fr.swingy.rpg.factory;

import java.util.Random;

import fr.swingy.rpg.model.artefacts.Artefact;
import fr.swingy.rpg.model.artefacts.Attack;
import fr.swingy.rpg.model.artefacts.Health;
import fr.swingy.rpg.model.artefacts.Relic;
import fr.swingy.rpg.model.artefacts.Shield;

public class ArtefactFactory
{
	private static final Random random = new Random();

	public static Artefact createArtefact(int lvl)
	{
		if (lvl == 1 && random.nextInt(100) >= 30)
			return (null);
		else if (lvl == 2 && random.nextInt(100) < 30)
			return (null);
		if (lvl > 3)
			lvl = 3;
		int rand = random.nextInt(100);
		if (rand < 30)
			return (new Attack(lvl, "⚔️"));
		else if (rand < 60)
			return (new Health(lvl, "❤️"));
		else if (rand < 90)
			return (new Shield(lvl, "⛨"));
		return (new Relic(lvl, "💎"));
	}
}
