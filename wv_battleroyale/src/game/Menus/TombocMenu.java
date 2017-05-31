package game.Menus;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.Button;
import game.GameUtils;

public class TombocMenu extends Screen
{

	private BufferedImage character;

	public TombocMenu(BufferedImage background, BufferedImage characterImage)
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
		GameUtils.self().drawText(width * 5 / 12, height * 2 / 9, "TOMBOC", 72, g);
		GameUtils.self().drawImg(character, bg.getWidth()/13, 250, character.getWidth() / 12, character.getHeight() / 12, g);
		GameUtils.self().drawText(width * 5 / 12, 275, "Ms. Tomboc is the Head Librarian at Westview.", 30, g);
		GameUtils.self().drawText(width * 5 / 12, 315, "She has made the library a place for students", 30, g);
		GameUtils.self().drawText(width * 5 / 12, 355, "to hang out and study.", 30, g);
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
