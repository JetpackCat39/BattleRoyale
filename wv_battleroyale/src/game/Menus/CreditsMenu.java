package game.Menus;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.Button;
import game.GUIUtils;

public class CreditsMenu extends Screen
{

	public CreditsMenu(BufferedImage background)
	{
		super(background);

		buttonList.add(new Button(width * 1 / 13, height * 3 / 19, "BACK"));
		// player 1 movement controls

	}

	@Override
	public void draw(Graphics g)
	{
		super.draw(g);
		GUIUtils.self().drawText(width / 3, height * 2 / 9, "CREDITS", 72, g, false);

	}

	public Button getBack()
	{
		return buttonList.get(0);
	}

	public int getNumButtons()
	{
		return buttonList.size();
	}

	@Override
	public void mousePressed(IScreen screen, int x, int y)
	{

		if (getBack().contains(x, y))
		{

			screen.getPrevScreen();

		}

	}

}
