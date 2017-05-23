package game.Menus;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.Button;
import game.GUIUtils;

public class PauseMenu extends Screen
{
	IScreen itemToOverlay;
	
	public PauseMenu(IScreen d, BufferedImage background)
	{
		super(background);
		itemToOverlay = d;
		buttonList.add(new Button(BUTTON_CENTER, height * 1/2, "RESUME"));
		buttonList.add(new Button(BUTTON_CENTER, height * 7/10, "CONTROLS"));
		buttonList.add(new Button(BUTTON_CENTER, height * 9/10, "TITLE"));
	}
	
	public void draw(Graphics g)
	{
		itemToOverlay.getGame().draw(g);
		super.draw(g);
		GUIUtils.self().drawText(width / 2 - 72*2, height * 1/3, "PAUSED", 72, g, false);
		getResume().draw(g);
		getControls().draw(g);
		getTitle().draw(g);
	}

	public Button getResume()
	{
		return buttonList.get(0);
	}
	
	public Button getControls()
	{
		return buttonList.get(1);
	}
	
	public Button getTitle()
	{
		return buttonList.get(2);
	}

	@Override
	public void mousePressed(IScreen screen, int x, int y)
	{
		if (getResume().contains(x, y))
		{
			screen.setScreen(screen.getGame(), false);
		}
		if (getControls().contains(x, y))
		{
			screen.setScreen(screen.getControls(), false);
		}
		if (getTitle().contains(x, y))
		{
			screen.setScreen(screen.getMenu(), false);
		}
	}
}
