package fr.swingy.rpg.model.factory;


import fr.swingy.rpg.model.artefacts.Attack;
import fr.swingy.rpg.model.artefacts.Health;
import fr.swingy.rpg.model.artefacts.Relic;
import fr.swingy.rpg.model.artefacts.Shield;
import fr.swingy.rpg.model.artefacts.Artefact;
import java.util.Random;

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
			return (new Attack(lvl, "Attack"));
		else if (rand < 60)
			return (new Health(lvl, "Health"));
		else if (rand < 90)
			return (new Shield(lvl, "Shield"));
		return (new Relic(lvl, "Relic"));
	}

}
