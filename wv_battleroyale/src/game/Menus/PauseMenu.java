package game.Menus;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import game.IDrawable;

public class PauseMenu extends Screen implements IDrawable
{
	IDrawable itemToOverlay;
	
	public PauseMenu(IDrawable d, BufferedImage background)
	{
		super(background);
		itemToOverlay = d;
	}
	
	@Override
	public void draw(Graphics g)
	{
		itemToOverlay.draw(g);
		super.draw(g);
	}

}
