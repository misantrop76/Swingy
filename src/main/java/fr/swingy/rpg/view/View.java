package fr.swingy.rpg.view;

import fr.swingy.rpg.model.GameViewData;

public interface View
{
	void	start();
	void	close();
	String	askInput(String request);
	String	getViewName();
	void	showMainMenu();
	void	showNewCharacterMenu();
	void	showGame(GameViewData data);
	void	showMessage(String message);
	void	showWinGame(GameViewData data);
	void	showLoseGame(GameViewData data);
	void	showFightChoice(String enemyName);
	void	showArtefactChoice(String eArtefact, String pArtefact);
}
