package game;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class MainGame extends Screen implements IDrawable
{

	private IOpponent p1, p2;

	public MainGame(BufferedImage background, IOpponent player1, IOpponent player2)
	{
		super(background);
		p1 = player1;
		p2 = player2;
		buttonList.add(new Button(width *5/6, height * 2/9, "BACK",32,Font.BOLD));
	}

	public void draw(Graphics g)
	{
		super.draw(g);
		p1.draw(g);
		p2.draw(g);
	}
}
