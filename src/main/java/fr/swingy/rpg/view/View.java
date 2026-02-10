package fr.swingy.rpg.view;

import fr.swingy.rpg.model.dto.GameViewData;

public interface View
{
	void start();
	void close();

	void showMainMenu();
	void showNewCharacterMenu();
	void showGame(GameViewData data);
	void showGameListMenu();
	void showLoseGame(GameViewData data);
	void showWinGame(GameViewData data);
	void showFight(GameViewData data);
	void showFightChoice(GameViewData data);
	void showNameInput();

	String getViewName();
}

