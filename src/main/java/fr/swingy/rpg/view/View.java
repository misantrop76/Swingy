package fr.swingy.rpg.view;

public interface View
{
	void	start();
	int		handleMainMenu();
	void	render();
	void	showMessage(String message);
	int		askNextAction();
	void	close();
	String	getViewName();
}
