package game;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import game.BattleRoyale.STATE;
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
	public STATE keyPressed(int keyCode, STATE currentState, STATE previousState)
	{
		switch (keyCode)
		{
		case KeyEvent.VK_ESCAPE:
			if (currentState == STATE.GAME)
			{
				return STATE.PAUSE;
			}
			break;
			default:
				p1.keyPressed(keyCode, currentState, previousState);
				p2.keyPressed(keyCode, currentState, previousState);
		}
		return currentState;
	}
	
	@Override
	public STATE keyReleased(int keyCode, STATE currentState, STATE previousState)
	{
		p1.keyReleased(keyCode, currentState, previousState);
		p2.keyReleased(keyCode, currentState, previousState);
		return currentState;
	}
}
