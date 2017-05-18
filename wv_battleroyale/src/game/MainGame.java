package game;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import game.Menus.IScreen;
import game.Menus.Screen;

public class MainGame extends Screen
{

	private IOpponent p1, p2;
	private int  minVal, offset, maxVal;

	public MainGame(BufferedImage background, IOpponent player1, IOpponent player2)
	{
		super(background);
		p1 = player1;
		p2 = player2;
		minVal = -(background.getWidth());
		offset = minVal/2;
		maxVal = 0;
	}

	@Override
	public void draw(Graphics g)
	{
		GUIUtils.self().drawImg(bg, offset, 0, bg.getWidth(), bg.getHeight(), g);
		p1.draw(g);
		p2.draw(g);
	}
	
	public boolean move()
	{
		if (Math.abs(p1.getX() - (p2.getX() + p2.getWidth())) > width)
		{
			return false; //need to find a way to jump at the ends of teh screen, becase you cant do that here.
		}
		
		int p1Min = Math.max(0, p2.getX() + p2.getWidth() - width);
		int p1Max = Math.min(bg.getWidth() - p1.getWidth(), p2.getX() + p2.getWidth());
		int p2Min = Math.max(0, p1.getX() + p1.getWidth() - width);
		int p2Max = Math.min(bg.getWidth() - p2.getWidth(), p1.getX() + p1.getWidth());
		
		boolean p1Moved = p1.move(p1Min, p1Max);
		boolean p2Moved = p2.move(p2Min, p2Max);	
		
		if (p1.getX() + offset < 0 || p1.getX() + offset < 0)
		{
			offset = -Math.min(p1.getX(), p2.getX());
		}
		
		if (p1.getX() + offset > width || p1.getX() + offset > width)
		{
			offset = -Math.max(p1.getX() + p1.getWidth(), p2.getX() + p2.getWidth());
		}
		
		return (p1Moved || p2Moved);
	}

	@Override
	public void keyPressed(IScreen screen, int keyCode)
	{
		switch (keyCode)
		{
		case KeyEvent.VK_ESCAPE:
			if (screen.getScreen() == screen.getGame())
			{
				screen.setScreen(screen.getPause());
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
