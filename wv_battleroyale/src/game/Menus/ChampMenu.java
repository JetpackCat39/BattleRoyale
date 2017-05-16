package game.Menus;

import java.awt.image.BufferedImage;

import game.BattleRoyale.STATE;

public class ChampMenu extends Screen
{

	public ChampMenu(BufferedImage background)
	{
		super(background);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public STATE mousePressed(int x, int y, STATE currentState, STATE previousState)
	{
		return STATE.STAGESELECT;
	}

}
