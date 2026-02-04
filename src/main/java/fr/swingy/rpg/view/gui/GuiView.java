package fr.swingy.rpg.view.gui;

import fr.swingy.rpg.model.GameViewData;
import fr.swingy.rpg.controller.GameController;
import fr.swingy.rpg.view.View;

import javax.swing.*;
import java.awt.*;

public class GuiView implements View
{
	private JFrame frame;
	private JPanel panel;
	private CardLayout cardLayout;
	private JLabel text;
	private GameController controller;

	public GuiView(GameController controller)
	{
		this.controller = controller;
	}

	@Override
	public void start()
	{
		Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
		int size = tailleEcran.height;
		this.frame = new JFrame("🐉 Swingy RPG");
		this.frame.setSize(size, size);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setLocationRelativeTo(null);
		//this.frame.setLayout(new GridBagLayout());
		this.frame.setResizable(false);
		this.frame.getContentPane().setBackground(Color.WHITE);
		// this.frame.setVisible(true);
		this.panel = new JPanel();
		this.text = new JLabel();
	}

	@Override
	public void close()
	{
		this.frame.dispose();
	}

	@Override
	public String getViewName()
	{
		return "GUI";
	}

	private JButton createJButton(String name, int size)
	{
		JButton button = new JButton(name);

		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		button.setBackground(new Color(40, 40, 40));
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Arial", Font.BOLD, size));
		button.putClientProperty("html.disable", Boolean.TRUE);
		return button;
	}

	@Override
	public void showMessage(String message)
	{
		text.setText(message);
	}

	@Override
	public void showMainMenu()
	{
		panel.removeAll();
		panel.updateUI();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(Color.DARK_GRAY);

		JButton newGame = createJButton("New Game", 40);
		JButton loadGame = createJButton("Load Game", 40);
		JButton console = createJButton("Switch to Console", 40);
		JButton exit = createJButton("Exit", 40);

		newGame.addActionListener(e -> controller.handleInputPlayer("1"));
		loadGame.addActionListener(e -> controller.handleInputPlayer("2"));
		console.addActionListener(e -> controller.handleInputPlayer("3"));
		exit.addActionListener(e -> controller.handleInputPlayer("4"));
	
		text.setText("SWINGY");
		text.setFont(new Font("Arial", Font.BOLD, 65));
		text.setForeground(Color.WHITE);
		text.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(Box.createVerticalGlue());
		panel.add(this.text);
		panel.add(Box.createVerticalStrut(80));
		panel.add(newGame);
		panel.add(Box.createVerticalStrut(50));
		panel.add(loadGame);
		panel.add(Box.createVerticalStrut(50));
		panel.add(console);
		panel.add(Box.createVerticalStrut(50));
		panel.add(exit);
		panel.add(Box.createVerticalGlue());
	
		frame.add(panel);
		frame.setVisible(true);
	}


	private void getName(String input)
	{
		controller.handleInputPlayer(input);
		controller.handleInputPlayer("Ronald");
	}

	@Override
	public void showNewCharacterMenu()
	{
		panel.removeAll();
		panel.updateUI();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(Color.DARK_GRAY);

		JButton warrior = createJButton("Warrior    HP : 130   ATK : 15   DEF : 12", 30);
		JButton mage = createJButton("Mage    HP : 80    ATK : 18   DEF : 5", 30);
		JButton rogue = createJButton("Rogue    HP : 120   ATK : 18   DEF : 10", 30);
		JButton paladin = createJButton("Paladin    HP : 120   ATK : 14   DEF : 15", 30);
		JButton berserker = createJButton("Berserker    HP : 150   ATK : 20   DEF : 7", 30);
		JButton console = createJButton("Switch to GUI mode", 50);
		JButton back = createJButton("Back", 50);

		warrior.addActionListener(e -> getName("1"));
		mage.addActionListener(e -> getName("2"));
		rogue.addActionListener(e -> getName("3"));
		paladin.addActionListener(e -> getName("4"));
		berserker.addActionListener(e -> getName("5"));
		console.addActionListener(e -> controller.handleInputPlayer("6"));
		back.addActionListener(e -> controller.handleInputPlayer("7"));

		text.setText("CHOOSE YOUR CHARACTER");
		text.setFont(new Font("Arial", Font.BOLD, 50));
		text.setForeground(Color.WHITE);
		text.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(Box.createVerticalGlue());
		panel.add(text);
		panel.add(Box.createVerticalStrut(70));
		panel.add(warrior);
		panel.add(Box.createVerticalStrut(50));
		panel.add(mage);
		panel.add(Box.createVerticalStrut(50));
		panel.add(rogue);
		panel.add(Box.createVerticalStrut(50));
		panel.add(paladin);
		panel.add(Box.createVerticalStrut(50));
		panel.add(berserker);
		panel.add(Box.createVerticalStrut(50));
		panel.add(console);
		panel.add(Box.createVerticalStrut(50));
		panel.add(back);
		panel.add(Box.createVerticalGlue());
		// frame.add(text);
		frame.add(panel);
		frame.setVisible(true);
	}

