package fr.swingy.rpg.factory;

import fr.swingy.rpg.model.entity.Berserker;
import fr.swingy.rpg.model.entity.Mage;
import fr.swingy.rpg.model.entity.Paladin;
import fr.swingy.rpg.model.entity.Player;
import fr.swingy.rpg.model.entity.Rogue;
import fr.swingy.rpg.model.entity.Warrior;

public class PlayerFactory 
{
	public static Player createPlayer(String choice, String name)
	{
		return switch (choice) {
			case "1" -> new Warrior(name);
			case "2" -> new Mage(name);
			case "3" -> new Rogue(name);
			case "4" -> new Paladin(name);
			case "5" -> new Berserker(name);
			default -> null;
		};
	}
}
