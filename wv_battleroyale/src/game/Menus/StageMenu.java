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
		int w = 280;
		buttonList.add(new Button((width * 1/5) - w/2, height / 5, w, 80, "FRONT OF THE SCHOOL", 20));
		buttonList.add(new Button((width * 1/2) - w/2, height / 5, w, 80, "QUAD", 20));
		buttonList.add(new Button((width * 4/5) - w/2, height / 5, w, 80, "FRONT OF THE THEATER", 20));
		buttonList.add(new Button((width * 1/5) - w/2, height * 2 / 5, w, 80, "AMPITHEATER", 20));
		buttonList.add(new Button((width * 1/2) - w/2, height * 2 / 5, w, 80, "POOL", 20));
		buttonList.add(new Button((width * 4/5) - w/2, height * 2 / 5, w, 80, "THEATER", 20));
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
	
	public Button getQuad() {
		return buttonList.get(1);
	}

	public Button getTheaterFront()
	{
		return buttonList.get(2);
	}

	public Button getAmpitheater()
	{
		return buttonList.get(3);
	}
	
	public Button getPool() {
		return buttonList.get(4);
	}

	public Button getTheater()
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
			screen.setBackground(GameUtils.self().loadImage("Images/front.JPG"));
			isSelected = true;
		}
		if (getAmpitheater().contains(x, y))
		{
			setSelected(getAmpitheater());
			screen.setBackground(GameUtils.self().loadImage("Images/ampitheater.JPG"));
			isSelected = true;
		}
		if (getTheaterFront().contains(x, y))
		{
			setSelected(getTheaterFront());
			screen.setBackground(GameUtils.self().loadImage("Images/theater_front.JPG"));
			isSelected = true;
		}
		if (getTheater().contains(x, y))
		{
			setSelected(getTheater());
			screen.setBackground(GameUtils.self().loadImage("Images/theater.JPG"));
			isSelected = true;
		}
		if (getQuad().contains(x, y))
		{
			setSelected(getQuad());
			screen.setBackground(GameUtils.self().loadImage("Images/quad.jpg"));
			isSelected = true;
		}
		if (getPool().contains(x, y))
		{
			setSelected(getPool());
			screen.setBackground(GameUtils.self().loadImage("Images/pool.jpg"));
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
