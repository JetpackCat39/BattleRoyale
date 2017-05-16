package game.Menus;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import game.BattleRoyale;
import game.BattleRoyale.STATE;
import game.Button;
import game.GUIUtils;
import game.IDrawable;
import game.Input.IKeya;

public class Screen implements IDrawable, IMenu, IKeya
{
	protected int height = BattleRoyale.HEIGHT;
	protected int width = BattleRoyale.WIDTH;
	protected static final int BUTTON_CENTER = 530;

	protected ArrayList<Button> buttonList;
	protected BufferedImage bg;

	public Screen(BufferedImage background)
	{
		bg = background;
		buttonList = new ArrayList<Button>();
	}

	@Override
	public void draw(Graphics g)
	{
		GUIUtils.self().drawImg(bg, 0, 0, (width + 15), (height + 15), g);
	}

	@Override
	public STATE mousePressed(int x, int y, STATE currentState, STATE previousState)
	{
		return currentState;
	}

	@Override
	public STATE keyPressed(int keyCode, STATE currentState, STATE previousState)
	{
		return currentState;
	}

	@Override
	public STATE keyReleased(int keyCode, STATE currentState, STATE previousState)
	{
		return currentState;
	}
}
