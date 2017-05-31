package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.Fighters.Fighter;
import game.Menus.IScreen;
import game.Menus.Screen;

public class MainGame extends Screen
{
	private Fighter p1, p2;
	private int minVal, offset, maxVal;

	public MainGame(BufferedImage background, Fighter player1, Fighter player2)
	{
		super(background);
		p1 = player1;
		p2 = player2;
		minVal = -(background.getWidth());
		maxVal = 0;
		setOffset((minVal + width) / 2);
		p1.setLeft(p1.getLeft() - offset);
		p2.setLeft(p2.getLeft() - offset);
	}

	@Override
	public void draw(Graphics g)
	{
		GameUtils.self().drawImg(bg, offset, 0, bg.getWidth(), bg.getHeight(), g);
		p1.draw(g, offset);
		p2.draw(g, offset);
	}

	public boolean move()
	{
		int p1Max = p2.getLeft() - p1.getWidth();
		int p1Min = Math.max(0, p2.getRight() - width);
		int p2Min = p1.getRight();
		int p2Max = Math.min(bg.getWidth() - p2.getWidth(), p1.getLeft() + width - p2.getWidth());

		boolean p1Moved = p1.move(p1Min, p1Max);
		boolean p2Moved = p2.move(p2Min, p2Max);

		if (p1.getLeft() + offset < 0)
		{
			setOffset(-p1.getLeft());
		}
		else if (p2.getRight() + offset > width)
		{
			setOffset(-(p2.getRight() - width));
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
