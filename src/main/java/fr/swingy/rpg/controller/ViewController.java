package fr.swingy.rpg.controller;

import fr.swingy.rpg.config.ViewMode;

public class ViewController
{
	private ViewMode mode;

	public ViewController(ViewMode mode)
	{
		this.mode = mode;
	}

	public void view()
	{
		if (mode == ViewMode.CONSOLE)
		{
		}
		else
		{

		}
	}
}