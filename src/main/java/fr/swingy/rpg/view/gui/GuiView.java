package fr.swingy.rpg.view.gui;

import fr.swingy.rpg.model.GameViewData;
import fr.swingy.rpg.view.View;

import javax.swing.*;

public class GuiView implements View
{
	private JFrame frame;

	@Override
	public void start()
	{
		frame = new JFrame("Swingy RPG");
		frame.setSize(600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	@Override
	public void close()
	{
		frame.dispose();
	}

	@Override
	public String getViewName()
	{
		return "GUI";
	}

	@Override
	public String askInput(String request)
	{
		return JOptionPane.showInputDialog(frame, request);
	}

	@Override
	public void showMessage(String message)
	{
		JOptionPane.showMessageDialog(frame, message);
	}

	@Override
	public void showMainMenu()
	{
		String[] options = {
			"New Game",
			"Load Game",
			"Switch to Console",
			"Exit"
		};

		int choice = JOptionPane.showOptionDialog(
			frame,
			"🐉 SWINGY RPG 🐉",
			"Main Menu",
			JOptionPane.DEFAULT_OPTION,
			JOptionPane.INFORMATION_MESSAGE,
			null,
			options,
			options[0]
		);
	}

	@Override
	public void showNewCharacterMenu()
	{
		String message =
			"Choose your class:\n\n" +
			"1 - 🤺 Warrior (HP 130 / ATK 15 / DEF 12)\n" +
			"2 - 🧙 Mage (HP 80 / ATK 18 / DEF 5)\n" +
			"3 - 🥷 Rogue (HP 120 / ATK 18 / DEF 10)\n" +
			"4 - 🌟 Paladin (HP 120 / ATK 14 / DEF 15)\n" +
			"5 - 🪓 Berserker (HP 150 / ATK 20 / DEF 7)\n\n" +
			"Enter your choice:";

		JOptionPane.showMessageDialog(frame, message);
	}

	@Override
	public void showGame(GameViewData data)
	{
		StringBuilder sb = new StringBuilder();

		sb.append("👤 ").append(data.heroName).append(" (")
		  .append(data.heroClassName).append(")\n");
		sb.append("❤️ HP ").append(data.heroHp).append("/")
		  .append(data.heroHpMax).append("\n");
		sb.append("⚔️ ATK ").append(data.heroAttack)
		  .append(" | 🛡️ DEF ").append(data.heroDefence).append("\n");
		sb.append("📈 LVL ").append(data.heroLevel)
		  .append(" (").append(data.heroXp).append("/")
		  .append(data.heroXpMax).append(" XP)\n");

		if (data.heroArtefact != null)
			sb.append("🧩 Artefact: ").append(data.heroArtefact).append("\n");

		sb.append("\n🗺️ MAP\n");
		sb.append("────────────\n");

		for (String line : data.map)
		{
			sb.append(line).append("\n");
		}

		JOptionPane.showMessageDialog(
			frame,
			sb.toString(),
			"Game",
			JOptionPane.INFORMATION_MESSAGE
		);
	}

	@Override
	public void showFightChoice(String enemyName)
	{
		String[] options = { "Fight ⚔️", "Run 🏃" };

		JOptionPane.showOptionDialog(
			frame,
			"You encounter: " + enemyName,
			"Fight",
			JOptionPane.DEFAULT_OPTION,
			JOptionPane.WARNING_MESSAGE,
			null,
			options,
			options[0]
		);
	}

	@Override
	public void showArtefactChoice(String eArtefact, String pArtefact)
	{
		String message = "New artefact dropped:\n\n" + eArtefact;

		if (pArtefact != null)
			message += "\n\nCurrent artefact:\n" + pArtefact;

		String[] options = { "Equip", "Ignore" };

		JOptionPane.showOptionDialog(
			frame,
			message,
			"Artefact",
			JOptionPane.DEFAULT_OPTION,
			JOptionPane.QUESTION_MESSAGE,
			null,
			options,
			options[0]
		);
	}

	@Override
	public void showWinGame(GameViewData data)
	{
		JOptionPane.showMessageDialog(
			frame,
			"🏆🏆🏆 CONGRATULATIONS 🏆🏆🏆\n\n" +
			"You won the game!\n\n" +
			"Hero: " + data.heroName,
			"Victory",
			JOptionPane.INFORMATION_MESSAGE
		);
	}

	@Override
	public void showLoseGame(GameViewData data)
	{
		JOptionPane.showMessageDialog(
			frame,
			"☠️☠️☠️ GAME OVER ☠️☠️☠️\n\n" +
			"Hero: " + data.heroName,
			"Defeat",
			JOptionPane.ERROR_MESSAGE
		);
	}
}
