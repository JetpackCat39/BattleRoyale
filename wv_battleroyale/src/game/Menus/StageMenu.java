package game.Menus;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.Button;
import game.GUIUtils;

public class StageMenu extends Screen
{

	public StageMenu(BufferedImage background)
	{
		super(background);
		buttonList.add(new Button(width * 1 / 8, height / 5, "SCHOOL:FRONT", 20));
		buttonList.add(new Button(BUTTON_CENTER, height / 5, "SCHOOL:QUAD", 20));
		buttonList.add(new Button(width * 11 / 16, height / 5, "AMPITHEATER", 20));
		buttonList.add(new Button(width * 1 / 8, height  * 2 / 5, "THEATER:FRONT", 20));
		buttonList.add(new Button(BUTTON_CENTER, height * 2 / 5, "POOL"));
		buttonList.add(new Button(width * 11 / 16, height * 2 / 5, "FOOTBALL FIELD", 20));
		// return button
		buttonList.add(new Button(width * 1 / 8, height * 4 / 5, "RETURN"));
		// play button
		buttonList.add(new Button(width * 11 / 16, height * 4 / 5, "PLAY!"));
	}
	
	@Override
	public void draw(Graphics g)
	{
		super.draw(g);
		getSchoolFront().draw(g);
		getSchoolQuad().draw(g);
		getAmpitheater().draw(g);
		getTheaterFront().draw(g);
		getPool().draw(g);
		getFootballField().draw(g);
		getReturn().draw(g);
		getPlay().draw(g);
	}
	
	public Button getSchoolFront()
	{
		return buttonList.get(0);
	}

	public Button getSchoolQuad()
	{
		return buttonList.get(1);
	}

	public Button getAmpitheater()
	{
		return buttonList.get(2);
	}

	public Button getTheaterFront()
	{
		return buttonList.get(3);
	}

	public Button getPool()
	{
		return buttonList.get(4);
	}

	public Button getFootballField()
	{
		return buttonList.get(5);
	}
	
	public Button getReturn()
	{
		return buttonList.get(6);
	}

	public Button getPlay()
	{
		return buttonList.get(7);
	}


	@Override
	public void mousePressed(IScreen screen, int x, int y)
	{
		if (getPlay().contains(x, y))
		{
			screen.setScreen(screen.getGame());
		}
		if (getReturn().contains(x, y))
		{
			screen.getPrevScreen();
		}
	}

}
