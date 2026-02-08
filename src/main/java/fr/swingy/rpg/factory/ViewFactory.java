package fr.swingy.rpg.factory;

import fr.swingy.rpg.controller.GameController;
import fr.swingy.rpg.view.View;
import fr.swingy.rpg.view.console.ConsoleView;
import fr.swingy.rpg.view.gui.GuiView;

public class ViewFactory
{

	public static View create(String mode, GameController controller)
	{
		if ("console".equalsIgnoreCase(mode))
		{
			return new ConsoleView(controller);
		}
		return new GuiView(controller);
	}
}
