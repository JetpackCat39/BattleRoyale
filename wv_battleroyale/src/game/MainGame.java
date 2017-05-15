package game;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import game.BattleRoyale.STATE;
import game.Menus.Screen;

public class MainGame extends Screen implements IDrawable, KeyListener
{

	private BattleRoyale game;
	private int midpoint;
	private final int MIDDLE = 592;
	private IOpponent p1, p2;

	public MainGame(BufferedImage background, BattleRoyale g, IOpponent player1, IOpponent player2)
	{
		super(background);
		game = g;
		p1 = player1;
		p2 = player2;
		midpoint = MIDDLE;
	}

	public void draw(Graphics g)
	{
		midpoint = (p1.getX() + p2.getX()) / 2;
		GUIUtils.self().drawImg(bg,  (bg.getWidth()/2) - MIDDLE * 8 / 3 - midpoint, 0, bg.getWidth(), bg.getHeight(), g);
		p1.draw(g);
		p2.draw(g);
	}

	public void keyTyped(KeyEvent e)
	{
	}

	public void keyPressed(KeyEvent e)
	{
		switch (e.getExtendedKeyCode())
		{
		case KeyEvent.VK_ESCAPE:
			if (game.getState() == STATE.GAME)
			{
				game.setState(STATE.PAUSE);
			}
			break;
		}
	}

	public void keyReleased(KeyEvent e)
	{		
	}
}
