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
	private int  minVal, offset, maxVal;
	private IOpponent p1, p2;

	public MainGame(BufferedImage background, BattleRoyale g, IOpponent player1, IOpponent player2)
	{
		super(background);
		game = g;
		p1 = player1;
		p2 = player2;
		buttonList.add(new Button(width * 5 / 6, height * 2 / 9, "BACK", 32, Font.BOLD));
		minVal = -(background.getWidth() - width);
		offset = minVal/2;
		maxVal = 0;
	}

	public void draw(Graphics g)
	{
		GUIUtils.self().drawImg(bg, offset, 0, bg.getWidth(), bg.getHeight(), g);
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
