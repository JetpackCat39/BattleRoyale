package game.Menus;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import game.Button;
import game.GameUtils;

public class WayMenu extends Screen
{

	private BufferedImage character;

	public WayMenu(BufferedImage background)
	{

		super(background);
		try {
			character = GameUtils.self().loadImage("Images/Way-Head.JPG");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		GameUtils.self().drawText(width * 5 / 12, height * 2 / 9, "WAY", 72, g);
		GameUtils.self().drawImg(character, bg.getWidth()/13, 250, character.getWidth() / 12, character.getHeight() / 12, g);
		GameUtils.self().drawText(width * 5 / 12, 275, "Mr. Way is a music teacher at Westview. He", 30, g);
		GameUtils.self().drawText(width * 5 / 12, 315, "teaches students how to play and perform in", 30, g);
		GameUtils.self().drawText(width * 5 / 12, 355, "a concert band and a marching band.", 30, g);
		GameUtils.self().drawText(width * 5 / 12, 475, "Attack: 8", 30, g);
		GameUtils.self().drawText(width * 5 / 12, 555, "Speed: 6", 30, g);
		GameUtils.self().drawText(width * 5 / 12, 595, "Health: 12", 30, g);
		GameUtils.self().drawText(width * 5 / 12, 675, "This character is an average fighter that can", 30, g);
		GameUtils.self().drawText(width * 5 / 12, 715, "find his way around blocked attacks", 30, g);
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
