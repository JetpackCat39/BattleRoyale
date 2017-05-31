package game.Menus;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.Button;
import game.GUIUtils;

// Class that gives a player info about Bob and his abilites
public class BobMenu extends Screen
{
	private BufferedImage character;

	public BobMenu(BufferedImage background, BufferedImage characterImage)
	{
		super(background);
		character = characterImage;
		buttonList.add(new Button(width * 1 / 13, height * 3 / 19, "BACK"));
	}

	@Override
	public void draw(Graphics g)
	{
		GUIUtils.self().drawImg(bg, 0, 0, (width + 15), (height + 15), g);

		for (int i = 0; i < buttonList.size(); i++)
		{
			getKey(i).draw(g);
		}
		GUIUtils.self().drawText(width * 5 / 12, height * 2 / 9, "BOB", 72, g);
		GUIUtils.self().drawImg(character, bg.getWidth()/13, 250, character.getWidth() / 12, character.getHeight() / 12, g);
		GUIUtils.self().drawText(width * 5 / 12, 275, "Mr. Bob is an English teacher at Westview", 30, g);
		GUIUtils.self().drawText(width * 5 / 12, 315, "High School.  He focuses on making students", 30, g);
		GUIUtils.self().drawText(width * 5 / 12, 355, "enjoy writing instead of worrying about formats", 30, g);
		GUIUtils.self().drawText(width * 5 / 12, 395, "or requirements. ", 30, g);


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
