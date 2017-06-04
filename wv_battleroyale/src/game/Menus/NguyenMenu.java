package game.Menus;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import game.Button;
import game.GameUtils;

public class NguyenMenu extends Screen
{

	private BufferedImage character;

	public NguyenMenu(BufferedImage background)
	{

		super(background);
		try {
			character = GameUtils.self().loadImage("Images/Nguyen-Head.JPG");
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
		GameUtils.self().drawText(width * 5 / 12, height * 2 / 9, "NGUYEN", 72, g);
		GameUtils.self().drawImg(character, bg.getWidth()/13, 250, character.getWidth() / 9, character.getHeight() / 9, g);
		GameUtils.self().drawText(width * 5 / 12, 275, "Mr. Nguyen is a math teacher at Westview.", 30, g);
		GameUtils.self().drawText(width * 5 / 12, 315, "He connects with the students and makes", 30, g);
		GameUtils.self().drawText(width * 5 / 12, 355, "sure they understand the content.", 30, g);
		GameUtils.self().drawText(width * 5 / 12, 475, "Attack:12", 30, g);
		GameUtils.self().drawText(width * 5 / 12, 555, "Speed: 5", 30, g);
		GameUtils.self().drawText(width * 5 / 12, 595, "Health: 8", 30, g);
		GameUtils.self().drawText(width * 5 / 12, 675, "This character is a fast, all-around attacker", 30, g);
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
