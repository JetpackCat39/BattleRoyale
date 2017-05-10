package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class MainGame extends Screen implements IDrawable
{

	IOpponent p1, p2;

	public MainGame(BufferedImage background, IOpponent player1, IOpponent player2)
	{
		super(background);
		p1 = player1;
		p2 = player2;
	}

	public void draw(Graphics g)
	{
		super.draw(g);
		p1.draw(g);
		p2.draw(g);
	}
}
