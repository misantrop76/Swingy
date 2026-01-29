package fr.swingy.rpg.view.gui;

import fr.swingy.rpg.model.GameViewData;
import fr.swingy.rpg.view.View;

import javax.swing.*;
import java.awt.*;

public class GuiView implements View
{
	private JFrame frame;
	private JPanel panel;
	private CardLayout cardLayout;
	private JTextArea gameArea;
	private String choice = null;

	public void setChoice(String choice)
	{
		this.choice = choice;
	}

	@Override
	public void start()
	{
		Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
		this.frame = new JFrame("🐉 Swingy RPG");
		this.frame.setSize(tailleEcran.height / 2, tailleEcran.height / 2);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setLocationRelativeTo(null);
		this.frame.setLayout(new GridBagLayout());
		this.frame.setResizable(false);
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		this.frame.setVisible(true);
		this.panel = new JPanel();
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

	@Override
	public String askInput(String request)
	{
		while (this.choice == null);
		{
			System.out.println(this.choice);
			try
			{
				Thread.sleep(100);
			}
			catch (Exception e)
			{
				System.out.println(e);
			}
		}
		String myChoice = this.choice;
		System.out.println(myChoice);
		this.choice = null;
		return (this.choice);
	}

	@Override
	public void showMessage(String message)
	{
		gameArea.setText(message);
	}

	private void createMainMenu()
	{
		panel.removeAll();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JButton newGame = new JButton("🎮 New Game");
		JButton loadGame = new JButton("💾 Load Game");
		JButton console = new JButton("💻 Switch to Console");
		JButton exit = new JButton("❌ Exit");

		newGame.addActionListener(e -> setChoice("1"));
		loadGame.addActionListener(e -> setChoice("2"));
		console.addActionListener(e -> setChoice("3"));
		exit.addActionListener(e -> setChoice("4"));

		newGame.setAlignmentX(Component.CENTER_ALIGNMENT);
		loadGame.setAlignmentX(Component.CENTER_ALIGNMENT);
		console.setAlignmentX(Component.CENTER_ALIGNMENT);
		exit.setAlignmentX(Component.CENTER_ALIGNMENT);

		panel.add(Box.createVerticalGlue());
		panel.add(newGame);
		panel.add(Box.createVerticalStrut(30));
		panel.add(loadGame);
		panel.add(Box.createVerticalStrut(30));
		panel.add(console);
		panel.add(Box.createVerticalStrut(30));
		panel.add(exit);
		panel.add(Box.createVerticalGlue());
		panel.setBackground(Color.DARK_GRAY);
		frame.add(panel);
	}

	private JPanel createGamePanel()
	{
		JPanel panel = new JPanel(new BorderLayout());

		gameArea = new JTextArea();
		gameArea.setEditable(false);
		gameArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
		gameArea.setMargin(new Insets(10, 10, 10, 10));

		panel.add(new JScrollPane(gameArea), BorderLayout.CENTER);

		JPanel buttons = new JPanel();

		JButton fight = new JButton("⚔️ Fight");
		JButton run = new JButton("🏃 Run");

		fight.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Fight selected"));
		run.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Run selected"));

		buttons.add(fight);
		buttons.add(run);

		panel.add(buttons, BorderLayout.SOUTH);

		return panel;
	}

	@Override
	public void showMainMenu()
	{
		createMainMenu();
	}

	@Override
	public void showNewCharacterMenu()
	{
		gameArea.setText(
			"Choose your class:\n\n" +
			"1 ➜ 🤺 Warrior\n" +
			"2 ➜ 🧙 Mage\n" +
			"3 ➜ 🥷 Rogue\n" +
			"4 ➜ 🌟 Paladin\n" +
			"5 ➜ 🪓 Berserker"
		);
	}

	@Override
	public void showGame(GameViewData data)
	{
		StringBuilder sb = new StringBuilder();

		sb.append("👤 ").append(data.heroName).append(" (")
			.append(data.heroClassName).append(")\n")
			.append("❤️ ").append(data.heroHp).append("/")
			.append(data.heroHpMax).append("\n")
			.append("⚔️ ").append(data.heroAttack)
			.append(" | 🛡️ ").append(data.heroDefence).append("\n\n");

		for (String line : data.map)
			sb.append(line).append("\n");

		gameArea.setText(sb.toString());
	}

	@Override
	public void showFightChoice(String enemyName)
	{
		gameArea.setText("⚠️ Enemy encountered : " + enemyName + "\n\n1 ➜ Fight\n2 ➜ Run");
	}

	@Override
	public void showArtefactChoice(String eArtefact, String pArtefact)
	{
		gameArea.setText(
			"🎁 New Artefact:\n" + eArtefact +
			(pArtefact != null ? "\n\nCurrent:\n" + pArtefact : "")
		);
	}

	@Override
	public void showWinGame(GameViewData data)
	{
		gameArea.setText("🏆 YOU WON 🏆\nHero: " + data.heroName);
	}

	@Override
	public void showLoseGame(GameViewData data)
	{
		gameArea.setText("☠️ GAME OVER ☠️\nHero: " + data.heroName);
	}
}
