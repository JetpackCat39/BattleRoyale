package game.Menus;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.Button;
import game.GUIUtils;
import game.IDrawable;

public class ControlsMenu extends Screen implements IDrawable
{
	private static final int SIDE = 80;

	public ControlsMenu(BufferedImage background)
	{
		super(background);
		buttonList.add(new Button(width * 1 / 13, height * 3 / 19, "BACK"));
		// player 1 movement controls
		buttonList.add(new Button(width * 8 / 30, height * 5 / 9, SIDE, SIDE, 0, "W"));
		buttonList.add(new Button(width * 8 / 30, height * 25 / 36, SIDE, SIDE, 0, "S"));
		buttonList.add(new Button(width * 46 / 240, height * 25 / 36, SIDE, SIDE, 0, "A"));
		buttonList.add(new Button(width * 82 / 240, height * 25 / 36, SIDE, SIDE, 0, "D"));
		// player 1 punch
		buttonList.add(new Button(width * 46 / 240, height * 15 / 18, SIDE, SIDE, 0, "T"));
		// player 1 kick
		buttonList.add(new Button(width * 82 / 240, height * 15 / 18, SIDE, SIDE, 0, "Y"));
		
		// player 2 movement controls
		buttonList.add(new Button(width * 30 / 48, height * 5 / 9, SIDE, SIDE, 0, "\u2191")); // up arrow key
		buttonList.add(new Button(width * 30 / 48, height * 25 / 36, SIDE, SIDE, 0, "\u2193")); // down arrow key
		buttonList.add(new Button(width * 26 / 48, height * 25 / 36, SIDE, SIDE, 0, "\u2190")); // left arrow key
		buttonList.add(new Button(width * 34 / 48, height * 25 / 36, SIDE, SIDE, 0, "\u2192")); // right arrow key
		// player 2 punch
		buttonList.add(new Button(width * 26 / 48, height * 15 / 18, SIDE, SIDE, 0, "N"));
		// player 2 kick
		buttonList.add(new Button(width * 34 / 48, height * 15 / 18, SIDE, SIDE, 0, "M"));
	}

	public void draw(Graphics g)
	{
		super.draw(g);

		GUIUtils.self().drawText(width / 3, height * 2 / 9, "CONTROLS", 72, g);
		GUIUtils.self().drawText(width / 5, height * 17 / 36, "PLAYER 1", 54, g);
		GUIUtils.self().drawText(width * 5 / 9, height * 17 / 36, "PLAYER 2", 54, g);
		GUIUtils.self().drawText(width * 47 / 240, height * 39 / 40, "PUNCH", 20, g);
		GUIUtils.self().drawText(width * 85 / 240, height * 39 / 40, "KICK", 20, g);
		GUIUtils.self().drawText(width * 105 / 192, height * 39 / 40, "PUNCH", 20, g);
		GUIUtils.self().drawText(width * 69 / 96, height * 39 / 40, "KICK", 20, g);
		for (int i = 0; i < buttonList.size(); i++)
		{
			getKey(i).draw(g);
		}

	}

	public Button getBack()
	{
		return buttonList.get(0);
	}

	public Button getKey(int num)
	{
		return buttonList.get(num);
	}
	
	public int getNumButtons()
	{
		return buttonList.size();
	}

	public void rebind(int keyToRebind, char key)
	{
		Button b = buttonList.remove(keyToRebind);
		b.setText(String.valueOf(key));
		buttonList.add(b);
	}

}
