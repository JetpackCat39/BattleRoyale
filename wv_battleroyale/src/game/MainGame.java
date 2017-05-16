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
		if(midpoint > 1184 - 52) {
			midpoint = 52;
			p1.setX(0);
			p2.setX(104);
		}
		if(midpoint < 52) {
			midpoint = 1184 - 52;
		
		}
		GUIUtils.self().drawImg(bg, -430 - (midpoint/2), 0, bg.getWidth(), bg.getHeight(), g);
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
