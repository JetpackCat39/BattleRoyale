package game.Menus;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import game.Button;
import game.GameUtils;

public class StageMenu extends Screen
{
	private static final Color SELECT = Color.MAGENTA;
	private static final Color PLAY = Color.GREEN;
	private boolean isSelected;
	private Button selected;

	public StageMenu(BufferedImage background)
	{
		super(background);
		buttonList.add(new Button(width * 1 / 8, height / 5, "SCHOOL:FRONT", 20));
		buttonList.add(new Button(BUTTON_CENTER, height / 5, "SCHOOL:QUAD", 20));
		buttonList.add(new Button(width * 11 / 16, height / 5, "AMPITHEATER", 20));
		buttonList.add(new Button(width * 1 / 8, height * 2 / 5, "THEATER:FRONT", 20));
		buttonList.add(new Button(BUTTON_CENTER, height * 2 / 5, "POOL"));
		buttonList.add(new Button(width * 11 / 16, height * 2 / 5, "FOOTBALL FIELD", 20));
		// return button
		buttonList.add(new Button(width * 1 / 8, height * 4 / 5, "BACK"));
		// cancel button
		buttonList.add(new Button(BUTTON_CENTER, height * 4/5, "CANCEL"));
		// play button
		buttonList.add(new Button(width * 11 / 16, height * 4 / 5, "PLAY!"));
		reset();
	}
	
	@Override
	public void reset()
	{
		isSelected = false;
	}

	@Override
	public void draw(Graphics g)
	{
		super.draw(g);
		
		if (isSelected)
		{
			selected.fill(g, SELECT);
			getPlay().fill(g, PLAY);
		}

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

	public Button getBack()
	{
		return buttonList.get(6);
	}

	public Button getCancel()
	{
		return buttonList.get(7);
	}
	
	public Button getPlay()
	{
		return buttonList.get(8);
	}

	private void setSelected(Button newSelected)
	{
		selected = newSelected;
	}

	@Override
	public void mousePressed(IScreen screen, int x, int y) throws IOException
	{
		if (getSchoolFront().contains(x, y))
		{
			setSelected(getSchoolFront());
			screen.setBackground(GameUtils.self().loadImage("Images/schoolfront.png"));
			isSelected = true;
		}
		if (getSchoolQuad().contains(x, y))
		{
			setSelected(getSchoolQuad());
			screen.setBackground(GameUtils.self().loadImage("Images/schoolquad.png"));
			isSelected = true;
		}
		if (getAmpitheater().contains(x, y))
		{
			setSelected(getAmpitheater());
			screen.setBackground(GameUtils.self().loadImage("Images/ampitheater.png"));
			isSelected = true;
		}
		if (getTheaterFront().contains(x, y))
		{
			setSelected(getTheaterFront());
			screen.setBackground(GameUtils.self().loadImage("Images/theaterfront.png"));
			isSelected = true;
		}
		if (getPool().contains(x, y))
		{
			setSelected(getPool());
			screen.setBackground(GameUtils.self().loadImage("Images/pool.png"));
			isSelected = true;
		}
		if (getFootballField().contains(x, y))
		{
			setSelected(getFootballField());
			screen.setBackground(GameUtils.self().loadImage("Images/footballfield.png"));
			isSelected = true;
		}

		if (isSelected)
		{
			if (getPlay().contains(x, y))
			{
				screen.setScreen(screen.getNewGame(), true);
			}
		}
		if (getBack().contains(x, y))
		{
			screen.getPrevScreen();
		}
		if (getCancel().contains(x, y))
		{
			screen.setScreen(screen.getMenu(), false);
		}

	}

}
