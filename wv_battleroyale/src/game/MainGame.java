package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.Fighters.IOpponent;
import game.Menus.IScreen;
import game.Menus.Screen;

public class MainGame extends Screen
{

	private IOpponent p1, p2;
	private int minVal, offset, maxVal;

	public MainGame(BufferedImage background, IOpponent player1, IOpponent player2)
	{
		super(background);
		p1 = player1;
		p2 = player2;
		minVal = -(background.getWidth());
		maxVal = 0;
		setOffset((minVal + width) / 2);
		p1.setX(p1.getX() - offset);
		p2.setX(p2.getX() - offset);
	}

	@Override
	public void draw(Graphics g)
	{
		GUIUtils.self().drawImg(bg, offset, 0, bg.getWidth(), bg.getHeight(), g);
		p1.draw(g, offset);
		p2.draw(g, offset);
	}

	public boolean move()
	{
		int p1Max = p2.getX() - p1.getWidth();
		int p1Min = Math.max(0, p1Max - width);
		int p2Min = p1.getX() + p1.getWidth();
		int p2Max = Math.min(bg.getWidth() - p2.getWidth(), p1.getX() + width - p2.getWidth());

		boolean p1Moved = p1.move(p1Min, p1Max);
		boolean p2Moved = p2.move(p2Min, p2Max);

		if (p1.getX() + offset < 0)
		{
			setOffset(-p1.getX());
		}
		else if (p2.getX() + offset + p2.getWidth() > width)
		{
			setOffset(-(p2.getX() + p2.getWidth() - width));
		}
		return (p1Moved || p2Moved);
	}

	private void setOffset(int newOffset)
	{
		if (newOffset < minVal)
		{
			newOffset = minVal;
		}
		else if (newOffset > maxVal)
		{
			newOffset = maxVal;
		}
		offset = newOffset;
	}

	public IOpponent createP1()
	{
		return null;
	}

	@Override
	public void keyPressed(IScreen screen, int keyCode)
	{
		if (p1.getPause() == keyCode)
		{
			if (screen.getScreen() == screen.getGame())
			{
				screen.setScreen(screen.getPause(), false);
			}

		}
		else
		{
			p1.keyPressed(null, keyCode);
			p2.keyPressed(null, keyCode);
		}
	}

	@Override
	public void keyReleased(int keyCode)
	{
		p1.keyReleased(keyCode);
		p2.keyReleased(keyCode);
	}
}
