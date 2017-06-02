package game.Menus;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.Button;
import game.GameUtils;

// Class that gives the player info about Cassen and his abilities
public class CassenMenu extends Screen
{
	private BufferedImage character;

	public CassenMenu(BufferedImage background, BufferedImage characterImage)
	{
		super(background);
		character = characterImage;
		buttonList.add(new Button(width * 1 / 13, height * 3 / 19, "BACK"));
	}

	@Override
	public void draw(Graphics g)
	{
		GameUtils.self().drawImg(bg, 0, 0, (width + 15), (height + 15), g);

		for (int i = 0; i < buttonList.size(); i++)
		{
			getKey(i).draw(g);
		}
		GameUtils.self().drawText(width * 5 / 12, height * 2 / 9, "CASSEN", 72, g);
		GameUtils.self().drawImg(character, bg.getWidth()/13, 250, character.getWidth() / 12, character.getHeight() / 12, g);
		GameUtils.self().drawText(width * 5 / 12, 275, "Mr. Cassen is the principal of Westview", 30, g);
		GameUtils.self().drawText(width * 5 / 12, 315, "High School and has lead it to become", 30, g);
		GameUtils.self().drawText(width * 5 / 12, 355, "one of the top schools in California.", 30, g);
		GameUtils.self().drawText(width * 5 / 12, 475, "Punch Power: 6", 30, g);
		GameUtils.self().drawText(width * 5 / 12, 515, "Kick Power: 6", 30, g);
		GameUtils.self().drawText(width * 5 / 12, 555, "Speed: 7", 30, g);
		GameUtils.self().drawText(width * 5 / 12, 595, "Health: 500", 30, g);
		GameUtils.self().drawText(width * 5 / 12, 675, "This character can massively hurt the", 30, g);
		GameUtils.self().drawText(width * 5 / 12, 675, "opponent if they don't block", 30, g);
	}

	public Button getBack()
	{
		return buttonList.get(0);
	}

	public int getNumButtons()
	{
		return buttonList.size();
	}

	public void rebind(int keyToRebind, char key)
	{
		Button b = buttonList.get(keyToRebind);
		b.setText(String.valueOf(key));
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
