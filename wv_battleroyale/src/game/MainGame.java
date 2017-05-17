package game;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import game.Menus.Screen;

public class MainGame extends Screen
{

 	private int midpoint;
	private final int MIDDLE = 592;
	private IOpponent p1, p2;

	public MainGame(BufferedImage background, IOpponent player1, IOpponent player2)
	{
		super(background);
		p1 = player1;
		p2 = player2;
		midpoint = MIDDLE;
	}

	@Override
	public void draw(Graphics g)
	{
		midpoint = (p1.getX() + p2.getX()) / 2;
		GUIUtils.self().drawImg(bg,  (bg.getWidth()/2) - MIDDLE * 8 / 3 - midpoint, 0, bg.getWidth(), bg.getHeight(), g);
		p1.draw(g);
		p2.draw(g);
	}

	@Override
	public Screen keyPressed(BattleRoyale g, int keyCode, Screen currentScreen, Screen previousScreen)
	{
		switch (keyCode)
		{
		case KeyEvent.VK_ESCAPE:
			if (currentScreen == g.getGame())
			{
				return g.getPause();
			}
			break;
		default:
			p1.keyPressed(null, keyCode, currentScreen, previousScreen);
			p2.keyPressed(null, keyCode, currentScreen, previousScreen);
			break;
		}
		return currentScreen;
	}
	
	@Override
	public Screen keyReleased(int keyCode, Screen currentScreen, Screen previousScreen)
	{
		p1.keyReleased(keyCode, currentScreen, previousScreen);
		p2.keyReleased(keyCode, currentScreen, previousScreen);
		return currentScreen;
	}
}
