package game.Menus;

import java.awt.*;
import java.awt.image.BufferedImage;

import game.Button;
import game.GUIUtils;
import game.IDrawable;

public class MainMenu extends Screen implements IDrawable, IMenu
{
	public MainMenu(BufferedImage background)
	{
		super(background);
		// play button
		buttonList.add(new Button(width * 1 / 5, height * 1 / 5, "PLAY"));
		// controls button
		buttonList.add(new Button(width * 1 / 5, height * 2 / 5, "CONTROLS"));
		// Credits button
		buttonList.add(new Button(width * 1 / 5, height * 3 / 5, "CREDITS"));
		// exit button
		buttonList.add(new Button(width * 1 / 5, height * 4 / 5, "EXIT"));
	}

	public void draw(Graphics g)
	{
		super.draw(g);
		getPlay().draw(g);
		getControls().draw(g);
		getExit().draw(g);
	}

	public Button getPlay()
	{
		return buttonList.get(0);
	}

	public Button getControls()
	{
		return buttonList.get(1);
	}

	public Button getCredits()
	{
		return buttonList.get(2);
	}

	public Button getExit()
	{
		return buttonList.get(3);
	}

	@Override
	public void mousePressed(IScreen screen, int x, int y)
	{
		if (getPlay().contains(x, y))
		{
			screen.setScreen(screen.getChampSelect(), true);
		}
		if (getControls().contains(x, y))
		{
			screen.setScreen(screen.getControls(), false);
		}
		if (getCredits().contains(x, y))
		{
			screen.setScreen(screen.getCredits(), false);
		}
		if (getExit().contains(x, y))
		{
			screen.setScreen(screen.getExit(), false);
		}

	}

}
