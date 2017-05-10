package game;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

public class MainMenu extends Screen implements IDrawable
{

	public MainMenu(BufferedImage background)
	{
		super(background);

		// play button
		buttonList.add(new Button(width / 3, height * 3 / 5, "PLAY"));
		// controls button
		buttonList.add(new Button(width / 3, height * 4 / 5, "CONTROLS"));
		// exit button
		buttonList.add(new Button(width / 3, height * 5 / 5, "EXIT"));
	}

	public void draw(Graphics g)
	{
		super.draw(g);
		GUIUtils.self().drawText(width / 9, height * 2 / 9, Color.white, Game.TITLE, 72, g);
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
