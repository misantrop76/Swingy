package fr.swingy.rpg.view.gui;

import fr.swingy.rpg.model.GameViewData;
import fr.swingy.rpg.controller.GameController;
import fr.swingy.rpg.view.View;
import fr.swingy.rpg.view.gui.MapPanel;
import java.net.URL;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
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
		this.frame.setSize((int)(size * 1.2), (int)(size * 0.9));
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setLocationRelativeTo(null);
		this.frame.setResizable(false);
		this.frame.getContentPane().setBackground(Color.WHITE);
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
	public void showNameInput()
	{
		panel.removeAll();
		panel.updateUI();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(Color.DARK_GRAY);
		JTextField nameInput = new JTextField();

		text.setFont(new Font("Arial", Font.BOLD, 50));
		text.setForeground(Color.WHITE);
		text.setAlignmentX(Component.CENTER_ALIGNMENT);
		text.setText("Enter your name : ");

		nameInput.setColumns(20);
		nameInput.setMaximumSize(new Dimension(150, 30));
		nameInput.setAlignmentX(Component.CENTER_ALIGNMENT);
		nameInput.setToolTipText("Enter your name");
		nameInput.addActionListener(e -> controller.handleInputPlayer(nameInput.getText()));
		panel.add(Box.createVerticalGlue());
		panel.add(text);
		panel.add(Box.createVerticalStrut(20));
		panel.add(nameInput);
		panel.add(Box.createVerticalGlue());

		frame.add(panel);
		frame.setVisible(true);
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
		text.setFont(new Font("Arial", Font.BOLD, 90));
		text.setForeground(Color.WHITE);
		text.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(Box.createVerticalGlue());
		panel.add(this.text);
		panel.add(Box.createVerticalStrut(90));
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

	@Override
	public void showGameListMenu()
	{
		panel.removeAll();
		panel.updateUI();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(Color.DARK_GRAY);

		JButton rogue = createJButton("Rogue    HP : 120   ATK : 18   DEF : 10", 30);
		JButton paladin = createJButton("Paladin    HP : 120   ATK : 14   DEF : 15", 30);
		JButton berserker = createJButton("Berserker    HP : 150   ATK : 20   DEF : 7", 30);
		JButton console = createJButton("Switch to GUI mode", 50);
		JButton back = createJButton("Back", 50);

		rogue.addActionListener(e -> controller.handleInputPlayer("1"));
		paladin.addActionListener(e -> controller.handleInputPlayer("2"));
		berserker.addActionListener(e -> controller.handleInputPlayer("3"));
		console.addActionListener(e -> controller.handleInputPlayer("4"));
		back.addActionListener(e -> controller.handleInputPlayer("5"));

		text.setText("CHOOSE YOUR GAME");
		text.setFont(new Font("Arial", Font.BOLD, 70));
		text.setForeground(Color.WHITE);
		text.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(Box.createVerticalGlue());
		panel.add(text);
		panel.add(Box.createVerticalStrut(90));
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

		warrior.addActionListener(e -> controller.handleInputPlayer("1"));
		mage.addActionListener(e -> controller.handleInputPlayer("2"));
		rogue.addActionListener(e -> controller.handleInputPlayer("3"));
		paladin.addActionListener(e -> controller.handleInputPlayer("4"));
		berserker.addActionListener(e -> controller.handleInputPlayer("5"));
		console.addActionListener(e -> controller.handleInputPlayer("6"));
		back.addActionListener(e -> controller.handleInputPlayer("7"));

		text.setText("CHOOSE YOUR\n CHARACTER");
		text.setFont(new Font("Arial", Font.BOLD, 70));
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
		frame.add(panel);
		frame.setVisible(true);
	}

	private ImageIcon createEmoji(String path, int targetW, int targetH)
	{
        URL url = GuiView.class.getResource(path);
        if (url == null)
        {
            System.err.println("[MapPanel] Ressource introuvable: " + path);
            return null;
        }
		ImageIcon raw = new ImageIcon(url);
		int w = raw.getIconWidth();
		int h = raw.getIconHeight();

		if (w <= 0 || h <= 0)
			return raw;

		double scale = Math.min((double) targetW / w, (double) targetH / h);
		int newW = (int) Math.round(w * scale);
		int newH = (int) Math.round(h * scale);

		Image scaled = raw.getImage().getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
		return new ImageIcon(scaled);
	}

	@Override
	public void showGame(GameViewData data)
	{
		panel.removeAll();
		panel.updateUI();
		panel.setLayout(new BorderLayout());
		panel.setBackground(Color.DARK_GRAY);

		MapPanel mapPanel = new MapPanel(data.map);
		mapPanel.setDrawGrid(false);
		mapPanel.setBackground(Color.BLACK);
		panel.add(mapPanel, BorderLayout.CENTER);


		JPanel rightPanel = new JPanel(new BorderLayout());
		rightPanel.setPreferredSize(new Dimension(300, 0));
		rightPanel.setBackground(Color.DARK_GRAY);


		JPanel statsPanel = new JPanel();
		statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
		statsPanel.add(Box.createVerticalGlue());
		statsPanel.setBackground(Color.DARK_GRAY);
		statsPanel.add(Box.createVerticalStrut(50));
		JLabel name = new JLabel("Name : " + data.heroName);
		JLabel className = new JLabel("Class Name : " + data.heroClassName);
		JLabel hp = new JLabel("HP : " + data.heroHp + "/" + data.heroHpMax);
		JLabel atk = new JLabel("ATK : " + data.heroAttack);
		JLabel def = new JLabel("DEF : " + data.heroDefence);
		JLabel xp = new JLabel("XP : " + data.heroXp + "/" + data.heroXpMax);

		for (JLabel lbl : new JLabel[]{name, className, hp, atk, def, xp})
		{
			statsPanel.add(Box.createVerticalStrut(20));
			lbl.setForeground(Color.WHITE);
			lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
			lbl.setFont(new Font("Arial", Font.BOLD, 20));
			statsPanel.add(lbl);
		}
		if (data.heroArtefact != null)
		{
			JLabel artefact = new JLabel("Artefact : " + data.heroArtefact);
			artefact.setForeground(Color.WHITE);
			artefact.setAlignmentX(Component.CENTER_ALIGNMENT);
			artefact.setFont(new Font("Arial", Font.BOLD, 20));
			statsPanel.add(artefact);
			statsPanel.add(Box.createVerticalStrut(20));
		}
		rightPanel.add(statsPanel, BorderLayout.NORTH);


		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
		controlPanel.setBackground(Color.DARK_GRAY);

		JPanel	controle = new JPanel();
		controle.setLayout(new GridBagLayout());
	
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(5, 5, 5, 5);

		controle.setBackground(Color.DARK_GRAY);


		JButton up = new JButton(createEmoji("/arrow_up.png", 58 , 58));
		JButton down = new JButton(createEmoji("/arrow_down.png", 58, 58));
		JButton right = new JButton(createEmoji("/arrow_right.png", 58, 58));
		JButton left = new JButton(createEmoji("/arrow_left.png", 58, 58));

		JButton console = createJButton("Switch Console", 30);
		JButton exit = createJButton("Exit", 30);

		up.setPreferredSize(new Dimension(50, 50));
		down.setPreferredSize(new Dimension(50, 50));
		left.setPreferredSize(new Dimension(50, 50));
		right.setPreferredSize(new Dimension(50, 50));
	
		gbc.gridx = 1;
		gbc.gridy = 0;
		controle.add(up, gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		controle.add(down, gbc);
		gbc.gridx = 2;
		gbc.gridy = 1;
		controle.add(right, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		controle.add(left, gbc);
	
		controlPanel.add(Box.createVerticalGlue());
		controlPanel.add(controle);
		controlPanel.add(Box.createVerticalStrut(100));
		controlPanel.add(console);
		controlPanel.add(Box.createVerticalStrut(40));
		controlPanel.add(exit);
		controlPanel.add(Box.createVerticalStrut(140));

		up.addActionListener(e -> controller.handleInputPlayer("1"));
		down.addActionListener(e -> controller.handleInputPlayer("2"));
		right.addActionListener(e -> controller.handleInputPlayer("3"));
		left.addActionListener(e -> controller.handleInputPlayer("4"));
		console.addActionListener(e -> controller.handleInputPlayer("5"));
		exit.addActionListener(e -> controller.handleInputPlayer("6"));


		rightPanel.add(controlPanel, BorderLayout.SOUTH);
		panel.add(rightPanel, BorderLayout.EAST);

		frame.add(panel);
		frame.setVisible(true);
	}

	@Override
	public void showFightChoice(String enemyName)
	{
		text.setText(enemyName);
		JButton yes = createJButton("YES", 30);
		JButton no = createJButton("NO", 30);
		yes.addActionListener(e -> controller.handleInputPlayer("1"));
		no.addActionListener(e -> controller.handleInputPlayer("2"));
		panel.removeAll();
		panel.updateUI();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(Color.DARK_GRAY);
		panel.add(text);
		panel.add(yes);
		panel.add(no);
	}

	@Override
	public void showArtefactChoice(String eArtefact, String pArtefact)
	{
		if (pArtefact != null)
			eArtefact = pArtefact + " -> " + eArtefact;
		text.setText(eArtefact);
		JButton yes = createJButton("YES", 30);
		JButton no = createJButton("NO", 30);
		yes.addActionListener(e -> controller.handleInputPlayer("1"));
		no.addActionListener(e -> controller.handleInputPlayer("2"));
		panel.removeAll();
		panel.updateUI();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(Color.DARK_GRAY);
		panel.add(text);
		panel.add(yes);
		panel.add(no);
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
