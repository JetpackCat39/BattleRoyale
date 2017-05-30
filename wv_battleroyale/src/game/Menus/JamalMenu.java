package game.Menus;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.Button;
import game.GUIUtils;

public class JamalMenu extends Screen
{
	private BufferedImage character;

	public JamalMenu(BufferedImage background, BufferedImage characterImage)
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
		GUIUtils.self().drawText(width * 5 / 12, height * 2 / 9, "JAMAL", 72, g);
		GUIUtils.self().drawImg(character, 150, 250, character.getWidth() / 12, character.getHeight() / 12, g);
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
