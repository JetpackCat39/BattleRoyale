package game.Menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.Button;
import game.GUIUtils;
import game.IDrawable;

public class ControlsMenu extends Screen implements IDrawable
{
	private static final int SIDE = 80;

	public ControlsMenu(BufferedImage background)
	{
		super(background);
		buttonList.add(new Button(width * 1 / 13, height * 3 / 19, "BACK", 32, Font.BOLD));
		buttonList.add(new Button(width * 8 / 30, height * 5 / 9, SIDE, SIDE, "W", 32, Font.PLAIN, 0));
		buttonList.add(new Button(width * 8 / 30, height * 25 / 36, SIDE, SIDE, "S", 32, Font.PLAIN, 0));
		buttonList.add(new Button(width * 46 / 240, height * 25 / 36, SIDE, SIDE, "A", 32, Font.PLAIN, 0));
		buttonList.add(new Button(width * 82 / 240, height * 25 / 36, SIDE, SIDE, "D", 32, Font.PLAIN, 0));
		buttonList.add(new Button(width * 30 / 48, height * 5 / 9, SIDE, SIDE, "\u2191", 32, Font.BOLD, 0));
		buttonList.add(new Button(width * 30 / 48, height * 25 / 36, SIDE, SIDE, "\u2193", 32, Font.BOLD, 0));
		buttonList.add(new Button(width * 26 / 48, height * 25 / 36, SIDE, SIDE, "\u2190", 32, Font.BOLD, 0));
		buttonList.add(new Button(width * 34 / 48, height * 25 / 36, SIDE, SIDE, "\u2192", 32, Font.BOLD, 0));
		buttonList.add(new Button(width * 46 / 240, height * 15 / 18, SIDE, SIDE, "G", 32, Font.PLAIN, 0));
		buttonList.add(new Button(width * 82 / 240, height * 15 / 18, SIDE, SIDE, "H", 32, Font.PLAIN, 0));
		buttonList.add(new Button(width * 26 / 48, height * 15 / 18, SIDE, SIDE, "1", 32, Font.PLAIN, 0));
		buttonList.add(new Button(width * 34 / 48, height * 15 / 18, SIDE, SIDE, "2", 32, Font.PLAIN, 0));
	}

	public void draw(Graphics g)
	{
		super.draw(g);

		GUIUtils.self().drawText(width / 3, height * 2 / 9, Color.white, "CONTROLS", 72, g, Font.BOLD);
		GUIUtils.self().drawText(width / 5, height * 17 / 36, Color.white, "PLAYER 1", 54, g, Font.PLAIN);
		GUIUtils.self().drawText(width * 5 / 9, height * 17 / 36, Color.white, "PLAYER 2", 54, g, Font.PLAIN);
		GUIUtils.self().drawText(width * 47 / 240, height * 39 / 40, Color.white, "PUNCH", 20, g, Font.PLAIN);
		GUIUtils.self().drawText(width * 85 / 240, height * 39 / 40, Color.white, "KICK", 20, g, Font.PLAIN);
		GUIUtils.self().drawText(width * 105 / 192, height * 39 / 40, Color.white, "PUNCH", 20, g, Font.PLAIN);
		GUIUtils.self().drawText(width * 69 / 96, height * 39 / 40, Color.white, "KICK", 20, g, Font.PLAIN);
		for (int i = 0; i < buttonList.size(); i++)
		{
			getKey(i).draw(g);
		}

	}

	public Button getBack()
	{
		return buttonList.get(0);
	}

	public Button getKey(int num)
	{
		return buttonList.get(num);
	}

}
