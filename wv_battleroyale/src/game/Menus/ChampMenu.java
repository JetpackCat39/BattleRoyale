package game.Menus;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.BattleRoyale;
import game.GUIUtils;

public class ChampMenu extends Screen
{

	public ChampMenu(BufferedImage background)
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
	public void mousePressed(BattleRoyale g, int x, int y)
	{
		g.setScreen(g.getStageSelect());
	}

}
