package fr.swingy.rpg.view;

import fr.swingy.rpg.controller.GameController;
import fr.swingy.rpg.model.GameViewData;

public interface View
{
	void start();
	void close();

	void showMainMenu();
	void showNewCharacterMenu();
	void showGame(GameViewData data);
	void showGameListMenu();
	void showMessage(String message);
	void showLoseGame(GameViewData data);
	void showWinGame(GameViewData data);
	void showArtefactChoice(String eArtefact, String pArtefact);
	void showFightChoice(String enemyName);

	String getViewName();
}

