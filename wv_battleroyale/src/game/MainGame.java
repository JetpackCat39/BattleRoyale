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
	private final int LEFT = 0;
	private final int RIGHT = 1184;

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
		//-1452 RIGHT
		//0 LEFT
		//-726 MIDDLE
		GUIUtils.self().drawImg(bg, -430 - (midpoint/2), 0, bg.getWidth(), bg.getHeight(), g);
		p1.draw(g);
		p2.draw(g);
	}

	@Override
	public void keyPressed(BattleRoyale g, int keyCode)
	{
		switch (keyCode)
		{
		case KeyEvent.VK_ESCAPE:
			if (g.getScreen() == g.getGame())
			{
				g.setScreen(g.getPause());
			}
			break;
		default:
			p1.keyPressed(null, keyCode);
			p2.keyPressed(null, keyCode);
			break;
		}
	}
	
	@Override
	public void keyReleased(int keyCode)
	{
		p1.keyReleased(keyCode);
		p2.keyReleased(keyCode);
	}
}
