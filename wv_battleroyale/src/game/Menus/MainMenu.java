package game.Menus;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import game.Button;
import game.GUIUtils;
import game.IDrawable;

public class MainMenu extends Screen implements IDrawable
{

	BufferedImage litFire;
	
	public MainMenu(BufferedImage background, BufferedImage fire)
	{
		super(background);
		litFire = fire;
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
		GUIUtils.self().drawText(850, 480, "B A T T L E  R O Y A L E", 32, g);
		GUIUtils.self().drawImg(litFire, 595, height - 230, litFire.getWidth() * 2 / 3, litFire.getHeight() * 1/35, g);
		GUIUtils.self().drawImg(litFire, 595, height - 280, litFire.getWidth() * 2 / 3, litFire.getHeight() * 1/35, g);
		GUIUtils.self().drawImg(litFire, 595, height - 280, litFire.getWidth() * 1/150, litFire.getHeight() * 1/2, g);
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
