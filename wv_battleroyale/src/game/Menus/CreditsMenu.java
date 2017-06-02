package game.Menus;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.Button;
import game.GameUtils;

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
		GameUtils.self().drawText(width / 3, height * 2 / 9, "CREDITS", 72, g);
		GameUtils.self().drawText(width /13, height * 11 / 36, "Game Creators: Evan Thurston, Patrick Paxson, Justin Slavick, Steven Zhang", 20, g);
		GameUtils.self().drawText(width /13, height * 13 / 36, "Tutorial for editing sprites: https://www.youtube.com/watch?v=hXImR8Wm53M", 20, g);
		GameUtils.self().drawText(width /13, height * 15 / 36, "Character Sprites: https://www.spriters-resource.com/snes/supersf2/", 20, g);
		GameUtils.self().drawText(width/13, height * 17 / 36, "Certain sound effects from: https://www.sounds-resource.com/arcade/sf2/", 20, g);
		GameUtils.self().drawText(width/13, height * 19/36, "Unedited Roblox Death sound effect from: https://www.youtube.com/watch?v=f49ELvryhao", 20, g);
		GameUtils.self().drawText(width /13, height * 21 / 36, "All pictures taken were at Westview High School", 20, g);
		GameUtils.self().drawText(width/13, height * 23 / 36, "Fire image used to edit the title screen found here: http://shaedsofgrey.deviantart.com/art/fire-45734782", 20, g);
		GameUtils .self().drawText(width /13, height * 25 / 36, "A huge thanks to all the teachers for letting us put them in the game!", 20, g);
		
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
