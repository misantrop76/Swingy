package fr.swingy.rpg.model.factory;

import fr.swingy.rpg.model.entity.Mage;
import fr.swingy.rpg.model.entity.Player;
import fr.swingy.rpg.model.entity.Warrior;

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
			default : 
				return null;
		}
	}
}
