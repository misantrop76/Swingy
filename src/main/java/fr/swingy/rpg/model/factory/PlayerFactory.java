package fr.swingy.rpg.model.factory;

import fr.swingy.rpg.model.entity.Mage;
import fr.swingy.rpg.model.entity.Player;
import fr.swingy.rpg.model.entity.Warrior;
import fr.swingy.rpg.model.entity.Rogue;
import fr.swingy.rpg.model.entity.Paladin;
import fr.swingy.rpg.model.entity.Berserker;

public class PlayerFactory 
{
	public static Player createPlayer(String choice, String name)
	{
		switch (choice)
		{
			case "1" :
				return new Warrior(name);
			case "2" :
				return new Mage(name);
			case "3" :
				return new Rogue(name);
			case "4" :
				return new Paladin(name);
			case "5" :
				return new Berserker(name);
			default : 
				return null;
		}
	}
}
