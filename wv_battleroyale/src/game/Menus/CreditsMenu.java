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
		GUIUtils.self().drawText(width / 3, height * 2 / 9, "CREDITS", 72, g);
		GUIUtils.self().drawText(width /13, height * 11 / 36, "Fire Image: http://dreamicus.com/data/fire/fire-04.jpg", 20, g);
		GUIUtils.self().drawText(width /13, height * 13 / 36, "Video that gave basics for the game: https://www.youtube.com/watch?v=hXImR8Wm53M", 20, g);
		GUIUtils.self().drawText(width /13, height * 15 / 36, "Character Sprites: https://www.spriters-resource.com/snes/supersf2/", 20, g);
	
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
