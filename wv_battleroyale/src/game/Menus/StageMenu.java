package game.Menus;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.GUIUtils;
import game.BattleRoyale.STATE;

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
	public STATE mousePressed(int x, int y, STATE currentState, STATE previousState)
	{
		return STATE.GAME;
	}

}
