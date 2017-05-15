package game.Menus;

import java.awt.*;
import java.awt.image.BufferedImage;

import game.Button;
import game.GUIUtils;
import game.BattleRoyale;
import game.IDrawable;

public class MainMenu extends Screen implements IDrawable
{

	public MainMenu(BufferedImage background)
	{
		super(background);

		// play button
		buttonList.add(new Button(width / 3, height * 2 / 5, "PLAY"));
		// controls button
		buttonList.add(new Button(width / 3, height * 3 / 5, "CONTROLS"));
		// exit button
		buttonList.add(new Button(width / 3, height * 4 / 5, "EXIT"));
	}

	public void draw(Graphics g)
	{
		super.draw(g);
		GUIUtils.self().drawText(width / 9, height * 2 / 9, BattleRoyale.TITLE, 72, g);
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
