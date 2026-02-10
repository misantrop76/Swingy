package fr.swingy.rpg.view.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import fr.swingy.rpg.controller.GameController;
import fr.swingy.rpg.model.dto.FightUpdateView;
import fr.swingy.rpg.model.dto.GameViewData;
import fr.swingy.rpg.view.View;

public class GuiView implements View
{
	private JFrame frame;
	private JPanel panel;
	private final GameController controller;

	public GuiView(GameController controller)
	{
		this.controller = controller;
	}

	@Override
	public void start()
	{
		Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
		int size = tailleEcran.height;

		this.frame = new JFrame("Swingy RPG");
		this.frame.setSize((int)(size * 1.2), (int)(size * 0.9));
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setLocationRelativeTo(null);
		this.frame.setResizable(false);
		this.frame.getContentPane().setBackground(Color.DARK_GRAY);
		this.panel = new JPanel();
		this.frame.add(this.panel);
		this.frame.setVisible(true);
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


	private void updateWindow()
	{
		panel.revalidate();
		panel.repaint();
		panel.updateUI();
		frame.revalidate();
		frame.repaint();
	}

	private JLabel createJLabel(String text, int size)
	{
		JLabel newJLabel = new JLabel(text);

		newJLabel.setFont(new Font("Arial", Font.BOLD, size));
		newJLabel.setForeground(Color.WHITE);
		newJLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		return (newJLabel);
	}

	private JButton createJButton(String name, int size)
	{
		JButton button = new JButton(name);

		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		button.setBackground(new Color(40, 40, 40));
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Arial", Font.BOLD, size));

		return button;
	}

	@Override
	public void showNameInput()
	{

		JTextField nameInput = new JTextField();
		JLabel text = createJLabel("Enter your name : ", 50);

		nameInput.setFont(new Font("Arial", Font.BOLD, 40));
		nameInput.setForeground(Color.DARK_GRAY);
		nameInput.setColumns(20);
		nameInput.setMaximumSize(new Dimension(300, 50));
		nameInput.setAlignmentX(Component.CENTER_ALIGNMENT);
		nameInput.addActionListener(e -> controller.handleInputPlayer(nameInput.getText()));

		panel.removeAll();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(Color.DARK_GRAY);
		panel.add(Box.createVerticalGlue());
		panel.add(text);
		panel.add(Box.createVerticalStrut(40));
		panel.add(nameInput);
		panel.add(Box.createVerticalStrut(50));
		panel.add(Box.createVerticalGlue());

		updateWindow();
	}

	@Override
	public void showMainMenu()
	{
		JButton newGame = createJButton("New Game", 40);
		JButton loadGame = createJButton("Load Game", 40);
		JButton console = createJButton("Switch to Console", 40);
		JButton exit = createJButton("Exit", 40);
		JLabel text = createJLabel("SWINGY", 70);

		newGame.addActionListener(e -> controller.handleInputPlayer("1"));
		loadGame.addActionListener(e -> controller.handleInputPlayer("2"));
		console.addActionListener(e -> controller.handleInputPlayer("3"));
		exit.addActionListener(e -> controller.handleInputPlayer("4"));
	
		panel.removeAll();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(Color.DARK_GRAY);
		panel.add(Box.createVerticalGlue());
		panel.add(text);
		panel.add(Box.createVerticalStrut(90));
		panel.add(newGame);
		panel.add(Box.createVerticalStrut(50));
		panel.add(loadGame);
		panel.add(Box.createVerticalStrut(50));
		panel.add(console);
		panel.add(Box.createVerticalStrut(50));
		panel.add(exit);
		panel.add(Box.createVerticalGlue());

		updateWindow();
	}

	@Override
	public void showGameListMenu()
	{

		JButton rogue = createJButton("Rogue    HP : 120   ATK : 18   DEF : 10", 30);
		JButton paladin = createJButton("Paladin    HP : 120   ATK : 14   DEF : 15", 30);
		JButton berserker = createJButton("Berserker    HP : 150   ATK : 20   DEF : 7", 30);
		JButton console = createJButton("Switch to GUI mode", 50);
		JButton back = createJButton("Back", 50);
		JLabel text = createJLabel("CHOOSE YOUR GAME", 70);

		rogue.addActionListener(e -> controller.handleInputPlayer("1"));
		paladin.addActionListener(e -> controller.handleInputPlayer("2"));
		berserker.addActionListener(e -> controller.handleInputPlayer("3"));
		console.addActionListener(e -> controller.handleInputPlayer("4"));
		back.addActionListener(e -> controller.handleInputPlayer("5"));

		panel.removeAll();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(Color.DARK_GRAY);
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

		updateWindow();
	}

	@Override
	public void showNewCharacterMenu()
	{

		JButton warrior = createJButton("Warrior    HP : 130   ATK : 15   DEF : 12", 30);
		JButton mage = createJButton("Mage    HP : 80    ATK : 18   DEF : 5", 30);
		JButton rogue = createJButton("Rogue    HP : 120   ATK : 18   DEF : 10", 30);
		JButton paladin = createJButton("Paladin    HP : 120   ATK : 14   DEF : 15", 30);
		JButton berserker = createJButton("Berserker    HP : 150   ATK : 20   DEF : 7", 30);
		JButton console = createJButton("Switch to GUI mode", 50);
		JButton back = createJButton("Back", 50);
		JLabel text = createJLabel("CHOOSE YOUR\n CHARACTER", 70);

		warrior.addActionListener(e -> controller.handleInputPlayer("1"));
		mage.addActionListener(e -> controller.handleInputPlayer("2"));
		rogue.addActionListener(e -> controller.handleInputPlayer("3"));
		paladin.addActionListener(e -> controller.handleInputPlayer("4"));
		berserker.addActionListener(e -> controller.handleInputPlayer("5"));
		console.addActionListener(e -> controller.handleInputPlayer("6"));
		back.addActionListener(e -> controller.handleInputPlayer("7"));

		panel.removeAll();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(Color.DARK_GRAY);
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

		updateWindow();
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
		Dimension screenSize = panel.getSize();

		JPanel	controlPanel = new JPanel();
		JPanel	statsPanel = new JPanel();
		JPanel	controle = new JPanel();
		JPanel	rightPanel = new JPanel(new BorderLayout());
		MapPanel mapPanel = new MapPanel(data.map);
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		JLabel name = new JLabel("Name : " + data.heroData.heroName);
		JLabel className = new JLabel("Class Name : " + data.heroData.heroClassName);
		JLabel hp = new JLabel("HP : " + data.heroData.heroHp + "/" + data.heroData.heroHpMax);
		JLabel atk = new JLabel("ATK : " + data.heroData.heroAttack);
		JLabel def = new JLabel("DEF : " + data.heroData.heroDefence);
		JLabel xp = new JLabel("LVL " + data.heroData.heroLevel + " : " + data.heroData.heroXp + "/" + data.heroData.heroXpMax + " XP");
		JLabel artefact = new JLabel();
		
		JButton up = new JButton(createEmoji("/arrow_up.png", 58, 58));
		JButton down = new JButton(createEmoji("/arrow_down.png", 58, 58));
		JButton right = new JButton(createEmoji("/arrow_right.png", 58, 58));
		JButton left = new JButton(createEmoji("/arrow_left.png", 58, 58));
		JButton console = createJButton("Switch Console", 30);
		JButton exit = createJButton("Exit", 30);
		
		up.addActionListener(e -> controller.handleInputPlayer("1"));
		down.addActionListener(e -> controller.handleInputPlayer("2"));
		right.addActionListener(e -> controller.handleInputPlayer("3"));
		left.addActionListener(e -> controller.handleInputPlayer("4"));
		console.addActionListener(e -> controller.handleInputPlayer("5"));
		exit.addActionListener(e -> controller.handleInputPlayer("6"));

		mapPanel.setPreferredSize( new Dimension(screenSize.height, screenSize.height));
		mapPanel.setDrawGrid(false);
		mapPanel.setBackground(Color.BLACK);

		statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
		statsPanel.add(Box.createVerticalGlue());
		statsPanel.setBackground(Color.DARK_GRAY);
		statsPanel.add(Box.createVerticalStrut(50));

		for (JLabel lbl : new JLabel[]{name, className, hp, atk, def, xp})
		{
			statsPanel.add(Box.createVerticalStrut(20));
			lbl.setForeground(Color.WHITE);
			lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
			lbl.setFont(new Font("Arial", Font.BOLD, 20));
			statsPanel.add(lbl);
		}
		if (data.heroData.heroArtefact != null)
		{
			statsPanel.add(Box.createVerticalStrut(20));
			artefact.setText("Artefact : " + data.heroData.heroArtefact);
			artefact.setForeground(Color.WHITE);
			artefact.setAlignmentX(Component.CENTER_ALIGNMENT);
			artefact.setFont(new Font("Arial", Font.BOLD, 20));
			statsPanel.add(artefact);
		}


		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
		controlPanel.setBackground(Color.DARK_GRAY);

		up.setPreferredSize(new Dimension(50, 50));
		down.setPreferredSize(new Dimension(50, 50));
		left.setPreferredSize(new Dimension(50, 50));
		right.setPreferredSize(new Dimension(50, 50));

		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(5, 5, 5, 5);
		controle.setLayout(new GridBagLayout());
		controle.setBackground(Color.DARK_GRAY);
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

		rightPanel.setBackground(Color.DARK_GRAY);
		rightPanel.setPreferredSize(new Dimension(screenSize.width - screenSize.height, screenSize.height));
		rightPanel.add(statsPanel, BorderLayout.NORTH);
		rightPanel.add(controlPanel, BorderLayout.SOUTH);


		panel.removeAll();
		panel.setLayout(new BorderLayout());
		panel.add(mapPanel, BorderLayout.CENTER);
		panel.add(rightPanel, BorderLayout.EAST);

		updateWindow();
	}

	@Override
	public void showFightChoice(GameViewData data)
	{
		String enemyName = data.enemyClassName;

		JLabel text = createJLabel(enemyName, 70);
		JLabel fight = createJLabel("Fight ?", 70);
		JButton yes = createJButton("YES", 30);
		JButton no = createJButton("NO", 30);

		yes.addActionListener(e -> controller.handleInputPlayer("1"));
		no.addActionListener(e -> controller.handleInputPlayer("2"));

		panel.removeAll();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(Color.DARK_GRAY);
		panel.add(Box.createVerticalGlue());
		panel.add(text);
		panel.add(Box.createVerticalStrut(10));
		panel.add(fight);
		panel.add(Box.createVerticalStrut(100));
		panel.add(yes);
		panel.add(Box.createVerticalStrut(30));
		panel.add(no);
		panel.add(Box.createVerticalStrut(50));
		panel.add(Box.createVerticalGlue());

		updateWindow();
	}

	private void showArtefactChoice(GameViewData data)
	{
		String eArtefact = data.enemyArtefact;
		String pArtefact = data.heroData.heroArtefact;
		if (pArtefact != null)
			eArtefact = pArtefact + " -> " + eArtefact;

		JLabel text = createJLabel("The enemy drop an artefact !", 70);
		JLabel question = createJLabel("Equip it ?", 70);
		JLabel proposal = createJLabel(eArtefact, 70);
		JButton yes = createJButton("YES", 30);
		JButton no = createJButton("NO", 30);
	
		yes.addActionListener(e -> controller.handleInputPlayer("1"));
		no.addActionListener(e -> controller.handleInputPlayer("2"));

		panel.removeAll();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(Color.DARK_GRAY);
		panel.add(Box.createVerticalGlue());
		panel.add(text);
		panel.add(Box.createVerticalStrut(10));
		panel.add(question);
		panel.add(Box.createVerticalStrut(30));
		panel.add(proposal);
		panel.add(Box.createVerticalStrut(100));
		panel.add(yes);
		panel.add(Box.createVerticalStrut(30));
		panel.add(no);
		panel.add(Box.createVerticalStrut(50));
		panel.add(Box.createVerticalGlue());

		updateWindow();
	}

	@Override
	public void showFight(GameViewData data)
	{
		int index[] = {0};
		Timer timer = new Timer(1000, null);
		JLabel text = createJLabel("LET'S THE FIGHT BEGIN !!!", 40);

		panel.removeAll();		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(Box.createVerticalGlue());
		panel.add(text);
		panel.add(Box.createVerticalGlue());
		updateWindow();
		timer.addActionListener(e -> 
		{
			if (index[0] >= data.fightUpdate.size())
			{
				timer.stop();
				if (data.enemyArtefact != null)
					showArtefactChoice(data);
				else
					controller.handleInputPlayer("");
				return;
			}
			FightUpdateView fightHit = data.fightUpdate.get(index[0]++);
			String annonce = "";
			if (fightHit.isCritical)
				annonce += "Bim !! Critical Hit ! ";
			if (fightHit.isPlayerAttacking)
				annonce += "You attack the " + data.enemyClassName + ", causing " + fightHit.damage + " damage. " + fightHit.enemyHp + " HP left.";
			else
				annonce += "The " + data.enemyClassName + " attack You, causing " + fightHit.damage + " damage. " + fightHit.playerHp + " HP left.";
			text.setText(annonce);

			panel.removeAll();
			panel.add(Box.createVerticalGlue());
			panel.add(text);
			panel.add(Box.createVerticalGlue());
			updateWindow();
		});
		timer.start();
	}

	@Override
	public void showWinGame(GameViewData data)
	{
	}

	@Override
	public void showLoseGame(GameViewData data)
	{
		JButton newGame = createJButton("Main Menu", 40);
		JButton console = createJButton("Switch to Console", 40);
		JButton exit = createJButton("Exit", 40);
		JLabel text = createJLabel("YOU LOSE !", 70);

		newGame.addActionListener(e -> controller.handleInputPlayer("1"));
		console.addActionListener(e -> controller.handleInputPlayer("2"));
		exit.addActionListener(e -> controller.handleInputPlayer("3"));
	
		panel.removeAll();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(Color.DARK_GRAY);
		panel.add(Box.createVerticalGlue());
		panel.add(text);
		panel.add(Box.createVerticalStrut(90));
		panel.add(newGame);
		panel.add(Box.createVerticalStrut(50));
		panel.add(console);
		panel.add(Box.createVerticalStrut(50));
		panel.add(exit);
		panel.add(Box.createVerticalGlue());

		updateWindow();
	}
}
