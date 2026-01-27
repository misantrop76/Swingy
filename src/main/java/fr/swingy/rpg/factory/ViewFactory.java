package fr.swingy.rpg.factory;

import fr.swingy.rpg.view.gui.GuiView;
import fr.swingy.rpg.view.console.ConsoleView;

public class ViewFactory
{

	public static View create(String mode)
	{
		if ("console".equalsIgnoreCase(mode))
		{
			return new ConsoleView();
		}
		return new GuiView();
	}
}
