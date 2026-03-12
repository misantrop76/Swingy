package fr.swingy.rpg.view.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import fr.swingy.rpg.model.dto.FightUpdateView;
import fr.swingy.rpg.model.dto.GameViewData;

class FightPanel extends JPanel
{
	private final Image playerImg;
	private final Image enemyImg;
	private final Image impactImg;
	private final Image criticalImpactImg;

	private boolean showCriticalText = false;
	private float criticalTextX;
	private float criticalTextY;

	private final int playerHp, playerHpMax;
	private final int enemyHp, enemyHpMax;

	private int displayedPlayerHp, displayedEnemyHp;

	private float playerX, enemyX;
	private float basePlayerX, baseEnemyX;
	private float playerY, enemyY;

	private float impactX, impactY;
	private boolean showImpact = false;

	private enum Phase { DASH, IMPACT, RETURN }
	private Phase phase;

	private static final int IMG_SIZE = 200;
	private static final int BAR_WIDTH = 220;


	public FightPanel(GameViewData data)
	{
		setBackground(Color.DARK_GRAY);
		setDoubleBuffered(true);
		playerHp = data.heroData.previousHp;
		playerHpMax = data.heroData.heroHpMax;

		enemyHp = data.previousHp;
		enemyHpMax = enemyHp;

		displayedPlayerHp = playerHp;
		displayedEnemyHp = enemyHp;

		playerImg = scale(MapPanel.loadImageFromResources(
				"/" + data.heroData.heroClassName.toLowerCase() + ".png"));
		enemyImg = scale(MapPanel.loadImageFromResources("/" + data.enemyClassName.toLowerCase() + ".png"));
		impactImg = scale(MapPanel.loadImageFromResources("/slash.png"), 150, 150);
		criticalImpactImg = scale(MapPanel.loadImageFromResources("/critical.png"), 250, 250);
		

		addComponentListener(new ComponentAdapter()
		{
			@Override
			public void componentResized(ComponentEvent e)
			{
				initPositions();
			}
		});
	}

	private Image scale(Image img)
	{
		return img.getScaledInstance(IMG_SIZE, IMG_SIZE, Image.SCALE_SMOOTH);
	}

	private Image scale(Image img, int w, int h)
	{
		return img.getScaledInstance(w, h, Image.SCALE_SMOOTH);
	}

	private void initPositions()
	{
		playerY = getHeight() / 2f - IMG_SIZE / 2f;
		enemyY = getHeight() / 2f - IMG_SIZE / 2f;

		basePlayerX = getWidth() / 4f - IMG_SIZE / 2f;
		baseEnemyX = getWidth() * 3f / 4f - IMG_SIZE / 2f;

		playerX = basePlayerX;
		enemyX = baseEnemyX;
	}

public void animateHit(FightUpdateView update, Runnable callback)
{

	boolean playerAttacks = update.isPlayerAttacking;
	int targetHp = playerAttacks ? update.enemyHp : update.playerHp;

	phase = Phase.DASH;
	showImpact = false;
	showCriticalText = false;

	final float dashDistance = 400f;
	final float speed = 30f;
	final float[] dashProgress = {0f};

	Timer timer = new Timer(16, null);
	timer.setCoalesce(true);
	timer.addActionListener(e ->
	{
		switch (phase)
		{

			case DASH ->
			{
				if (dashProgress[0] < dashDistance)
				{
					if (playerAttacks)
						playerX += speed;
					else
						enemyX -= speed;
					dashProgress[0] += speed;
				}
				else
				{
					phase = Phase.IMPACT;
					showImpact = true;
					impactX = playerAttacks ? enemyX + IMG_SIZE/2f : playerX + IMG_SIZE/2f;
					impactY = playerAttacks ? enemyY + IMG_SIZE/2f : playerY + IMG_SIZE/2f;
				}

		        if (update.isCritical)
		        {
		            showCriticalText = true;
		            criticalTextX = impactX;
		            criticalTextY = impactY - 150;
		        }
			}
			case IMPACT ->
			{
				if (playerAttacks)
				{
					displayedEnemyHp = Math.max(targetHp, displayedEnemyHp - Math.max(1, (displayedEnemyHp - targetHp) / 5));
					if (displayedEnemyHp <= targetHp) phase = Phase.RETURN;
				}
				else
				{
					displayedPlayerHp = Math.max(targetHp, displayedPlayerHp - Math.max(1, (displayedPlayerHp - targetHp) / 5));
					if (displayedPlayerHp <= targetHp)
						phase = Phase.RETURN;
				}
			}
			case RETURN ->
			{
				if (playerAttacks)
				{
					playerX -= speed;
					if (playerX <= basePlayerX)
					{
						playerX = basePlayerX;
						finish(callback);
						timer.stop();
					}
				}
				else
				{
					enemyX += speed;
					if (enemyX >= baseEnemyX)
					{
						enemyX = baseEnemyX;
						finish(callback);
						timer.stop();
					}
				}
			}
		}
		repaint();
	});

	timer.start();
}

private void finish(Runnable callback)
{
	showImpact = false;
	showCriticalText = false;
	SwingUtilities.invokeLater(callback);
}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
		g2.drawImage(playerImg, (int)playerX, (int)playerY, null);
		g2.drawImage(enemyImg, (int)enemyX, (int)enemyY, null);

		if (showImpact)
		{
			if (showCriticalText)
			{	
		    	g2.drawImage(criticalImpactImg, (int)impactX - 75, (int)impactY - 75, null);
			    g2.setColor(Color.RED);
			    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 28f));

			    String text = "CRITICAL HIT!";
			    int textWidth = g2.getFontMetrics().stringWidth(text);

			    g2.drawString(text, (int)(criticalTextX - textWidth/2), (int)criticalTextY);
			}
			else
		    	g2.drawImage(impactImg, (int)impactX - 75, (int)impactY - 75, null);
		}


		drawHealthBar(g2,
				(int)(getWidth() / 4f - BAR_WIDTH / 2f),
				200,
				displayedPlayerHp,
				playerHpMax);

		drawHealthBar(g2,
				(int)(getWidth() * 3f / 4f - BAR_WIDTH / 2f),
				200,
				displayedEnemyHp,
				enemyHpMax);
		Toolkit.getDefaultToolkit().sync();
	}

	private void drawHealthBar(Graphics2D g2, int x, int y, int hp, int maxHp) {

		int height = 25;

		g2.setColor(Color.GRAY);
		g2.fillRect(x, y, BAR_WIDTH, height);

		float ratio = Math.max(0f, (float) hp / maxHp);
		int hpWidth = (int) (BAR_WIDTH * ratio);

		g2.setColor(Color.RED);
		g2.fillRect(x, y, hpWidth, height);

		g2.setColor(Color.WHITE);
		g2.drawRect(x, y, BAR_WIDTH, height);
	}
}
