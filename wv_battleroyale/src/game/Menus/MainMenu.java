package game.Menus;

import java.awt.*;
import java.awt.image.BufferedImage;

import game.Button;
import game.GUIUtils;
import game.IDrawable;

public class MainMenu extends Screen implements IDrawable
{

	public MainMenu(BufferedImage background)
	{
		super(background);

		// play button
		buttonList.add(new Button(width * 1/5, height * 2 / 5, "PLAY"));
		// controls button
		buttonList.add(new Button(width * 1/5, height * 3 / 5, "CONTROLS"));
		// exit button
		buttonList.add(new Button(width * 1/5, height * 4 / 5, "EXIT"));
	}

	public void draw(Graphics g)
	{
		super.draw(g);
		GUIUtils.self().drawText(850, 480, "BATTLE ROYALE", 40, g);
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

	public Button getExit()
	{
		return buttonList.get(2);
	}

}
