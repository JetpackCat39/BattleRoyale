package game.Menus;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import game.BattleRoyale;
import game.Button;
import game.GUIUtils;
import game.IDrawable;

public class Screen implements IDrawable
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

	public void draw(Graphics g)
	{
		GUIUtils.self().drawImg(bg, 0, 0, (width + 15), (height + 15), g);
	}
}
