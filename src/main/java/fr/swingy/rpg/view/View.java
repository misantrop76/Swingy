package fr.swingy.rpg.view;

import java.util.List;

import fr.swingy.rpg.model.dto.GameViewData;
import fr.swingy.rpg.model.dto.PlayerViewData;

public interface View
{
	void start();
	void close();

	void showMainMenu();
	void showNewCharacterMenu();
	void showGame(GameViewData data);
	void showGameListMenu(List <PlayerViewData> savedPlayer);
	void showLoseGame(GameViewData data);
	void showWinGame(GameViewData data);
	void showFight(GameViewData data);
	void showFightChoice(GameViewData data);
	void showNameInput();
	void showRmMenu(List <PlayerViewData> savedPlayer);

	String getViewName();
}

