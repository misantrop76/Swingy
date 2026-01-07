package fr.swingy.rpg.model.factory;

import fr.swingy.rpg.model.entity.Monster;
import fr.swingy.rpg.model.entity.Dragon;
import fr.swingy.rpg.model.entity.DarkKnight;
import fr.swingy.rpg.model.entity.Skeleton;
import fr.swingy.rpg.model.entity.Enemy;
import java.util.Random;

public class EnemyFactory
{
	private static final Random random = new Random();

	public static Enemy createEnemy(int lvl)
	{
		int ratio = random.nextInt(100);

		if (lvl == 1)
				return (new Monster("Monster"));
		else if (lvl <= 3)
		{
			if (ratio < 50)
				return (new Monster("Monster"));
			else
				return (new Skeleton("Skeleton"));
		}
		else if (lvl < 5)
		{
			if (ratio < 30)
				return (new Monster("Monster"));
			else if (ratio < 90)
				return (new Skeleton("Skeleton"));
			else
				return (new DarkKnight("Dark Knight"));
		}
		else if (lvl < 7)
		{
			if (ratio < 20)
				return (new Monster("Monster"));
			else if (ratio < 60)
				return (new Skeleton("Skeleton"));
			else if (ratio < 95)
				return (new DarkKnight("Dark Knight"));
			else
				return (new Dragon("Dragon"));
		}
		else
		{
			if (ratio < 40)
				return (new Skeleton("Skeleton"));
			else if (ratio < 75)
				return (new DarkKnight("Dark Knight"));
			else
				return (new Dragon("Dragon"));
		}
	}
}
