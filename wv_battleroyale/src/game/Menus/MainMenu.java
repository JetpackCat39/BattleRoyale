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
		buttonList.add(new Button(width / 3, height * 2 / 5, "PLAY", 32, Font.BOLD));
		// controls button
		buttonList.add(new Button(width / 3, height * 3 / 5, "CONTROLS", 32, Font.BOLD));
		// exit button
		buttonList.add(new Button(width / 3, height * 4 / 5, "EXIT", 32, Font.BOLD));
	}

	public void draw(Graphics g)
	{
		super.draw(g);
		GUIUtils.self().drawText(width / 9, height * 2 / 9, Color.white, BattleRoyale.TITLE, 72, g, Font.BOLD);
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