	@Override
	public void showGame(GameViewData data)
	{
		panel.removeAll();
		panel.updateUI();
		panel.setLayout(new GridLayout(0, 3, 0, 1));
		StringBuilder sb = new StringBuilder();
		JLabel stats = new JLabel(
			"<html style='color:white; font-family:Segoe UI Emoji;'>" +
			"<b>👤 " + data.heroName + "</b> (" + data.heroClassName + ")<br>" +
			"❤️ " + data.heroHp + "/" + data.heroHpMax + "<br>" +
			"⚔️ " + data.heroAttack + " &nbsp; 🛡️ " + data.heroDefence +
			"</html>"
		);
		stats.setOpaque(true);
		stats.setBackground(new Color(30, 30, 30));
		stats.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		sb.append("👤 ").append(data.heroName).append(" (")
			.append(data.heroClassName).append(")\n")
			.append("❤️ ").append(data.heroHp).append("/")
			.append(data.heroHpMax).append("\n")
			.append("⚔️ ").append(data.heroAttack)
			.append(" | 🛡️ ").append(data.heroDefence).append("\n\n");

		for (int a = 0; data.map[a] != null; a++)
			sb.append(data.map[a]).append("\n");

		JButton up = createJButton("Move Up", 40);
		JButton down = createJButton("Move Down", 40);
		JButton right = createJButton("Move Right", 40);
		JButton left = createJButton("Move Left", 40);
		JButton console = createJButton("Switch to Console mode", 40);
		JButton exit = createJButton("Exit Game", 40);

		up.addActionListener(e -> getName("1"));
		down.addActionListener(e -> getName("2"));
		right.addActionListener(e -> getName("3"));
		left.addActionListener(e -> getName("4"));
		console.addActionListener(e -> getName("5"));
		exit.addActionListener(e -> controller.handleInputPlayer("6"));

		panel.add(Box.createVerticalGlue());
		panel.add(up);
		panel.add(Box.createVerticalStrut(30));
		panel.add(down);
		panel.add(Box.createVerticalStrut(30));
		panel.add(right);
		panel.add(Box.createVerticalStrut(30));
		panel.add(left);
		panel.add(Box.createVerticalStrut(30));
		panel.add(console);
		panel.add(Box.createVerticalStrut(30));
		panel.add(exit);
		panel.add(Box.createVerticalGlue());
		panel.setBackground(Color.DARK_GRAY);
		frame.add(panel);
		panel.setVisible(true);
		frame.setVisible(true);
	}

	@Override
	public void showGameListMenu()
	{

	}

	@Override
	public void showFightChoice(String enemyName)
	{
		//gameArea.setText("⚠️ Enemy encountered : " + enemyName + "\n\n1 ➜ Fight\n2 ➜ Run");
	}

	@Override
	public void showArtefactChoice(String eArtefact, String pArtefact)
	{
		// gameArea.setText(
		// 	"🎁 New Artefact:\n" + eArtefact +
		// 	(pArtefact != null ? "\n\nCurrent:\n" + pArtefact : "")
		// );
	}

	@Override
	public void showWinGame(GameViewData data)
	{
		// gameArea.setText("🏆 YOU WON 🏆\nHero: " + data.heroName);
	}

	@Override
	public void showLoseGame(GameViewData data)
	{
		// gameArea.setText("☠️ GAME OVER ☠️\nHero: " + data.heroName);
	}
}
