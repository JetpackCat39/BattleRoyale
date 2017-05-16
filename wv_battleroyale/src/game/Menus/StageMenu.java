package game.Menus;

import java.awt.image.BufferedImage;

import game.BattleRoyale.STATE;

public class StageMenu extends Screen
{

	public StageMenu(BufferedImage background)
	{
		super(background);
		// TODO Auto-generated constructor stub
	}

	@Override
	public STATE mousePressed(int x, int y, STATE currentState, STATE previousState)
	{
		return STATE.GAME;
	}

}
