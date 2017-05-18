package game.Menus;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import game.Input.PlayerControls;

import game.Button;
import game.GUIUtils;

public class ChampMenu extends Screen 
{
	private boolean isSelected;
	private static final Color P1COLOR = Color.red;
	private static final Color P2COLOR = Color.blue;

	public ChampMenu(BufferedImage background)
	{
		super(background);
		// TODO Auto-generated constructor stub
		// play button
		buttonList.add(new Button(width * 1 / 5, height * 2 / 5, "TAMMY"));
		// controls button
		buttonList.add(new Button(width * 1 / 5, height * 3 / 5, "JUSTIN"));
		// exit button
		buttonList.add(new Button(width * 1 / 5, height * 4 / 5, "EXIT"));
		isSelected = false;
	}

	@Override
	public void draw(Graphics g)
	{
		// GUIUtils.self().drawText(width/2, height/2, "CLICK THE MOUSE", 72, g,
		// false);
		super.draw(g);
		getTammy().draw(g);
		getJustin().draw(g);
		getExit().draw(g);
		GUIUtils.self().drawSelector(g, getTammy(), P1COLOR);
	}

	public Button getTammy()
	{
		return buttonList.get(0);
	}

	public Button getJustin()
	{
		return buttonList.get(1);
	}

	public Button getExit()
	{
		return buttonList.get(2);
	}

	public Button getNext()
	{
		return null;
	}

	@Override
	public void mousePressed(IScreen screen, int x, int y)
	{
		if (isSelected)
		{
			if (getNext().contains(x, y))
			{
				isSelected = true;
			}
		}
	}
	
	
}
