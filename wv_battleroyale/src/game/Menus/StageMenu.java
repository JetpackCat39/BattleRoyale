package game.Menus;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.GUIUtils;

public class StageMenu extends Screen
{

	public StageMenu(BufferedImage background)
	{
		super(background);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void draw(Graphics g)
	{
		GUIUtils.self().drawText(width/2, height/2, "CLICK THE MOUSE", 72, g, false);
	}

	@Override
	public void mousePressed(IScreen screen, int x, int y)
	{
		screen.setScreen(screen.getGame());
	}

}
